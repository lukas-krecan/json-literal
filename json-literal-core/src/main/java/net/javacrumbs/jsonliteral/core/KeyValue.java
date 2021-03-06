package net.javacrumbs.jsonliteral.core;


import java.io.Serializable;

/**
 * An interface to use for Key -&gt; Value.
 */
@FunctionalInterface
public interface KeyValue extends Serializable {
    public Object apply(String key);
}
