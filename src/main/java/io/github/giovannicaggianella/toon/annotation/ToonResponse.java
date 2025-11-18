package io.github.giovannicaggianella.toon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method return type to be serialized to TOON format.
 * Automatically serializes the response object to TOON format using JToon.
 *
 * <p>Example usage:
 * {@code
 * @GetMapping("/users/{id}")
 * @ToonResponse
 * public User getUser(@PathVariable Long id) {
 *     return userService.findById(id);
 * }
 * }
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToonResponse {

    /**
     * The content type to use for the response.
     * Default is "application/toon".
     *
     * @return the content type string
     */
    String contentType() default "application/toon";

    /**
     * Whether to include length markers in array serialization.
     * When true, arrays will be prefixed with # to indicate count.
     *
     * @return true if length markers should be included
     */
    boolean lengthMarker() default false;
}