package io.github.giovannicaggianella.toon.processor;

import dev.toonformat.jtoon.Delimiter;
import dev.toonformat.jtoon.EncodeOptions;
import dev.toonformat.jtoon.JToon;
import tools.jackson.databind.ObjectMapper;


/**
 * Processes TOON annotations and handles serialization/deserialization of TOON format.
 * This class provides methods to encode and decode between Java objects and TOON format.
 */
public class ToonAnnotationProcessor {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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
     * Encodes a Java object to TOON format with optional length markers.
     *
     * @param object       the object to encode
     * @param lengthMarker whether arrays should be prefixed with a length marker
     * @return the TOON string representation of the object
     */
    public String encode(Object object, boolean lengthMarker) {
        if (!lengthMarker) {
            return encode(object);
        }
        EncodeOptions options = new EncodeOptions(2, Delimiter.COMMA, true, false, 3);
        return JToon.encode(object, options);
    }

    /**
     * Decodes a TOON string to a Java object of the specified class.
     *
     * @param <T>   the type of the target object
     * @param toon  the TOON string to decode
     * @param clazz the target class
     * @return the decoded object
     * @throws IllegalArgumentException if the TOON string is invalid or cannot be decoded to the target class
     */
    public <T> T decode(String toon, Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Target class must not be null");
        }
        if (toon == null || toon.trim().isEmpty()) {
            throw new IllegalArgumentException("TOON string must not be null or blank");
        }

        try {
            Object decoded = JToon.decode(toon);
            if (decoded == null) {
                return null;
            }

            if (clazz.isInstance(decoded)) {
                return clazz.cast(decoded);
            }

            return OBJECT_MAPPER.convertValue(decoded, clazz);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Failed to decode TOON payload into " + clazz.getSimpleName(), ex);
        }
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
