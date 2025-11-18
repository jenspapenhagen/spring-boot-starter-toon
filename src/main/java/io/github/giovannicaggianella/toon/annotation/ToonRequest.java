package io.github.giovannicaggianella.toon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method parameter as a TOON-formatted request body.
 * Automatically deserializes TOON format to the target object using JToon.
 *
 * <p>Example usage:
 * {@code
 * @PostMapping("/users")
 * public ResponseEntity<User> createUser(@ToonRequest User user) {
 *     // user is automatically deserialized from TOON format
 *     return ResponseEntity.ok(user);
 * }
 * }
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToonRequest {

    /**
     * The name of the request parameter to bind to.
     * If not specified, the parameter name will be used.
     *
     * @return the name of the request parameter
     */
    String name() default "";

    /**
     * Whether the parameter is required.
     * If true, an exception will be thrown if the parameter is missing.
     *
     * @return true if the parameter is required
     */
    boolean required() default true;

}
