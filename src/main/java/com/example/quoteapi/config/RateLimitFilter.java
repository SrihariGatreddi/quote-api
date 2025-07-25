package com.example.quoteapi.config;

import io.github.bucket4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1))))
                .build();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String ip = req.getRemoteAddr();
        String path = req.getRequestURI();
        String key = ip + ":" + path;

        Bucket bucket = buckets.computeIfAbsent(key, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
            logger.info("IP: {}, Path: {}, Status: {}", ip, path, res.getStatus());
        } else {
            long waitTimeNs = bucket.estimateAbilityToConsume(1).getNanosToWaitForRefill();
            long waitTimeMs = waitTimeNs / 1_000_000;
            long waitTimeSec = (waitTimeMs + 999) / 1000;

           
            waitTimeSec = Math.min(Math.max(waitTimeSec, 1), 60);

            res.setStatus(429);

            res.setHeader("Retry-After", String.valueOf(waitTimeSec));
            res.getWriter().write("Too many requests. Please try again after " + waitTimeSec + " seconds.");
            logger.warn("Rate limit exceeded - IP: {}, Path: {}", ip, path);
        }
    }
}
