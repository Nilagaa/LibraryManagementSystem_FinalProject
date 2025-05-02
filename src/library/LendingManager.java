package library;

public class LendingManager {
    private Library library;

    public LendingManager(Library library) {
        this.library = library;
    }

    public String borrowBook(String memberName, String bookTitle) {
        Member member = library.findMember(memberName);
        Book book = library.findBook(bookTitle);

        if (member == null || book == null) return "Invalid input.";
        if (!book.isAvailable()) return "Book not available.";

        member.borrowBook(book);
        return "Book borrowed successfully.";
    }

    public String returnBook(String memberName, String bookTitle) {
        Member member = library.findMember(memberName);
        Book book = library.findBook(bookTitle);

        if (member == null || book == null) return "Invalid input.";
        if (!member.hasBook(book)) return "Member doesn't have this book.";

        member.returnBook(book);
        return "Book returned successfully.";
    }
}
