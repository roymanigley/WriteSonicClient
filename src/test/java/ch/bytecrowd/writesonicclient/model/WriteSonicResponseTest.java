package ch.bytecrowd.writesonicclient.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WriteSonicResponseTest {


    public static final String MESSAGE_DEFAULT = "Hello";
    public static final String IMAGE_LINK_ONE_DEFAULT = "http://image1.com";
    public static final String IMAGE_LINK_TWO_DEFAULT = "http://image2.com";

    public static final String MESSAGE_UPDATED = "Bye";
    public static final String IMAGE_LINK_ONE_UPDATED = "http://image1-changed.com";
    public static final String IMAGE_LINK_TWO_UPDATED = "http://image2-changed.com";

    @Test
    void assertEqualsAndHashCode() {
        WriteSonicResponse sonicResponse_default_one = WriteSonicResponse.builder()
                .message(MESSAGE_DEFAULT)
                .image_urls(List.of(IMAGE_LINK_ONE_DEFAULT, IMAGE_LINK_TWO_DEFAULT))
                .build();

        WriteSonicResponse sonicResponse_default_two = WriteSonicResponse.builder()
                .message(MESSAGE_DEFAULT)
                .image_urls(List.of(IMAGE_LINK_ONE_DEFAULT, IMAGE_LINK_TWO_DEFAULT))
                .build();

        WriteSonicResponse sonicResponse_changed = WriteSonicResponse.builder()
                .message(MESSAGE_UPDATED)
                .image_urls(List.of(IMAGE_LINK_ONE_UPDATED, IMAGE_LINK_TWO_UPDATED))
                .build();

        assertThat(sonicResponse_default_one).isEqualTo(sonicResponse_default_two);
        assertThat(sonicResponse_default_one).hasSameHashCodeAs(sonicResponse_default_two);

        assertThat(sonicResponse_default_one).isNotEqualTo(sonicResponse_changed);
        assertThat(sonicResponse_default_one.hashCode()).isNotEqualTo(sonicResponse_changed.hashCode());
    }
}