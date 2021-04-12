# CarFinance
## Instructions for running the application ##
### Front-End: ###
cd public
npm install
npm start
### Back-End: ###
java -jar carfinance-jh-copy.jar

## Instructions for code review ##
### Front-End: ###
* front-end/src/screens/HomeScreen.js - the first page
* fron-end/src/screens/ProductScreen.js - the second page
* front-end/src/App.css - the style sheet with CSS
* front-end/src/Utils.js - universal functions shared by the screens

### Back-End: ###
* back-end/src/main/java/carfinance/api/ - the folder for the api
* back-end/src/main/java/carfinance/server/ - the folder for the server application
* back-end/src/main/java/carfinance/server/calculator/ - the folder for the classes responsible for annual payment and interest rate calculation
* back-end/src/main/java/carfinance/server/offerGenerator/ - the folder for the classes responsible for generating the offers
* back-end/src/main/java/carfinance/server/responseGenerator/ - the folder for the classes responsible for generating the reponses to the client
* back-end/src/main/java/carfinance/server/preprocessor/ - the folder for the classes responsible for parsing and validating client inputs.

### JUnit-tests: ###
* back-end/src/test/java/carfinance/ - the folder for the JUnit tests