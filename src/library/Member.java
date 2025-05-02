package library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    public int id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }

    public boolean hasBook(Book book) {
        return borrowedBooks.contains(book);
    }

    @Override
    public String toString() {
        return name + " (Borrowed: " + borrowedBooks.size() + ")";
    }
}
