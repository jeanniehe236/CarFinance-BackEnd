# CarFinance - Back-end
Note: The front-end code can be found at: https://github.com/jeanniehe236/CarFinance-FrontEnd.git
## Instructions for running the application
java -jar carfinance-jh-copy.jar

## Instructions for testing the API without the front-end: 
To get the valid non-number input options: http://localhost:8080/car-finance/
To get the an offer, use the following template but replace [] with valid inputs: http://localhost:8080/car-finance/get-offers?channel=[channel]&car-type=[carType]&fuel-type=[fuel type]&tot-cost=[totalCost]&down-payment=[down payment]&term=[term] but replace 
* Examples:
  * http://localhost:8080/car-finance/get-offers?car-type=New&channel=Dealer&fuel-type=Hybrid&tot-cost=1000&down-payment=10&term=10
  * http://localhost:8080/car-finance/get-offers?channel=End_Customer&car-type=New&fuel-type=Petrol&tot-cost=10000&down-payment=100&term=5

## Instructions for code review
* back-end/src/main/java/carfinance/api/ - the folder for the api
* back-end/src/main/java/carfinance/server/ - the folder for the server application
* back-end/src/main/java/carfinance/server/calculator/ - the folder for the classes responsible for annual payment and interest rate calculation
* back-end/src/main/java/carfinance/server/offerGenerator/ - the folder for the classes responsible for generating the offers
* back-end/src/main/java/carfinance/server/responseGenerator/ - the folder for the classes responsible for generating the reponses to the client
* back-end/src/main/java/carfinance/server/preprocessor/ - the folder for the classes responsible for parsing and validating client inputs.
* 
### JUnit-tests:
* back-end/src/test/java/carfinance/ - the folder for the JUnit tests
