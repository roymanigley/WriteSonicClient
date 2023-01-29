package ch.bytecrowd.writesonicclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteSonicRequest {
    private boolean enable_google_results;
    private boolean enable_memory;
    private String input_text;
}
