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
package net.javacrumbs.jsonliteral.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.javacrumbs.jsonliteral.core.lambda.NamedValue;

import static java.util.Arrays.asList;

public class JsonLiteral {
    @SafeVarargs
    public static JsonNode obj(NamedValue<Object>... keyValuePairs) {
        ObjectNode node = new ObjectNode(new ObjectMapper().getNodeFactory());
        asList(keyValuePairs)
                .stream()
                .forEach(kvp -> put(node, kvp));
        return node;
    }

    private static void put(ObjectNode node, NamedValue<Object> kvp) {
        Object value = kvp.value();
        if (value instanceof Boolean) {
            node.put(kvp.name(), (Boolean) value);
        } else if (value instanceof JsonNode) {
            node.put(kvp.name(), (JsonNode) value);
        } else if (value instanceof String) {
            node.put(kvp.name(), (String) value);
        } else if (value instanceof Integer) {
            node.put(kvp.name(), (Integer) value);
        }else if (value instanceof Long) {
            node.put(kvp.name(), (Long) value);
        } else if (value instanceof Double) {
            node.put(kvp.name(), (Double) value);
        } else if (value == null) {
            node.putNull(kvp.name());
        } else {
            throw new IllegalArgumentException("Can not serialize type " + value.getClass());
        }
    }
}
