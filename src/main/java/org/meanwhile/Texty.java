package org.meanwhile;

import java.util.List;
import java.util.function.Supplier;


/**
 * Build
 */
public class Texty implements Text {

    private final StringBuilder textBuilder = new StringBuilder();

    /**
     * Starts a text builder
     * @return new instance of Texty
     */
    public static Texty newBuilder() {
        return new Texty();
    }

    /**
     * New instance of LineBuilder
     */
    @Override
    public Line line() {
        return new LineBuilder(this::updateNewLine);
    }

    /**
     * New instance of LineBuilder
     * @param lineText Content of line
     * @return Current Line instance.
     */
    @Override
    public Line line(String lineText) {
        return new LineBuilder(lineText, this::updateNewLine);
    }

    /**
     * New instance of LineBuilder
     * @param lineText Line content as List of string
     * @return Current Line instance.
     */
    @Override
    public Line line(List<String> lineText) {
        return new LineBuilder(lineText, this::updateNewLine);
    }

    /**
     * New instance of LineBuilder
     * @param lineSupplier Java Supplier<String> for line content
     * @return Current Line instance
     */
    @Override
    public Line line(Supplier<String> lineSupplier) {
        return new LineBuilder(lineSupplier, this::updateNewLine);
    }

    /**
     * Insert a new line (with carriage return)
     * @return Current Texty instance
     */
    @Override
    public Text newLine() {
        textBuilder.append(LineBuilder.CR);
        return this;
    }

    /**
     * Build full text
     * @return Text as String
     */
    public String build() {
        return textBuilder.toString();
    }

    /**
     *
     * @param builder S
     * @return  Current Texty instance
     */
    private Text updateNewLine(StringBuilder builder) {
        if(!textBuilder.isEmpty()) {
            newLine();
        }
        textBuilder.append(builder);
        return this;
    }
}
