package net.javacrumbs.jsonliteral.test;

import net.javacrumbs.jsonliteral.core.lambda.NamedValue;
import net.javacrumbs.jsonunit.fluent.JsonFluentAssert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

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
                        $null -> null,
                        dbl -> 1.1,
                        flt -> 1.0f,
                        arr -> asList(1, "a", 3),
                        arr2 -> new String[]{"a", "b", "c"},
                        bd -> new BigDecimal("1.23"),
                        bi -> new BigInteger("1")
                )
        ).isEqualTo("{\"one\": true, \"two\": {\"three\":false}, \"string\":\"value\", \"integer\":1, \"null\": null, \"dbl\": 1.1, " +
                "\"flt\":1.0, \"arr\":[1, \"a\", 3], \"arr2\": [\"a\", \"b\", \"c\"], \"bd\": 1.23, \"bi\":1}");
    }

    protected abstract Object obj(NamedValue<Object>... keyValuePairs);
}