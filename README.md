# Static Methods Aggregator

Based on the historical Java Hamcrest Generator,
re-packaged their legacy code here to help aggregate
a list of classes' public static methods in a single
_Sugar_ class.

Example usage (find the releases in this repo, which includes the `static-methods-agg.jar` file):

```shell
# arguments:
#   1. the folder in which it will look recursively for java classes
#   2. the fully qualified name of the generated sugar class
#   3. the root folder of the java project in which the
#      generated sugar class will be generated
java -jar static-methods-agg.jar ~/myproject/src/test/java/org/example/mypackage org.example.GeneratedSugarClass ~/myproject/src/test/java
```

## Example

Imagine having a list of Java classes like this

```
 org.example
 └── mypackage
     ├── Clazz1.java
     ├── Clazz2.java
     ├── Clazz3.java
     └── ...
```

In each of them, they are statically exposed methods and we don't wish you import them
all.

This is where the sugar class comes in play. The generated class would then look like
the following:

```java
// Generated source.
package org.example;

public class GeneratedSugarClass {

    public static org.example.FullyQualifiedReturnedObject method1(/* list of args with fully qualified types */) {
        return org.example.mypackage.Clazz1.method1(/* delegate the arguments */);
    }

    public static org.example.FullyQualifiedReturnedObject method2(/* list of args with fully qualified types */) {
        return org.example.mypackage.Clazz1.method2(/* delegate the arguments */);
    }

    public static org.example.FullyQualifiedReturnedObject method3(/* list of args with fully qualified types */) {
        return org.example.mypackage.Clazz1.method3(/* delegate the arguments */);
    }

    public static org.example.OtherFullyQualifiedReturnedObject method1(/* list of args with fully qualified types */) {
        return org.example.mypackage.Clazz2.method1(/* delegate the arguments */);
    }

    // ...
}
```
