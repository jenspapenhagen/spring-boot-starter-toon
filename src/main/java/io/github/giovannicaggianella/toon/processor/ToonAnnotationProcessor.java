package io.github.giovannicaggianella.toon.processor;

import com.felipestanzani.jtoon.JToon;

/**
 * Processes TOON annotations and handles serialization/deserialization of TOON format.
 * This class provides methods to encode and decode between Java objects and TOON format.
 */
public class ToonAnnotationProcessor {

    /**
     * Default constructor.
     */
    public ToonAnnotationProcessor() {
    }

    /**
     * Encodes a Java object to TOON format.
     *
     * @param object the object to encode
     * @return the TOON string representation of the object
     * @throws IllegalArgumentException if the object cannot be encoded to TOON format
     */
    public String encode(Object object) {
        return JToon.encode(object);
    }

    /**
     * Decodes a TOON string to a Java object of the specified class.
     *
     * @param <T> the type of the target object
     * @param toon the TOON string to decode
     * @param clazz the target class
     * @return the decoded object
     * @throws IllegalArgumentException if the TOON string is invalid or cannot be decoded to the target class
     */
    public <T> T decode(String toon, Class<T> clazz) {
        //return JToon.decode(toon, clazz);
        return null;
    }

    /**
     * Converts a JSON string to TOON format.
     *
     * @param json the JSON string to convert
     * @return the TOON representation of the JSON
     * @throws IllegalArgumentException if the JSON string is invalid
     */
    public String encodeJson(String json) {
        return JToon.encodeJson(json);
    }

    /**
     * Converts a TOON string to JSON format.
     *
     * @param toon the TOON string to convert
     * @return the JSON representation of the TOON string
     * @throws IllegalArgumentException if the TOON string is invalid
     */
    public String decodeToJson(String toon) {
        return JToon.decodeToJson(toon);
    }
}
