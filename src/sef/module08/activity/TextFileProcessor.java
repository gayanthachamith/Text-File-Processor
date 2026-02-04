package sef.module08.activity;

import java.io.*;

public class TextFileProcessor {


        public static void main(String[] args) {

            String inputFile = "data/input.txt";
            String outputFile = "data/output.txt";

            int lineCount = 0;

            try (
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
            ) {

                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    String processedLine = line.toUpperCase();

                    writer.write(processedLine);
                    writer.newLine();

                    lineCount++;
                }

                System.out.println("File processed successfully.");
                System.out.println("Number of lines processed: " + lineCount);

            } catch (IOException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

