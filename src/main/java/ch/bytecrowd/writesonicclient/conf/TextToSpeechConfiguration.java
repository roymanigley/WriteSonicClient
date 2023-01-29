package ch.bytecrowd.writesonicclient.conf;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextToSpeechConfiguration {

    @Bean
    Voice createVoice() {
        System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        return voice;
    }
}
