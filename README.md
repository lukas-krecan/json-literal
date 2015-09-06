# json-literal
A library for writing JSON in literal-like style based on Benji Weber's [Lambda Type References](https://github.com/benjiman/lambda-type-references).
You need to compile with Java 8u60 or newer and javac `-parameters` for parameter reflection to work.

# Usage

Import Maven project depending on your implementation (Jackson 2 in this example, but modules for Gson and Jackson 1 are provided too)

    <dependency>
        <groupId>net.javacrumbs</groupId>
        <artifactId>json-literal-jackson2</artifactId>
        <version>0.1.0</version>
    </dependency>

And enjoy

    import static net.javacrumbs.jsonliteral.jackson2.JsonLiteral.obj;

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
                            dbl -> 1.1,
                            flt -> 1.0,
                            arr -> asList(1, "a", 3),
                            arr2 -> array("a", "b", "c") // or you can use array factory method
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
      "dbl" : 1.1,
      "flt" : 1.0,
      "arr" : [ 1, "a", 3 ],
      "arr2" : [ "a", "b", "c" ]
    }

Please note that you can use only valid Java identifier for key value. So you can not use `null`, `for`
or any other keyword, not to mention funny characters. Fot the time being, you can prepend the name by $,
the library will automatically strip it and use the rest of the string. We will need more sophisticated
mechanism in the future.

Please let me know what you think about the library.


