package gui;

import gui.MainWindow;
import library.Library;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Library lib = new Library();
            new MainWindow(lib);
        });
    }
}
