# json-literal
A library for writing JSON in literal-like style based on Benji Weber's [Lambda Type References](https://github.com/benjiman/lambda-type-references)

# Usage

Import Maven project depending oun your implementation (Jackson 2 in this example)

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
                    nothing -> null,
                    dbl -> 1.1,
                    flt -> 1.0,
                    arr -> asList(1, "a", 3),
                    arr2 -> new String[]{"a", "b", "c"}
            );

    System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(node));

Prints

    {
      "one" : true,
      "two" : {
        "three" : false
      },
      "string" : "value",
      "integer" : 1,
      "nothing" : null,
      "dbl" : 1.1,
      "flt" : 1.0,
      "arr" : [ 1, "a", 3 ],
      "arr2" : [ "a", "b", "c" ]
    }


TODO:

- Support for key renaming so we can use key that are not Java identifiers


