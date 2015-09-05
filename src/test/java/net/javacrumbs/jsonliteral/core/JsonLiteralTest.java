package net.javacrumbs.jsonliteral.core;

import org.junit.Test;

import static java.util.Arrays.asList;
import static net.javacrumbs.jsonliteral.core.JsonLiteral.*;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

public class JsonLiteralTest {

    @Test
    public void shouldCreateNode() {
        assertThatJson(
                obj(
                        one -> true,
                        two -> obj(
                                three -> false
                        ),
                        string -> "value",
                        integer -> 1,
                        nothing -> null,
                        dbl -> 1.1f,
                        arr -> asList(1, "a", 3)
                )
        ).isEqualTo("{\"one\": true, \"two\": {\"three\":false}, \"string\":\"value\", \"integer\":1, \"nothing\": null, \"dbl\": 1.1, " +
                "\"arr\":[1, \"a\", 3]}");
    }

}