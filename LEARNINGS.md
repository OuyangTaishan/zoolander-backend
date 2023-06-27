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

### H2 limits the size of table cells, so how to overcome it?
```java
@Lob // large object
@Column(length = 1000)
```

### Verknüpfung von Posts und Comments
(Im folgenden Block sind Java-Klassen kursiv, Java-Annotationen fett und Java-Variablen als Code-Schnipsel hervorgehoben.)
- ein *Post* kann mehrere *Comment*s enthalten
- deshalb muss es in der *Post* class ein Feld `List<Comment>` geben, das mit **@OneToMany** annotiert ist
- in der *Comment* class muss es entsprechend ein Feld `Post` geben, damit mit **@ManyToOne** annotiert ist
- ohne diese Annotation kommt es zu einem compiling error: 'Basic' attribute type should not be 'Persistence Entity'
- mit dieser Annotation kann jedoch nicht ohne Weiteres ein JSON-Objekt serialisiert werden
- das liegt daran, dass in einem *Post* Objekt alle *Comment*s gelistet sind, in denen dann wiederum, der *Post* gelistet wäre, in dem dann wieder die *Comment*s gelistet wären...
- um diese Endlosschleife zu verhindern, braucht es über der *Comment* class eine **@JsonIgnoreProperties("post")** Annotation
- (wenn mehrere Felder ignoriert werden sollen, werden die Variablennamen in geschweiften Klammern mit Kommas getrennt: **@JsonIgnoreProperties("id", "post")**)
- die Annotation lässt sich nicht direkt über das Feld schreiben, sondern muss über der Klasse stehen

### Anelgen von Kommentaren über die *CommentRepo*
- runtime error: BeanCreationException
- einfach im entsprechenden *Post* mit `Post.getComments().add(comment)` anhängen.
- dann muss der *Post* aber hinterher mit `postRepo.save(post)` gespeichert werden
- das funktioniert nicht, wenn der *Post* schon gespeichert war - keine Ahnung wie man hier updaten soll

- entsprechend ist mir auch noch unklar wie man im Angular einen neuen *Comment* schreibt, bei dem `User author` und `Post post` direkt verknüpft sind
- geht das über @Injection?
- der `User author` müsste sich über den `sessionService` regeln lasssen, von dem Patrick vorhin gesprochen hat (oder ein ähnlicher Weg ohne zusätzliche *Component*)
