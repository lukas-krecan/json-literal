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
import com.fasterxml.jackson.databind.ObjectWriter;

import static net.javacrumbs.jsonliteral.jackson2.JsonLiteral.obj;

public class Example2 {

    private static final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    public static void main(String[] args) throws JsonProcessingException {
        JsonNode node =
            obj(
                address -> obj(
                    street -> "Central Park",
                    city -> "Prague"
                ),
                age -> 2,
                location -> null,
                ids -> new int[]{1, 2}
            );

        System.out.println(objectWriter.writeValueAsString(node));
    }
}
