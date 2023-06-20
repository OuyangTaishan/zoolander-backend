# LEARNINGS
## REST
### How to pull JSON data from a web api:
In your @RestController class, have a method annotated with

```java
@RequestMapping('/api/whatever')
@RequestBody
private String getData() {
String uri = "https://homepage.com/data";

        // A RestTemplate takes a URI and Obj.class and returns an instance of Obj.
        // It does so by opening the URI, taking the JSON data it finds there,
        // and filling the constructor of Obj via the class's getter methods.
        // If a getter method is missing, it returns an instance which lacks that field attribute.
        RestTemplate restTemplate = new RestTemplate();
        Obj obj = restTemplate.getForObject(uri, Obj.class);
        objRepository.save(obj);

        return "" + obj.getId();
    }
```
