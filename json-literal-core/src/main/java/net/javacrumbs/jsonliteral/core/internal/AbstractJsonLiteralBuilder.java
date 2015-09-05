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
package net.javacrumbs.jsonliteral.core.internal;

import net.javacrumbs.jsonliteral.core.NameTranslator;
import net.javacrumbs.jsonliteral.core.lambda.NamedValue;

import static java.util.Arrays.asList;

public abstract class AbstractJsonLiteralBuilder<T> {
    private final NameTranslator nameTranslator;

    protected AbstractJsonLiteralBuilder(NameTranslator nameTranslator) {
        this.nameTranslator = nameTranslator;
    }

    @SafeVarargs
    public final T obj(NamedValue<Object>... keyValuePairs) {
        T node = createNode();
        asList(keyValuePairs)
                .stream()
                .forEach(kvp -> put(node, translateName(kvp), kvp.value()));
        return node;
    }

    private String translateName(NamedValue<Object> kvp) {
        return nameTranslator.translate(kvp.name());
    }

    protected abstract void put(T node, String name, Object value);

    protected abstract T createNode();
}
