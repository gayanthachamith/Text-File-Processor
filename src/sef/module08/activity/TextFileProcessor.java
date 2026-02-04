package sef.module08.activity;

import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TextFileProcessor {

    public static void main(String[] args) {

        String inputFile = "data/input.txt";
        String outputFile = "data/output.txt";

        //reader  writer objects
        TextFileReader readerUtil = new TextFileReader();
        TextFileWriter writerUtil = new TextFileWriter();

        int lineCount = 0;

        try (
                BufferedReader reader = readerUtil.getReader(inputFile);
                BufferedWriter writer = writerUtil.getWriter(outputFile)
        ) {

            String line;
//read line by line

            while ((line = reader.readLine()) != null) {
                if(!line.isBlank()) {
                    line = line.trim(); // removing whitespace
                    String processedLine = line.toUpperCase(); // convert to uppercase

                    writer.write(processedLine); //write to output file
                    writer.newLine();

                    lineCount++;
                }
            }

            System.out.println("Processing completed.");
            System.out.println("Number of lines processed: " + lineCount);

        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
