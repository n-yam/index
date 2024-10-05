# index

## Compile
```
rm -fr build
javac src/main/java/index/Main.java -cp src/main/java -d build/classes
jar -cvfe build/index.jar index.Main -C build/classes . -C src/main/resources .
```

## Run
```
java -jar build/index.jar
```

## Docker Build
```
build -t index .
```

## Docker Run
```
docker run -p 8000:8000 --rm --name index index
```