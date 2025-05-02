package library;

import java.util.*;

public class Library {
    private BookStore bookStore;
    private List<Member> members;

    public Library() {
        bookStore = new BookStore();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookStore.addBook(book);
    }

    public void removeBook(Book book) {
        bookStore.removeBook(book);
    }

    public List<Book> getBooks() {
        return bookStore.getAllBooks();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Book> searchBooks(String keyword) {
        return bookStore.searchLinear(keyword);
    }

    public Book findBook(String title) {
        return bookStore.binarySearchByTitle(title);
    }

    public Member findMember(String name) {
        for (Member m : members) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    // Search members by name (case-insensitive)
    public List<Member> searchMembers(String keyword) {
        List<Member> results = new ArrayList<>();
        for (Member m : members) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(m);
            }
        }
        return results;
    }

    // Sort members by name (alphabetically)
    public void sortMembersByName() {
        members.sort(Comparator.comparing(Member::getName));
    }

    public void sortBooksByTitle() {
        bookStore.sortByTitleMerge(bookStore.getAllBooks());
    }

    public void sortBooksByAuthor() {
        bookStore.sortByAuthorQuick();
    }
}
