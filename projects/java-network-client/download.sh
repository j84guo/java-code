#/bin/bash

cd build/
java jhttp.JhttpApplication -cp build/ -o test.jar http://central.maven.org/maven2/org/apache/spark/spark-core_2.11/2.2.1/spark-core_2.11-2.2.1.jar
cd ..
