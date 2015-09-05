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

import net.javacrumbs.jsonliteral.core.KeyValue;
import net.javacrumbs.jsonliteral.core.NameTranslator;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public class JsonLiteral {
    private static final JsonLiteralBuilder defaultBuilder = new JsonLiteralBuilder(NameTranslator.DEFAULT_TRANSLATOR, JsonNodeFactory.instance);

    /**
     * Creates JsonObject. Use like this
     * <code>
     * <pre>
     *     json = obj(
     *           one -> true,
     *           two -> obj(
     *                   three -> false
     *            ),
     *           string -> "value",
     *           integer -> 1,
     *           $null -> null,
     *           dbl -> 1.1,
     *           flt -> 1.0,
     *           arr -> asList(1, "a", 3),
     *           arr2 -> new String[]{"a", "b", "c"}
     *    );
     *   </pre>
     * </code>
     *
     * @param keyValuePairs
     * @return
     */
    public static ObjectNode obj(KeyValue... keyValuePairs) {
        return defaultBuilder.obj(keyValuePairs);
    }

    public static ArrayNode array(Object... values) {
        return defaultBuilder.array(values);
    }
}
