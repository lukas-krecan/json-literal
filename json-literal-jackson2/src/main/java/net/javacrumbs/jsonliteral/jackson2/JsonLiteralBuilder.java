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
package net.javacrumbs.jsonliteral.jackson2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.javacrumbs.jsonliteral.core.AbstractJsonLiteralBuilder;
import net.javacrumbs.jsonliteral.core.NameTranslator;

import java.util.stream.Stream;

import static java.util.Arrays.stream;

public final class JsonLiteralBuilder extends AbstractJsonLiteralBuilder<ObjectNode> {
    private final ObjectMapper objectMapper;

    public JsonLiteralBuilder(NameTranslator nameTranslator, ObjectMapper objectMapper) {
        super(nameTranslator);
        this.objectMapper = objectMapper;
    }

    @Override
    protected final void put(ObjectNode node, String name, Object value) {
        node.put(name, convertValueToNode(value));
    }

    @Override
    protected final ObjectNode createNode() {
        return new ObjectNode(objectMapper.getNodeFactory());
    }

    public final ArrayNode array(Object... values) {
        return arrayFromStream(stream(values));
    }

    private ArrayNode arrayFromStream(Stream<?> values) {
        return values
                .map(this::convertValueToNode)
                .collect(() -> new ArrayNode(objectMapper.getNodeFactory()), ArrayNode::add, ArrayNode::addAll);
    }

    private JsonNode convertValueToNode(Object value) {
        return objectMapper.convertValue(value, JsonNode.class);
    }
}
