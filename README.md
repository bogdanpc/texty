# Text builder using Java Fluent API

Java project

Implement a text builder with lines, labels to explore Fluent APIs 

## How to use it


```java
    Texty
        .newBuilder()
        .line("My list").endLine()
        .newLine()
        .line(List.of("red", "blue", "orange")).label("Cities").endsWith(LineBuilder.SEMI_COLON).endLine()
        .build();
```

Text result
```text
Hello,

Colors: red, blue, orange;
```

Line with label (always at start of line. Ends with a colon `:` and space. i.e. `Location: `)

```Java
Texty.newBuilder().line("My home town").label("Location").endLine().build();
```

Text result
```text
Location: My home town
```

Use `endsWith(...)` to add a punctuation on end of the line
Add a semi-colon to end of line

```Java
Texty.newBuilder().line("My home town").label("Location").endsWith(';').endLine().build();
```

```Java
Texty.newBuilder().line("My home town").label("Location").endsWith(LineBuilder.SEMI_COLON).endLine().build();
```

```text
Location: My home town;
```

## Continuous testing

Quarkus supports [continuous testing](https://quarkus.io/guides/continuous-testing)
To use it on a non Quarkus project, I created a maven profile `test`. See this blog post https://blog.sebastian-daschner.com/entries/quarkus-dev-for-java-projects

To run it
```shell
mvn quarkus:dev -Ptest
```