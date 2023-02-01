# derivco Project

## Introduction
- Repo presenting the coverage of the API test cases of Cocktail DB. The implementation covers the test cases of Cocktails, and Ingredients supported with an emailable report. The testable API is provided in [API Links](https://www.thecocktaildb.com/api.php).

## Configuration
- To run the test cases using any IDE
1. JAVA JDK 17 from [JAVA LINK](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)
2. Any IDE.
3. Maven plugin installed through IDE.
4. TestNG plugin installed through IDE.
5. Clone the current repo using [GIT HUB LINK](https://github.com/AhmedMAnwar/derivco)
6. Run the application as TestNG.

- To run the test cases using command line
1. Clone the current repo [GIT HUB LINK](https://github.com/AhmedMAnwar/derivco)
2. Install Maven from [MAVEN LINK](https://maven.apache.org/download.cgi)
3. Add maven through environment varibales [MAVEN SETUP](https://www.tutorialspoint.com/maven/maven_environment_setup.htm)
4. Through command line, checkout to the repo directroy, write down the following commands
    ```
     maven clean test
    ```
## Emilable Report
- To find the emilable report follow the following path after cloning the repo locally
     ```
     /derivco/test-output/emailable-report.html
    ```
 - Figure to show the report view.
  ![image](https://user-images.githubusercontent.com/60217499/215907520-e70c7204-e863-4e80-bf84-c1b43ca31e41.png)
  
## Non Functional Tests Suggestion

### Test Cases
**_Performance Test:_** To cover the API under performance process, then focus on three aspects Speed, Scalability, Stability by the following test cases
1. Verify response time when 1000 users access Search, and Lookup APIs simultaneously.
2. Verify response time when 1000 users access Search, and Lookup APIs with dealy 2 sec between each request.
3. Verify the response time of Search, and Lookup APIs when the network is slow.
4. Verify the maximum number of invocations for Search, and Lookup APIs to know the system behavior under stress test.
5. Verify the server resources when invoking the APIs for more than one day without delay.

**_Portability Test:_** To cover the API under portability test process. As the API call DB, then the DB is a crucial point in veryfing the API calls.
1. Verify API response after updting the DB driver.
2. Verfiy API response after doing DB migartion from one system to another same system.
3. Verfiy API response after transfering the DB from OS to another OS.
4. Verfiy API response after uploading a huge data into DB.
### Frameworks
- Performance Test
  - JMeter.
  - BlazeMeter.
  - HP LoadRunner.
- Portability Test
  - Can be included with the CI/CD process. For instance, after doing a DB upgrade process through CI/CD process, then the automated testes can be called as job after the success of DB upgrade to test Installability, Adaptability, Replaceability,  and Compatibility. CI/CD tools such as Jenkins, Circle CI/CD, AWS.
