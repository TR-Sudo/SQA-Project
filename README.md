# SQA-Project

## Front-End
```Bash
Steps to run front-end:
1.  cd into TMT-OTBNB/src
2.  javac otbnb/*.java
3.  java otbnb.Server <user file> <rentalunit file> <transaction file>
    1. e.g.,  'java otbnb.Server ../userdemo.txt ../listingsdemo.txt ../dailyTransactionFile.txt'
```


## Back-End
```Bash
Steps to run back-end:
1.  cd into TMT-OTBNB-BackEnd/src
2.  javac *.java
3.  java BackEnd <user file> <rentalunit file> <transaction file>
    1. e.g., `java BackEnd ../userdemo.txt ../listingsdemo.txt ../dailyTransactionFile.txt`
```


## Testing
Steps to run test script:
1. `cd` into `TMT-OTBNB`
2. `./script.sh`
    1. Might have to change permissions first, `chmod a+x ./script.sh` 

Steps to run JUnit tests:
1. `cd` into `TMT-OTBNB/src`
2. Compile all `.java` files
    1. On Linux or macOS `javac -cp .:../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar otbnb/*.java junit_tests/*.java`
    2. On Windows `javac -cp .;../lib/junit-4.13.2.jar;../lib/hamcrest-core-1.3.jar otbnb/*.java junit_tests/*.java`
4. Run tests
    1. On Linux or macOS `java -cp .:../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore junit_tests.<filename>`
    - e.g., `java -cp .:../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore junit_tests.StatementCoverage`
    2. On Windows `java -cp .;../lib/junit-4.13.2.jar;../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore junit_tests.<filename>`
    - e.g., `java -cp .;../lib/junit-4.13.2.jar;../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore junit_tests.StatementCoverage`    
    - If above fails to run, test through vs code 


Steps to run Backend tests:
1. `cd` into `TMT-OTBNB`
    1. for daily `./daily.sh` 
    2. for weekly `./weekly.sh`

## Files
- `userdemo.txt` - contains the user accounts with their username and account type, this is a sample file that is used for testing
- `listingsdemo.txt` - contains the rental units, this is a sample file that is used for testing
- `dailyTransactionFile.txt` - contains the daily transactions made by users once they log out
