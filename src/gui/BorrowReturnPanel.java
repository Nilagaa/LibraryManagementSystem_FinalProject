package gui;

import library.*;
import javax.swing.*;
import java.awt.*;

public class BorrowReturnPanel extends JPanel {
    public BorrowReturnPanel(Library library) {
        LendingManager manager = new LendingManager(library);
        setLayout(new GridLayout(3, 2, 10, 10));

        JTextField memberField = new JTextField();
        JTextField bookField = new JTextField();
        JButton borrowBtn = new JButton("Borrow");
        JButton returnBtn = new JButton("Return");

        borrowBtn.addActionListener(e -> {
            String msg = manager.borrowBook(memberField.getText(), bookField.getText());
            JOptionPane.showMessageDialog(null, msg);
        });

        returnBtn.addActionListener(e -> {
            String msg = manager.returnBook(memberField.getText(), bookField.getText());
            JOptionPane.showMessageDialog(null, msg);
        });

        add(new JLabel("Member Name:"));
        add(memberField);
        add(new JLabel("Book Title:"));
        add(bookField);
        add(borrowBtn);
        add(returnBtn);
    }
}
