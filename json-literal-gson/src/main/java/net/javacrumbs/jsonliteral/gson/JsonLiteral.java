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
import com.google.gson.JsonObject;
import net.javacrumbs.jsonliteral.core.KeyValue;
import net.javacrumbs.jsonliteral.core.NameTranslator;

public class JsonLiteral {

    private static final Gson defaultGson = new Gson();
    private static final JsonLiteralBuilder defaultBuilder = new JsonLiteralBuilder(NameTranslator.DEFAULT_TRANSLATOR, defaultGson);

    /**
     * Sets the Gson instance used by the builder to construct JsonArray/Objects from literals.
     * Useful when you want to modify the configuration options of the Gson instance.
     * <code>
     *     JsonLiteral.setGson(new GsonBuilder().serializeNulls().create());
     * </code>
     * @param gson
     */
    public void setGson(Gson gson) {
        JsonLiteral.defaultGson = gson;
        JsonLiteral.defaultBuilder = new JsonLiteralBuilder(NameTranslator.DEFAULT_TRANSLATOR, gson);
    }

    /**
     * Creates JsonObject. Use like this
     * <pre>
     *   <code>
     *     json = obj(
     *           one -&gt; true,
     *           two -&gt; obj(
     *                   three -&gt; false
     *            ),
     *           string -&gt; "value",
     *           integer -&gt; 1,
     *           $null -&gt; null,
     *           dbl -&gt; 1.1,
     *           flt -&gt; 1.0,
     *           arr -&gt; asList(1, "a", 3),
     *           arr2 -&gt; new String[]{"a", "b", "c"}
     *    );
     *  </code>
     * </pre>
     *
     * @param keyValuePairs
     * @return
     */
    public static JsonObject obj(KeyValue... keyValuePairs) {
        return defaultBuilder.obj(keyValuePairs);
    }

    public static JsonArray array(Object... values) {
        return defaultBuilder.array(values);
    }
}
