package com.barkovsky.check_runner.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class ReadWriteFileUtil {

    private ReadWriteFileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void writeStringInFile(String receipt, String fileFormat) {
        String fileName = UUID.randomUUID() + fileFormat;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(receipt);
            writer.newLine();
        } catch (IOException exception) {
            System.err.println("Writing in file causes error");
            System.exit(1);
        }
    }

    public static String readStringFromFile(String fileName) {
        if (fileName == null) {
            System.err.println("Incorrect filename");
            System.exit(1);
        }
        Path path = Path.of(fileName);
        String str = null;
        try {
            str = Files.readString(path);
        } catch (IOException e) {
            System.err.println("Reading from file causes error");
            System.exit(1);
        }
        return str;
    }

}
