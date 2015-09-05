package net.javacrumbs.jsonliteral.core;

import org.junit.Test;

import static net.javacrumbs.jsonliteral.core.JsonLiteral.obj;
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
                        dbl -> 1.1
                )
        ).isEqualTo("{\"one\": true, \"two\": {\"three\":false}, \"string\":\"value\", \"integer\":1, \"nothing\": null, \"dbl\": 1.1}");
    }

}