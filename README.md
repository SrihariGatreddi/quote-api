

A simple Spring Boot REST API that returns quotes, with IP-based rate limiting and Swagger API documentation. Built as part of the Kuppi Smart assessment.
Features of the Project:
- Java 8 + Spring Boot
- In-memory storage using `ConcurrentHashMap`
- Two GET endpoints:
  - `/api/quotes` – Get all quotes
  - `/api/quotes/{id}` – Get quote by ID
- IP-based rate limiting (5 requests per minute per endpoint)
- Logs client IP and response status
- Swagger/OpenAPI documentation
- Thread-safe and concurrency-safe

Setup Instructions

1. Clone the repository on your local machine (Editors: vscode or ecllipse ):
   git clone https://github.com/yourusername/quote-api.git
   cd quote-api
2.If you're using Eclipse:
Import the project:
File → Import → Maven → Existing Maven Projects
Navigate to the project folder.
Right-click QuoteApiApplication.java → Run As → Java Application.
3.Access the application:
cURL Commands to Test Quote API:
 1.Get all quotes
curl http://localhost:8086/api/quotes
2.Get a specific quote by ID
curl http://localhost:8086/api/quotes/1

or 
SWAGGER UI:
1.http://localhost:8086/swagger-ui/index.html
or

Quote API Endpoints:
1.Get All Quotes:
GET http://localhost:8086/api/quotes

2.Get Quote by ID (e.g., ID = 1):
GET http://localhost:8086/api/quotes/1


Rate Limiting
Max 5 requests per minute per IP per endpoint.
If exceeded, you’ll receive:
Status: 429 Too Many Requests
Header: Retry-After: <seconds>
Body: Too many requests. Please try again after <seconds> seconds.


Project Structure:

com.example.quoteapi.controller – contains QuoteController.java
com.example.quoteapi.model – contains Quote.java
com.example.quoteapi.service – contains QuoteService.java
com.example.quoteapi.config – contains RateLimitFilter.java
QuoteApiApplication.java – main class to run the Spring Boot app



