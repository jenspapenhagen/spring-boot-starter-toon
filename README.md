# Spring Boot Starter Toon

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.giovannicaggianella/spring-boot-starter-toon.svg)](https://search.maven.org/search?q=g:io.github.giovannicaggianella%20AND%20a:spring-boot-starter-toon)

Spring Boot Starter for TOON format serialization/deserialization using JToon.

## 🚀 Features

- Seamless Spring Boot integration for TOON serialization/deserialization
- Custom annotation support
- Automatic configuration with Spring Boot Auto-Configuration
- Java 14+ compatible

## 📦 Installation

Add the following dependency to your Maven project:

```xml
<dependency>
    <groupId>io.github.giovannicaggianella</groupId>
    <artifactId>spring-boot-starter-toon</artifactId>
    <version>0.2.0</version>
</dependency>
```

## 🛠️ Usage

### 1. Annotate your class with `@ToonEntity`

```java
import io.github.giovannicaggianella.toon.annotation.ToonEntity;

@ToonEntity
public class User {
    private String name;
    private int age;
    
    // Constructors, getters, and setters
}
```

### 2. Use `ToonAnnotationProcessor` for serialization/deserialization

```java
import io.github.giovannicaggianella.toon.processor.ToonAnnotationProcessor;

// In a Spring component
@Autowired
private ToonAnnotationProcessor toonProcessor;

// Serialization
User user = new User("John", 30);
String toonString = toonProcessor.encode(user);

// Deserialization
User deserializedUser = toonProcessor.decode(toonString, User.class);
```

### 3. Available Annotations

#### @ToonEntity
Marks a class as serializable to TOON format.

#### @ToonRequest
Use in Spring controllers to automatically deserialize request bodies in TOON format.

#### @ToonResponse
Use in Spring controllers to automatically serialize responses to TOON format.

## 🌟 TOON Format Examples

Here are some examples of the TOON format and how to work with it using this starter:

### Basic Object
```java
@ToonEntity
public class User {
    private int id = 123;
    private String name = "Ada";
    private boolean active = true;
    // getters and setters
}
```

TOON Output:
```
id: 123
name: Ada
active: true
```

### Nested Objects
```java
@ToonEntity
public class Order {
    private String orderId = "ORD-123";
    private User customer = new User(123, "Ada");
    // getters and setters
}
```

TOON Output:
```
orderId: ORD-123
customer:
  id: 123
  name: Ada
  active: true
```

### Arrays
```java
@ToonEntity
public class ShoppingCart {
    private String[] tags = {"urgent", "gift"};
    private List<Item> items = List.of(
        new Item("A1", 2, 9.99),
        new Item("B2", 1, 14.50)
    );
    // getters and setters
}
```

TOON Output:
```
tags[2]: urgent,gift
items[2]{sku,qty,price}:
  A1,2,9.99
  B2,1,14.5
```

### Using Custom Delimiters
```java
ToonAnnotationProcessor processor = new ToonAnnotationProcessor();
EncodeOptions options = new EncodeOptions(2, Delimiter.PIPE, false);
String toon = processor.encode(new ShoppingCart(), options);
```

TOON Output with Pipe Delimiter:
```
tags[2|]: urgent|gift
items[2|]{sku|qty|price}:
  A1|2|9.99
  B2|1|14.5
```

## ⚙️ Configuration

Default configuration can be customized via `application.properties`:

```properties
# Enable/disable auto-configuration (true by default)
toon.enabled=true
```

## 📚 JToon Documentation

For more information about TOON format and JToon library, check the [official documentation](https://github.com/felipestanzani/JToon).

## 📄 License

This project is licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0).

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

## 👤 Author

Giovanni Caggianella - [@giovannicaggianella](https://github.com/GiovanniCaggianella)
