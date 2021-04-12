# CarFinance - Backend

## Instructions for running the application
java -jar carfinance-jh-copy.jar

## Instructions for testing the API without the front-end: 
To get the valid non-number input options: http://localhost:8080/car-finance/
To get the an offer, use the following template but replace [] with valid inputs: http://localhost:8080/car-finance/get-offers?channel=[channel]&car-type=[car type]&fuel-type=[fuel type]&tot-cost=[total cost]&down-payment=[down payment]&term=[term] but replace 
* Examples:
* * http://localhost:8080/car-finance/get-offers?car-type=New&channel=Dealer&fuel-type=Hybrid&tot-cost=1000&down-payment=10&term=10
* * http://localhost:8080/car-finance/get-offers?channel=End_Customer&car-type=New&fuel-type=Petrol&tot-cost=10000&down-payment=100&term=5
