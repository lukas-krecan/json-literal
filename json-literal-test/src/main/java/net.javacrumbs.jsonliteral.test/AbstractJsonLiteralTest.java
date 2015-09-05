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
        ).isEqualTo("{\n" +
                "   \"one\":true,\n" +
                "   \"two\":{\n" +
                "      \"three\":false\n" +
                "   },\n" +
                "   \"string\":\"value\",\n" +
                "   \"integer\":1,\n" +
                "   \"null\":null,\n" +
                "   \"dbl\":1.1,\n" +
                "   \"flt\":1.0,\n" +
                "   \"arr\":[\n" +
                "      1,\n" +
                "      \"a\",\n" +
                "      3\n" +
                "   ],\n" +
                "   \"arr2\":[\n" +
                "      \"a\",\n" +
                "      \"b\",\n" +
                "      \"c\"\n" +
                "   ],\n" +
                "   \"bd\":1.23,\n" +
                "   \"bi\":1\n" +
                "}\n");
    }

    @Test
    public void shouldCreateArrayOfNodes() {
        JsonFluentAssert.assertThatJson(
                obj(
                        array -> asList(
                                obj(a -> 1),
                                obj(a -> 2)
                        )
                )
        ).isEqualTo("{\"array\": [{\"a\":1}, {\"a\":2}]}");
    }

    protected abstract Object obj(NamedValue<Object>... keyValuePairs);
}