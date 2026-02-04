package sef.module08.activity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {

    public BufferedWriter getBufferedWriter(String filePath) throws IOException {
        return new BufferedWriter(new FileWriter(filePath));
    }
}
