package sef.module08.activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {

    public BufferedReader getBufferedReader(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }
}
