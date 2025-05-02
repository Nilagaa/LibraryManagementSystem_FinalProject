package gui;

import library.*;
import library.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookPanel extends JPanel {
    private DefaultListModel<Book> listModel;
    private JList<Book> bookList;

    public BookPanel(Library library) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title label
        JLabel titleLabel = new JLabel("Book Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // List of books
        listModel = new DefaultListModel<>();
        bookList = new JList<>(listModel);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setPreferredSize(new Dimension(500, 250));
        updateBookList(library.getBooks());
        add(scrollPane, BorderLayout.CENTER);

        // Button group
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton addButton = new JButton("➕ Add Book");
        JButton removeButton = new JButton("🗑️ Remove Selected");
        JButton searchButton = new JButton("🔍 Search");
        JButton sortTitleButton = new JButton("🔤 Sort by Title");
        JButton sortAuthorButton = new JButton("✍️ Sort by Author");
        JButton refreshButton = new JButton("🔁 Show All");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortTitleButton);
        buttonPanel.add(sortAuthorButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter Book Title:");
            String author = JOptionPane.showInputDialog("Enter Author Name:");
            if (title != null && author != null) {
                Book book = new Book(title.trim(), author.trim());
                library.addBook(book);
                updateBookList(library.getBooks());
            }
        });

        removeButton.addActionListener(e -> {
            Book selected = bookList.getSelectedValue();
            if (selected != null) {
                library.removeBook(selected);
                updateBookList(library.getBooks());
            }
        });

        searchButton.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog("Search by title or author:");
            if (keyword != null && !keyword.trim().isEmpty()) {
                updateBookList(library.searchBooks(keyword.trim()));
            }
        });

        sortTitleButton.addActionListener(e -> {
            library.sortBooksByTitle();
            updateBookList(library.getBooks());
        });

        sortAuthorButton.addActionListener(e -> {
            library.sortBooksByAuthor();
            updateBookList(library.getBooks());
        });

        refreshButton.addActionListener(e -> {
            updateBookList(library.getBooks());
        });
    }

    private void updateBookList(List<Book> books) {
        listModel.clear();
        for (Book b : books) {
            listModel.addElement(b);
        }
    }
}
