# index

## Compile
```
javac src/main/java/index/Main.java -cp src/main/java -d build/classes
jar -cvfe build/index.jar index.Main -C build/classes . -C src/main/resources .
```

## Run
```
java -jar build/index.jar
```