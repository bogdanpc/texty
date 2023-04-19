package org.meanwhile;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Line builder
 * Observer is passed used constructor. Text build can use it to add line to it's body existing text
 * Ending/closing a line will notify observer.
 */
class LineBuilder implements Line {
    private final Function<StringBuilder, Text> observer;
    private final StringBuilder line;

    private String label;
    private char endOfLinePunctuation;

    final static char CR = '\n';
    final static char SEMI_COLON = ';';
    final static char FULL_STOP = '.';

    LineBuilder(Function<StringBuilder, Text> observer) {
        Objects.requireNonNull(observer, "Builder is required");
        this.observer = observer;
        this.line = new StringBuilder();
    }

    LineBuilder(String text, Function<StringBuilder, Text> observer) {
        this(observer);
        line.append(text);
    }

    LineBuilder(List<String> text, Function<StringBuilder, Text> observer) {
        this(String.join(", ", text), observer);
    }

    LineBuilder(Supplier<String> text, Function<StringBuilder, Text> observer) {
        this(text.get(), observer);
    }

    /**
     * Label of the line. Only one label / line.
     * Label is added at the beginning of the line. Comma and space is appended after it. i.e. `Location: `
     * @param label Line label
     * @return Current Line instance
     */
    @Override
    public Line label(String label) {
        Objects.requireNonNull(label, "Please provide a label");
        this.label = label;
        return this;
    }

    /**
     * Append not empty string to current line
     * @param text Text appended to line
     * @return Current Line instance
     */
    @Override
    public Line append(String text) {
        if (text != null && !text.isEmpty()) {
            line.append(text);
        }
        return this;
    }

    /**
     * Append not empty string to current line
     * @param supplier Supplier String result appended to line
     * @return Current Line instance
     */
    @Override
    public Line append(Supplier<String> supplier) {
        return append(supplier.get());
    }

    /**
     * Set end of line character. It will be added before a CR
     * @return Current Line instance
     */
    @Override
    public Line endsWith(char punctuation) {
        this.endOfLinePunctuation = punctuation;
        return this;
    }

    /**
     *
     * @return Text instance to chain lines
     */
    @Override
    public Text endLine() {
        appendLabelEndLine();
        appendPunctuationEndLine();

        //notify observer
        return observer.apply(line);
    }

    private void appendPunctuationEndLine() {
        if (endOfLinePunctuation != Character.MIN_VALUE) {
            line.append(endOfLinePunctuation);
        }
    }

    private void appendLabelEndLine() {
        if (label != null) {
            line.insert(0, label + ": ");
        }
    }
}
