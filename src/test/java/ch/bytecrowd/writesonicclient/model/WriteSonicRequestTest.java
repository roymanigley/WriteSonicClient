package ch.bytecrowd.writesonicclient.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WriteSonicRequestTest {

    public static final boolean ENABLE_GOOGLE_RESULTS_DEFAULT = true;
    public static final boolean ENABLE_MEMORY_DEFAULT = false;
    public static final String INPUT_TEXT_DEFAULT = "Haha";
    public static final boolean ENABLE_GOOGLE_RESULTS_UPDATED = false;
    public static final boolean ENABLE_MEMORY_UPDATED = true;
    public static final String INPUT_TEXT_UPDATED = "UIIII";

    @Test
    void assertEqualsAndHashCode() {
        WriteSonicRequest request_default_one = WriteSonicRequest.builder()
                .enable_google_results(ENABLE_GOOGLE_RESULTS_DEFAULT)
                .enable_memory(ENABLE_MEMORY_DEFAULT)
                .input_text(INPUT_TEXT_DEFAULT)
                .build();

        WriteSonicRequest request_default_two = WriteSonicRequest.builder()
                .enable_google_results(ENABLE_GOOGLE_RESULTS_DEFAULT)
                .enable_memory(ENABLE_MEMORY_DEFAULT)
                .input_text(INPUT_TEXT_DEFAULT)
                .build();

        WriteSonicRequest request_changed = WriteSonicRequest.builder()
                .enable_google_results(ENABLE_GOOGLE_RESULTS_UPDATED)
                .enable_memory(ENABLE_MEMORY_UPDATED)
                .input_text(INPUT_TEXT_UPDATED)
                .build();

        assertThat(request_default_one).isEqualTo(request_default_two);
        assertThat(request_default_one).hasSameHashCodeAs(request_default_two);

        assertThat(request_default_one).isNotEqualTo(request_changed);
        assertThat(request_default_one.hashCode()).isNotEqualTo(request_changed.hashCode());
    }
}