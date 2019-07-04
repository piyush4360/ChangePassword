# ChangePassword
This repo consists of Java code that allows user to change their existing password. Their are several restrictions however while entering the new password just to ensure a robust security of user's credentials.

The project is built using maven and uses TestNg as the unit testing framework for automating tests.
The project structure is pretty staight forward as follows

* **src/main/java** is the folder where all the business logic resides.
* **com.demo.password** is the package under which all the business logic resides.
* **ChangePassword.java** consists of business logic to change the password
* **PasswordRules.java** consists of logic to validate new password against a set of rules
* **ChangePassword_Test.java** has all the automated test cases for testing the Change password functionality. Test cases are built using TestNg fwk.
* **config.properties** contains the user's original password in key value pair

Basic workflow of the design is as follows:

1. changePassword(oldPassword, newPassword) function is called with oldPassword and newPassword from the TestNg test cases.
2. Initial verification happens for oldPassword to check its validity.
3. If oldPassword is valid, check the new Password for all the password rules. Currently the rules set on new password validation are as follows:
    - Password length should be greater than 18 characters
    - New password cannot match exactly to old password (**Additional rule provided**)
    - Password should have atleast one uppercase character, one lowercase, one special and one digit
    - No more than 4 repeat characters allowed
    - No more than 4 special characters are allowed
    - Digits cannot be equal to or greater than 50% of password length
    - New Password should not contain more than 80% of old password.
4. Individual error messages to be displayed for all above rules if violated.
5. Once password is validated for above rules, change the password and replace the oldPassword with newPassword in config.properties
6. Next time user changes password, the oldPassword would be the newly created one in step 4 above.

#### Verify Old password ###

For verifying the old password, we have created a dummy mock which would set and get the users old password from the config.properties file. The reason to use config.properties is it a simple solution to mocking an actual DB. Everytime a password is changed, the **userPassword** key is updated with new Password value. Next time read of this property fetches the current value set.

### Automation Test Cases ###

**ChangePassword_Test.java** has all our automated tests. This file is a Testng file and all test cases are annotated with @Test. 
For reading and writing to config.properties, file operations have to be done which is taken care by @BeforeClass and @AfterMethod.
@BeforeClass would initialize ChangePassword class once and @AfterMethod has code to set the user's old password to original value, otherwise next time the tests would run, the first test case would fail.

### Execution And Reporting ###

We have leveraged the user of Maven as our build tool. All the dependencies would be runtime downloaded and tests would run. Maven pom.xml points to testng.xml file which in turn would execute all are testng cases.

### Email reports ###

We have also provided an additnal feature to email the report to the respective stakeholders once the test execution is completed. The email would contain a static html report which would describe which all tests have passed and failed. Pretty basic for now :)

## How to Execute ##

Pre-req: Maven should be installed and set in env variable (mvn)

1. git clone <this_repo>
2. cd ChangePassword
3. Open pom.xml and just change the value of <receiver> email in maven-postman-plugin to any of your choice. I have created a new gmail account **charlie.tango436@gmail.com** that can be used for testing purpose by anyone. 
4. Run following command : **mvn surefire-report:report**
5. It would install all dependencies, build project, run automation cases, generate report and email the report to the receiver's mentioned in step 3. (Note: Just ensure that smtp port is open on your machine, otherwise mails would not be sent)






