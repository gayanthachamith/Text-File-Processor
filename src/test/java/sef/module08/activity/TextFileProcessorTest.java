package test.java.sef.module08.activity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sef.module08.activity.TextFileProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFileProcessorTest {

    @TempDir
    Path tempDir;

    @Test
    void normalLines_shouldBeTrimmedAndUppercased() throws IOException {
        Path input = tempDir.resolve("input.txt");
        Path output = tempDir.resolve("output.txt");

        Files.writeString(input, "hello\nworld \njava io\n");

        TextFileProcessor processor = new TextFileProcessor();
        int count = processor.process(input.toString(), output.toString());

        List<String> outLines = Files.readAllLines(output);

        assertEquals(3, count);
        assertEquals(List.of("HELLO", "WORLD", "JAVA IO"), outLines);
    }

    @Test
    void tabsAndMultipleSpaces_shouldBePreservedExceptTrimEnds() throws IOException {
        Path input = tempDir.resolve("input.txt");
        Path output = tempDir.resolve("output.txt");

        // leading/trailing whitespace should be trimmed
        // internal tabs/spaces should remain
        Files.writeString(input,
                "  hello\tworld  \n" +
                        "A   B\n" +
                        "\tC\t \n"
        );

        TextFileProcessor processor = new TextFileProcessor();
        int count = processor.process(input.toString(), output.toString());

        List<String> outLines = Files.readAllLines(output);

        assertEquals(3, count);
        assertEquals(List.of("HELLO\tWORLD", "A   B", "C"), outLines);
    }

    @Test
    void fileDoesNotExist_shouldThrowIOException() {
        Path output = tempDir.resolve("output.txt");

        TextFileProcessor processor = new TextFileProcessor();

        assertThrows(IOException.class, () ->
                processor.process(tempDir.resolve("missing.txt").toString(), output.toString())
        );
    }

    @Test
    void summaryCount_shouldIgnoreEmptyLines() throws IOException {
        Path input = tempDir.resolve("input.txt");
        Path output = tempDir.resolve("output.txt");

        Files.writeString(input,
                "\n" +
                        "   \n" +
                        "hello\n" +
                        " \n" +
                        "world\n"
        );

        TextFileProcessor processor = new TextFileProcessor();
        int count = processor.process(input.toString(), output.toString());

        List<String> outLines = Files.readAllLines(output);

        assertEquals(2, count);
        assertEquals(List.of("HELLO", "WORLD"), outLines);
    }
}
