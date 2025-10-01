package org.meanwhile;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TextyTest {

    public static final String COLORS = "Colors";

    @Test
    void text_works_all_api() {

        var text = Texty
                .newBuilder()
                .line().append("Hello").label("Greetings").endLine()
                .line(List.of("red", "blue", "orange")).label(COLORS).endsWith(';').endLine()
                .line("1").label("Numbers").endLine()
                .newLine()
                .line("A").append("B").append("C").append("").endLine()
                .line(() -> "Supplier").endLine()
                .build();

        assertThat(text).isEqualTo("""
                Greetings: Hello
                Colors: red, blue, orange;
                Numbers: 1
                
                ABC
                Supplier\
                """);
    }

    @Test
    void build_without_lines() {
        var text = Texty
                .newBuilder()
                .build();
        assertThat(text).isEmpty();
    }

    @Test
    void append_text_to_line() {
        var text = Texty
                .newBuilder()
                .line().append("B").append("C").append("").append(() -> "D").endLine()
                .build();

        assertThat(text).isEqualTo("BCD");
    }

    @Test
    void multiple_label_same_line() {
        var text = Texty
                .newBuilder()
                .line("works").label("Label1").label("Label2").endLine()
                .build();

        assertThat(text).isEqualTo("Label2: works");
    }

    @Test
    void end_of_line_punctuation() {
        var text = Texty
                .newBuilder()
                .line(List.of("red", "blue")).label(COLORS).endsWith(LineBuilder.SEMI_COLON).endLine()
                .line(List.of("black", "white")).label(COLORS).endsWith(LineBuilder.FULL_STOP).endLine()
                .build();

        assertThat(text).isEqualTo("""
                Colors: red, blue;
                Colors: black, white.\
                """);
    }
}