package net.javacrumbs.jsonliteral.core;

/**
 * Translates a name so we can support other name than Java identifiers.
 */
public interface NameTranslator {
    public static NameTranslator DEFAULT_TRANSLATOR = new NameTranslator() {
        @Override
        public String translate(String originalName) {
            if (originalName.startsWith("$")) {
                return originalName.substring(1);
            } else {
                return originalName;
            }
        }
    };
    String translate(String originalName);
}
