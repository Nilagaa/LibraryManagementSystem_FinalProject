package gui;

import javax.swing.*;
import java.awt.*;
import library.*;

public class MainWindow extends JFrame {
    public MainWindow(Library library) {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Books", new BookPanel(library));
        tabs.add("Members", new MemberPanel(library));
        tabs.add("Borrow/Return", new BorrowReturnPanel(library));

        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }
}