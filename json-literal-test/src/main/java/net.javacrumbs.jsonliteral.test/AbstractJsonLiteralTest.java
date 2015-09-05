package net.javacrumbs.jsonliteral.test;

import net.javacrumbs.jacksonliteral.core.lambda.NamedValue;
import net.javacrumbs.jsonunit.fluent.JsonFluentAssert;
import org.junit.Test;

import static java.util.Arrays.asList;

public abstract class AbstractJsonLiteralTest {
    @Test
    public void shouldCreateNode() {
        JsonFluentAssert.assertThatJson(
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
                )
        ).isEqualTo("{\"one\": true, \"two\": {\"three\":false}, \"string\":\"value\", \"integer\":1, \"nothing\": null, \"dbl\": 1.1, " +
                "\"flt\":1.0, \"arr\":[1, \"a\", 3], \"arr2\": [\"a\", \"b\", \"c\"]}");
    }

    protected abstract Object obj(NamedValue<Object>... keyValuePairs);
}