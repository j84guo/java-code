#!/bin/bash

# assumes libs/Exec1.jar exists
mkdir -p out

# compile package2, using classes from Exec1.jar
javac -cp src/main/java:libs/Exec1.jar src/main/java/package2/Main.java -d out

# test run
java -cp libs/Exec1.jar:out package2.Main

# to package a fat jar, we need to extract the libary classes into out/ since
# java -jar does not read inner packaged jar files
cp libs/Exec1.jar out
cd out
tar -xvf Exec1.jar
rm Exec1.jar
cd ..

# create fat jar
jar -cvfm Exec2.jar src/main/resources/META-INF/MANIFEST.MF -C out .
