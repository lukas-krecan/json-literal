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
package net.javacrumbs.jsonliteral.jackson1;

import net.javacrumbs.jacksonliteral.core.lambda.NamedValue;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.BooleanNode;
import org.codehaus.jackson.node.DoubleNode;
import org.codehaus.jackson.node.IntNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.LongNode;
import org.codehaus.jackson.node.NullNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.TextNode;

import static java.util.Arrays.asList;

public class JsonLiteral {
    private static JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

    @SafeVarargs
    public static JsonNode obj(NamedValue<Object>... keyValuePairs) {
        ObjectNode node = nodeFactory.objectNode();
        asList(keyValuePairs)
                .stream()
                .forEach(kvp -> put(node, kvp));
        return node;
    }

    private static void put(ObjectNode node, NamedValue<Object> kvp) {
        node.put(kvp.name(), convertValueToNode(kvp.value()));
    }

    private static JsonNode convertValueToNode(Object value) {
        if (value instanceof Boolean) {
            return BooleanNode.valueOf((Boolean) value);
        } else if (value instanceof JsonNode) {
            return (JsonNode) value;
        } else if (value instanceof String) {
            return TextNode.valueOf((String) value);
        } else if (value instanceof Integer) {
            return IntNode.valueOf((Integer) value);
        } else if (value instanceof Long) {
            return LongNode.valueOf((Long) value);
        } else if (value instanceof Double) {
            return DoubleNode.valueOf((Double) value);
        } else if (value instanceof Iterable) {
            return serializeIterable((Iterable<?>) value);
        } else if (value instanceof Object[]) {
            return serializeIterable(asList((Object[]) value));
        } else if (value == null) {
            return NullNode.getInstance();
        } else {
            throw new IllegalArgumentException("Can not serialize type " + value.getClass());
        }
    }

    private static JsonNode serializeIterable(Iterable<?> value) {
        ArrayNode arrayNode = new ArrayNode(nodeFactory);
        for (Object a : value) {
            arrayNode.add(convertValueToNode(a));
        }
        return arrayNode;
    }
}
