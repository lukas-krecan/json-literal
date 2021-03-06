**This was an experiment, which unfortunatelly does not work in Java 9 and higher!!!**


# json-literal
A library for writing JSON in literal-like style based on Benji Weber's [Lambda Type References](https://github.com/benjiman/lambda-type-references).
You need to compile with Java 8u60 or newer and javac `-parameters` for parameter reflection to work.

**This project is abusing lambdas in a way that probably is not a good idea, use at your own risk.**

# Usage

Import Maven project depending on your implementation (Jackson 2 in this example, but modules for Gson and Jackson 1 are provided too)

    <dependency>
        <groupId>net.javacrumbs</groupId>
        <artifactId>json-literal-jackson2</artifactId>
        <version>0.1.0</version>
    </dependency>

And enjoy

    import static net.javacrumbs.jsonliteral.jackson2.JsonLiteral.*;

    ...

    JsonNode node =
        obj(
            one -> true,
            two -> obj(
                three -> false
            ),
            string -> "value",
            integer -> 1,
            $null -> null,
            $double -> 1.1,
            $float -> 1.0f,
            array -> asList(1, "a", 3),
            array2 -> array("a", "b", "c") // you can use array method
        );

Results in

    {
      "one" : true,
      "two" : {
        "three" : false
      },
      "string" : "value",
      "integer" : 1,
      "null" : null,
      "double" : 1.1,
      "float" : 1.0,
      "array" : [ 1, "a", 3 ],
      "array2" : [ "a", "b", "c" ]
    }

Please note that you can use only valid Java identifier for key value. So you can not use `null`, `for`
or any other keyword, not to mention funny characters. Fot the time being, you can prepend the name by $,
the library will automatically strip it and use the rest of the string. We will need more sophisticated
mechanism in the future.

Please let me know what you think about the library.


