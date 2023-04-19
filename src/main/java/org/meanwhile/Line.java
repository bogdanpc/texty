package org.meanwhile;

import java.util.function.Supplier;

public interface Line {
    Line label(String value);
    Line append(String value);

    Line append(Supplier<String> supplier);

    Line endsWith(char punctuation);

    Text endLine();
}
