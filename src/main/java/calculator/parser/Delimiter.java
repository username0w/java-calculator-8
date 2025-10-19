package calculator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Delimiter {
    private static final List<String> DEFAULT = List.of(",", ":");
    private final List<String> custom;

    public Delimiter(List<String> custom) {
        List<String> validated = custom == null ? List.of() : custom;

        if (validated.stream().anyMatch(d -> d.length() != 1)) {
            throw new IllegalArgumentException("커스텀 구분자는 한 개의 문자로 구성됩니다.");
        }
        this.custom = validated;
    }

    public String getRegex() {
        List<String> all = new ArrayList<>(DEFAULT);
        if (custom == null) {
            return String.join("|", all);
        }
        all.addAll(custom);
        return all.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }
}


