A simple Spring Boot REST API that returns quotes, with IP-based rate limiting and Swagger API documentation. Built as part of the Kuppi Smart assessment.

Features of the Project:

Java 8 + Spring Boot

In-memory storage using ConcurrentHashMap

Two GET endpoints:

/api/quotes – Get all quotes

/api/quotes/{id} – Get quote by ID

IP-based rate limiting (5 requests per minute per endpoint)

Logs client IP and response status

Swagger/OpenAPI documentation

Thread-safe and concurrency-safe

Setup Instructions:

Clone the repository on your local machine (Editors: VS Code or Eclipse)

git clone https://github.com/yourusername/quote-api.git
cd quote-api

If you're using Eclipse:

Go to File → Import → Maven → Existing Maven Projects

Navigate to the project folder

Right-click QuoteApiApplication.java → Run As → Java Application

Access the application at runtime

Example cURL Commands to Test Quote API:

Get all quotes
curl http://localhost:8086/api/quotes

Get a specific quote by ID
curl http://localhost:8086/api/quotes/1

Swagger UI (to test via browser):

http://localhost:8086/swagger-ui/index.html

Quote API Endpoints:

Get All Quotes:
GET http://localhost:8086/api/quotes

Get Quote by ID (example with ID = 1):
GET http://localhost:8086/api/quotes/1

Rate Limiting:

Max 5 requests per minute per IP per endpoint

If exceeded, the response will include:

Status: 429 Too Many Requests

Header: Retry-After: <seconds>

Body: Too many requests. Please try again after <seconds> seconds.

Project Structure:

com.example.quoteapi.controller – contains QuoteController.java

com.example.quoteapi.model – contains Quote.java

com.example.quoteapi.service – contains QuoteService.java

com.example.quoteapi.config – contains RateLimitFilter.java

QuoteApiApplication.java – main class to run the Spring Boot app

API Base URL:

http://localhost:8086/api/quotes

http://localhost:8086/swagger-ui/index.html

Submitted by Srihari as part of the Kuppi Smart backend assignment
