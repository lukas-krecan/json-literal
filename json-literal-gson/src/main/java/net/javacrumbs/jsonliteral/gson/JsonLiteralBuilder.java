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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.javacrumbs.jsonliteral.core.AbstractJsonLiteralBuilder;
import net.javacrumbs.jsonliteral.core.NameTranslator;

/**
 * Builds JSON literal using Gson. You can use this class for customization.
 */
public final class JsonLiteralBuilder extends AbstractJsonLiteralBuilder<JsonObject> {

    private final Gson gson;

    public JsonLiteralBuilder(NameTranslator nameTranslator, Gson gson) {
        super(nameTranslator);
        this.gson = gson;
    }

    @Override
    protected final void put(JsonObject node, String name, Object value) {
        node.add(name, convertValueToNode(value));
    }

    @Override
    protected final JsonObject createNode() {
        return new JsonObject();
    }

    public final JsonArray array(Object... values) {
        return toArray(this::convertValueToNode, JsonArray::new, JsonArray::add, JsonArray::addAll, values);
    }

    private JsonElement convertValueToNode(Object value) {
        return gson.toJsonTree(value);
    }
}
