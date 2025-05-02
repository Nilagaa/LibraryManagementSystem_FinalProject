package library;

import java.util.*;

public class BookStore {
    private List<Book> books;

    public BookStore() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    // Linear search by title or author
    public List<Book> searchLinear(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    // Binary search (title must be sorted before use)
    public Book binarySearchByTitle(String title) {
        List<Book> sorted = new ArrayList<>(books);
        sortByTitleMerge(sorted); // ensure it's sorted
        int left = 0, right = sorted.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Book midBook = sorted.get(mid);
            int compare = midBook.getTitle().compareToIgnoreCase(title);

            if (compare == 0) return midBook;
            if (compare < 0) left = mid + 1;
            else right = mid - 1;
        }

        return null;
    }

    // Merge sort by title
    public void sortByTitleMerge(List<Book> list) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<Book> left = new ArrayList<>(list.subList(0, mid));
        List<Book> right = new ArrayList<>(list.subList(mid, list.size()));

        sortByTitleMerge(left);
        sortByTitleMerge(right);
        merge(list, left, right);
    }

    private void merge(List<Book> list, List<Book> left, List<Book> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getTitle().compareToIgnoreCase(right.get(j).getTitle()) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    // Quick sort by author
    public void sortByAuthorQuick() {
        quickSort(0, books.size() - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        String pivot = books.get(high).getAuthor();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (books.get(j).getAuthor().compareToIgnoreCase(pivot) <= 0) {
                i++;
                Collections.swap(books, i, j);
            }
        }
        Collections.swap(books, i + 1, high);
        return i + 1;
    }
}
