# banking - Teller Counters Application


Service to display list of service counters and token numbers under each service counter: 
Created and edpoint "/counters/" (GET) which returns the details of all the counters available.

Services to Issue tokens to the customer:
Created an endpoint "/tokens/issueToken/{customerId}/{counterServiceType}" (GET) for an existing customer which takes the customerId and the counterServiceType (which type of service the customer is seeking eg: deposit, withdraw etc.,) required by the customer and issues the token accordingly and returns the tokenId.


Created and endpoint "/tokens/issueToken/newCustomer/{counterServiceType}" (POST) which taks the customer details as Request body (name, address, phone number, priority/regular) and the counterServiceType (which type of service the customer is seeking eg: deposit, withdraw etc.,) required by the customer and create an account for the customer and issues token accordingly and returns the tokenId.


Service to Move a Customer/Token to other counter to fulfill the other services:
Create an endpoint "/tokens/changeTokenCounter/{tokenId}/{counterServiceType}" (GET) which takes tokenId and the counterServiceType (which type of service the customer is seeking eg: deposit, withdraw etc.,) required by the customer and assigns corresponding counter which can fulfill the customer needs and returns the counterId assigned to the Token/Customer.


Service to Change the status of Token:
Created an endpoint "/tokens/updateTokenStatus/{tokenId}/{tokenStatus}" (GET) which takes tokenId and tokenStatus to which the token has to be updated and the token is updated accordingly. 



SWAGGER URL to access all the endpoints: http://localhost:8080/swagger-ui.html
