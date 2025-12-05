package io.github.giovannicaggianella.toon.config;

import io.github.giovannicaggianella.toon.processor.ToonAnnotationProcessor;
import io.github.giovannicaggianella.toon.processor.ToonResponseBodyAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot autoconfiguration for Toon.
 * Automatically registers beans required for TOON serialization using JToon.
 */
@Configuration
public class ToonAutoConfiguration {

    /**
     * Creates a bean for TOON annotation processing.
     *
     * @return an instance of ToonAnnotationProcessor
     */
    @Bean
    @ConditionalOnMissingBean
    public ToonAnnotationProcessor toonAnnotationProcessor() {
        return new ToonAnnotationProcessor();
    }

    /**
     * Registers the {@link ToonResponseBodyAdvice} bean to handle @ToonResponse annotations.
     *
     * @param processor the ToonAnnotationProcessor used for encoding
     * @return the ToonResponseBodyAdvice bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ToonResponseBodyAdvice toonResponseBodyAdvice(ToonAnnotationProcessor processor) {
        return new ToonResponseBodyAdvice(processor);
    }

}
