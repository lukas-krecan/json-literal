package net.javacrumbs.jsonliteral.jackson1;

import net.javacrumbs.jsonliteral.core.KeyValue;
import net.javacrumbs.jsonliteral.test.AbstractJsonLiteralTest;


public class JsonLiteralTest extends AbstractJsonLiteralTest {
    @Override
    protected final Object obj(KeyValue... keyValuePairs) {
        return JsonLiteral.obj(keyValuePairs);
    }

    @Override
    protected Object array(Object... values) {
        return JsonLiteral.array(values);
    }
}