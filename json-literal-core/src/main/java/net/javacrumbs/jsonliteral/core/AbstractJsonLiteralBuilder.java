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
package net.javacrumbs.jsonliteral.core;

import net.javacrumbs.lambdaextractor.ParameterNameExtractor;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.stream;

public abstract class AbstractJsonLiteralBuilder<T> {
    private final NameTranslator nameTranslator;

    protected AbstractJsonLiteralBuilder(NameTranslator nameTranslator) {
        this.nameTranslator = nameTranslator;
    }

    public final T obj(KeyValue... keyValuePairs) {
        T node = createNode();
        Arrays.stream(keyValuePairs)
            .forEach(kvp -> {
                String name = ParameterNameExtractor.extractLastParameterName(kvp);
                put(node, translateName(name), kvp.apply(name));
            });
        return node;
    }

    protected final <R, S> S toArray(Function<Object, R> conversion, Supplier<S> arraySupplier, BiConsumer<S, R> accumulator,
                                BiConsumer<S, S> combiner, Object... values) {
        return stream(values)
            .map(conversion)
            .collect(arraySupplier, accumulator, combiner);
    }

    private String translateName(String name) {
        return nameTranslator.translate(name);
    }

    protected abstract void put(T node, String name, Object value);

    protected abstract T createNode();
}
