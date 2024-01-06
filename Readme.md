# Parity Games
Requirements:
1. Java 21
2. Maven

To run with maven:
``mvn exec:java -Dexec.mainClass="org.tue.ParityGame" -Dexec.args="./ccp orderedNodes"``

To build a jar with all the dependencies:
``mvn clean package``

To run the program using jar:
``java -jar ./ccp orderedNodes``