package ch.bytecrowd.writesonicclient.service;

import com.sun.speech.freetts.Voice;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class TextToSpeechService {

    private final Pattern removeLinksAndWordsInBracets = Pattern.compile("\\[+((?!\\]).)+\\]+|\\(((?!\\)).)+\\)");
    private final Voice voice;

    public void speak(String text) {
        voice.speak(removeLinksAndWordsInBracets.matcher(text).replaceAll(StringUtil.EMPTY_STRING));
    }

    public void close() {
        voice.deallocate();
    }
}
