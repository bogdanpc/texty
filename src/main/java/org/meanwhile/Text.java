package org.meanwhile;

import java.util.List;
import java.util.function.Supplier;

interface Text {
    Line line();

    Line line(String value);

    Line line(List<String> value);

    Line line(Supplier<String> lineSupplier);

    Text newLine();

    String build();
}