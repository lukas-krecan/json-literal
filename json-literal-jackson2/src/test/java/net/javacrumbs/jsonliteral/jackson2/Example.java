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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Arrays.asList;
import static net.javacrumbs.jsonliteral.jackson2.JsonLiteral.array;
import static net.javacrumbs.jsonliteral.jackson2.JsonLiteral.obj;

public class Example {
    public static void main(String[] args) throws JsonProcessingException {
        JsonNode node =
                obj(
                        one -> true,
                        two -> obj(
                                three -> false
                        ),
                        string -> "value",
                        integer -> 1,
                        $null -> null,
                        $double -> 1.1,
                        $float -> 1.0,
                        array -> asList(1, "a", 3),
                        array2 -> array("a", "b", "c") // you can use array method
                );

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(node));

        System.out.println("------");

        String value = "valueFromVariable";

        JsonNode node2 =
                obj(
                        root -> obj(
                                value1 -> "value",
                                value2 -> obj(
                                        value3 -> 1,
                                        value4 -> obj(
                                                test -> value
                                        )
                                )
                        )
                );

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(node2));
    }
}
