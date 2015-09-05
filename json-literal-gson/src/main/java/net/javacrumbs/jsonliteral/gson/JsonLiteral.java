/**
 * Copyright 2009-2014 the original author or authors.
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
import net.javacrumbs.jacksonliteral.core.lambda.NamedValue;

import static java.util.Arrays.asList;

public class JsonLiteral {

    @SafeVarargs
    public static JsonObject obj(NamedValue<Object>... keyValuePairs) {
        JsonObject node = new JsonObject();
        asList(keyValuePairs)
                .stream()
                .forEach(kvp -> put(node, kvp));
        return node;
    }

    private static void put(JsonObject node, NamedValue<Object> kvp) {
        node.add(kvp.name(), convertValueToNode(kvp.value()));
    }

    private static JsonElement convertValueToNode(Object value) {
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

    private static JsonElement serializeIterable(Iterable<?> value) {
        JsonArray arrayNode = new JsonArray();
        for (Object a : value) {
            arrayNode.add(convertValueToNode(a));
        }
        return arrayNode;
    }
}
