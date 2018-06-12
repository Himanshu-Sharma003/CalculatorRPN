ECHO OFF
setlocal EnableExtensions EnableDelayedExpansion

set location=%CD%

echo %location%

if not exist %location%\classes mkdir %location%\classes
cd src/main/java
javac -d %location%\classes  com\calculator\commons\*.java

javac -d %location%\classes com\calculator\cache\ICache.java

javac -d %location%\classes -classpath %location%\classes com\calculator\cache\impl\*.java

javac  -Xlint:none -d %location%\classes -classpath %location%\classes com\calculator\cache\CacheManager.java

javac -d %location%\classes -classpath %location%\classes com\calculator\commons\exception\*.java

javac -d %location%\classes -classpath %location%\classes com\calculator\operator\*.java

javac -d %location%\classes -classpath %location%\classes com\calculator\operator\impl\*.java

javac -d %location%\classes -classpath %location%\classes com\calculator\RPNCalculator.java

cd ../resources
copy /y *.properties %location%\classes

javac -d %location%\classes  ../../test/java/com/calculator/commons/*.java

javac -d %location%\classes  -classpath %location%\classes  ../../test/java/com/calculator/test/*.java

javac -d %location%\classes  -classpath %location%\classes ../../test/java/com/calculator/*.java

set /p "userInput=Do you want to execute test cases (Y/N)?"

IF "%userInput%"== "y" set  true=1
IF "%userInput%"== "Y"  set true=1
IF defined TRUE (
	echo "##########################################################"
	echo "Executing test  scenario"
	java -classpath %location%\classes com/calculator/TestExecutor
	echo "##########################################################"
) 

cd ../java
java -classpath %location%\classes com/calculator/RPNCalculator
pause
