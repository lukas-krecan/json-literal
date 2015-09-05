/**
 * Copyright 2009-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.javacrumbs.jsonliteral.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.javacrumbs.jsonliteral.core.NameTranslator;
import net.javacrumbs.jsonliteral.core.AbstractJsonLiteralBuilder;

import static java.util.Arrays.asList;

public final class JsonLiteralBuilder extends AbstractJsonLiteralBuilder<JsonObject> {
    public JsonLiteralBuilder(NameTranslator nameTranslator) {
        super(nameTranslator);
    }

    @Override
    protected final void put(JsonObject node, String name, Object value) {
        node.add(name, convertValueToNode(value));
    }

    @Override
    protected final JsonObject createNode() {
        return new JsonObject();
    }

    public final JsonArray array(Object[] values) {
        return asList(values).stream().map(this::convertValueToNode)
                .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);
    }

    private JsonElement convertValueToNode(Object value) {
        if (value instanceof Boolean) {
            return new JsonPrimitive((Boolean) value);
        } else if (value instanceof JsonElement) {
            return (JsonElement) value;
        } else if (value instanceof String) {
            return new JsonPrimitive((String) value);
        } else if (value instanceof Number) {
            return new JsonPrimitive((Number) value);
        } else if (value instanceof Iterable) {
            return serializeIterable((Iterable<?>) value);
        } else if (value instanceof Object[]) {
            return serializeIterable(asList((Object[]) value));
        } else if (value == null) {
            return JsonNull.INSTANCE;
        } else {
            throw new IllegalArgumentException("Can not serialize type " + value.getClass());
        }
    }

    private JsonElement serializeIterable(Iterable<?> value) {
        JsonArray arrayNode = new JsonArray();
        for (Object a : value) {
            arrayNode.add(convertValueToNode(a));
        }
        return arrayNode;
    }
}
