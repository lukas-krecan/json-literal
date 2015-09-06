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

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

import static java.util.Arrays.asList;

/**
 * Extracts parameter name from lambda. Inspired by
 * https://github.com/benjiman/lambda-type-references/blob/master/src/main/java/com/benjiweber/typeref/MethodFinder.java
 */
class NameExtractor {
    static String extractName(KeyValue keyValue) {
        Method method = lambdaMethod(keyValue);
        Parameter parameter = method.getParameters()[0];
        if (!parameter.isNamePresent()) {
            throw new IllegalStateException("You need to compile with javac -parameters for parameter reflection to work; You also need java 8u60 or newer to use it with lambdas");
        }
        return parameter.getName();
    }

    private static SerializedLambda serialized(KeyValue keyValue) {
        try {
            Method replaceMethod = keyValue.getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(keyValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> getContainingClass(SerializedLambda serialized) {
        try {
            String className = serialized.getImplClass().replaceAll("/", ".");
            return Class.forName(className);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static Method lambdaMethod(KeyValue keyValue) {
        SerializedLambda lambda = serialized(keyValue);
        Class<?> containingClass = getContainingClass(lambda);
        return asList(containingClass.getDeclaredMethods())
            .stream()
            .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
            .findFirst()
            .orElseThrow(UnableToGuessMethodException::new);
    }

    static class UnableToGuessMethodException extends IllegalStateException {
    }
}
