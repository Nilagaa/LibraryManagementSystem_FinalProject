package library;

import java.beans.*;
import java.io.*;
import java.util.List;

public class FileManager {
    public static void saveLibrary(Library library, String filename) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)))) {
            encoder.writeObject(library);
        }
    }

    public static Library loadLibrary(String filename) throws IOException {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)))) {
            return (Library) decoder.readObject();
        }
    }
}
