# compiled classes
mkdir -p out

# compile Main class, setting classpath so that other classes can be found
javac -cp src/main/java src/main/java/package1/Main.java -d out

# create executable jar
jar -cvfm Exec1.jar src/main/resources/META-INF/MANIFEST.MF -C out/ . 



