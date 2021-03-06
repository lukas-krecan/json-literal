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
package net.javacrumbs.jsonliteral.jackson1;

import net.javacrumbs.jsonliteral.core.AbstractJsonLiteralBuilder;
import net.javacrumbs.jsonliteral.core.NameTranslator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Builds JSON literal using Jackson 1. You can use this class for customization.
 */
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
        return toArray(this::convertValueToNode, () -> new ArrayNode(objectMapper.getNodeFactory()), ArrayNode::add, ArrayNode::addAll, values);
    }

    private JsonNode convertValueToNode(Object value) {
        return objectMapper.convertValue(value, JsonNode.class);
    }
}
