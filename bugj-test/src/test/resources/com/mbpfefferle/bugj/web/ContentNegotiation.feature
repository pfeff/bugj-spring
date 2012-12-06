Feature: Content Negotiation
    Scenario: Request JSON data
        Given A bug has been entered
        When I GET the URL with a JSON media type
        Then The response media type should be application/json

