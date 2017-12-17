#/bin/bash

rm -rf build/
mkdir build/

javac src/jhttp/JhttpApplication.java -cp src -d build/
