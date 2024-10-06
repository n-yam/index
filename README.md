# index

## Build & Run
```
build -t index .
docker run -p 8000:8000 --rm --name index index
```

## Web API
```
curl -i -XGET localhost:8000/api/flashcards
curl -i -XPOST localhost:8000/api/flashcards -F frontText=HELLO -F backText=WORLD
```