package sef.module08.activity;

import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TextFileProcessor {

    public static void main(String[] args) {

        String inputFile = "data/input.txt";
        String outputFile = "data/output.txt";

        TextFileReader readerUtil = new TextFileReader();
        TextFileWriter writerUtil = new TextFileWriter();

        int lineCount = 0;

        try (
                BufferedReader reader = readerUtil.getReader(inputFile);
                BufferedWriter writer = writerUtil.getWriter(outputFile)
        ) {

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String processedLine = line.toUpperCase();

                writer.write(processedLine);
                writer.newLine();

                lineCount++;
            }

            System.out.println("Processing completed.");
            System.out.println("Number of lines processed: " + lineCount);

        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
