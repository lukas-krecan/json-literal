package net.javacrumbs.jsonliteral.jackson2;

import net.javacrumbs.jacksonliteral.core.lambda.NamedValue;
import net.javacrumbs.jsonliteral.jackson2.JsonLiteral;
import net.javacrumbs.jsonliteral.test.AbstractJsonLiteralTest;


public class JsonLiteralTest extends AbstractJsonLiteralTest {

    @Override
    @SafeVarargs
    protected final Object obj(NamedValue<Object>... keyValuePairs) {
        return JsonLiteral.obj(keyValuePairs);
    }
}