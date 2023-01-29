package ch.bytecrowd.writesonicclient.model;

import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteSonicResponse {
    private String message;
    private List<String> image_urls;

    public String toPrint() {
        String image_urls = Optional.ofNullable(this.image_urls)
                .map(urls -> urls.stream()
                        .map(image_url -> "- " + image_url)
                        .collect(Collectors.joining("\n")))
                .orElse(StringUtil.EMPTY_STRING);
        return message + "\n\n"
                + image_urls;
    }
}
