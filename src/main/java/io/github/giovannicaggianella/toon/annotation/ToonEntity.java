package io.github.giovannicaggianella.toon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a Toon-serializable entity.
 * Enables automatic TOON format serialization/deserialization using JToon.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToonEntity {

    /**
     * The name of the entity for TOON serialization.
     * If not specified, the simple class name is used.
     *
     * @return the name of the entity
     */
    String name() default "";

    /**
     * The description of the entity.
     * This is used for documentation purposes only.
     *
     * @return the description of the entity
     */
    String description() default "";

}
