package net.javacrumbs.jsonliteral.core;

/**
 * Translates a name so we can support other name than Java identifiers.
 */
public interface NameTranslator {
    /**
     * Strips $ if it's on the first position.
     */
    public static NameTranslator DEFAULT_TRANSLATOR = originalName -> {
        if (originalName.startsWith("$")) {
            return originalName.substring(1);
        } else {
            return originalName;
        }
    };
    String translate(String originalName);
}
