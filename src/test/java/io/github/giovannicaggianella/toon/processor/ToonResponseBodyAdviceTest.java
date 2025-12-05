package io.github.giovannicaggianella.toon.processor;

import io.github.giovannicaggianella.toon.annotation.ToonResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ToonResponseBodyAdviceTest {

    private ToonResponseBodyAdvice advice;

    @Before
    public void setUp() {
        advice = new ToonResponseBodyAdvice(new ToonAnnotationProcessor());
    }

    @Test
    public void supportsReturnsTrueForAnnotatedMethod() throws NoSuchMethodException {
        MethodParameter parameter = methodParameter("annotatedEndpoint");

        boolean supported = advice.supports(parameter, JacksonJsonHttpMessageConverter.class);

        assertThat(supported).isTrue();
    }

    @Test
    public void beforeBodyWriteEncodesResponseAndSetsContentType() throws Exception {
        MethodParameter parameter = methodParameter("annotatedEndpoint");
        ServerHttpResponse response = new TestServerHttpResponse();
        Map<String, Object> body = new HashMap<>();
        body.put("tags", Arrays.asList("dev", "ops"));

        Object result = advice.beforeBodyWrite(body, parameter, MediaType.APPLICATION_JSON,
                JacksonJsonHttpMessageConverter.class, null, response);

        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.parseMediaType("application/toon"));
        assertThat(result).isInstanceOf(String.class);
        assertThat((String) result).contains("[#2]");
    }

    @Test
    public void beforeBodyWriteWithoutAnnotationReturnsOriginalBody() throws Exception {
        MethodParameter parameter = methodParameter("plainEndpoint");
        ServerHttpResponse response = new TestServerHttpResponse();
        List<String> body = Arrays.asList("a", "b");

        Object result = advice.beforeBodyWrite(body, parameter, MediaType.APPLICATION_JSON,
                JacksonJsonHttpMessageConverter.class, null, response);

        assertThat(result).isSameAs(body);
        assertThat(response.getHeaders().getContentType()).isNull();
    }

    private MethodParameter methodParameter(String methodName) throws NoSuchMethodException {
        Method method = DummyController.class.getDeclaredMethod(methodName);
        return new MethodParameter(method, -1);
    }

    static class DummyController {

        @ToonResponse(lengthMarker = true)
        public Map<String, Object> annotatedEndpoint() {
            return new HashMap<>();
        }

        public List<String> plainEndpoint() {
            return Arrays.asList("plain");
        }
    }

    static class TestServerHttpResponse implements ServerHttpResponse {

        private final HttpHeaders headers = new HttpHeaders();

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

        @Override
        public OutputStream getBody() throws IOException {
            return new ByteArrayOutputStream();
        }

        @Override
        public void setStatusCode(HttpStatusCode status) {
            // not needed for these tests
        }

        @Override
        public void flush() throws IOException {
            // nothing to flush in tests
        }

        @Override
        public void close() {
            // nothing to close
        }
    }
}
