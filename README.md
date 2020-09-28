# exchange-rates
API test automation project using `Java`, `TestNG` and `REST Assured`. The tests include JSON schema, key values and currency codes validations. There are 2 tests, one to validate the latest exchange rates and another to validate historical exchange rates. For the historical test, the fixed date '2010-01-12' was used.

Observation: In case you wish to test other historical dates, the `currency-codes.csv` file might need to be updated. It only contains current currency codes found online and old ones found on the specified '2010-01-12' date.

# Additional libraries
Besides `TestNG` and `REST Assured`, this project also uses additional libraries, such as:
* `ExtentReports`: to generate the test evidence in an elegant and user friendly way as an HTML file
* `JSONSchemaValidator`: to validate that the body of the request's response matches the JSON schema file

# Requirements
* Java 8 installed

# Running the tests
Tests can be run using the following commands:
```bash
set classpath=<path where you saved the project>\exchange-rates\target\test-classes;<path where you saved the project>\exchange-rates\target\dependency\*
```
```bash
java org.testng.TestNG <path where you saved the project>\exchange-rates\testng.xml 
```
This command will run the tests and generate the HTML file with the test evidences using `ExtentReports`.

# Test results
After the execution, you will be able to find the HTML file of the generated report inside the `reports` folder.