public class Book {
    private String title;
    private String author;
    private int totalCopies;
    private int borrowedCopies;
    public Book(String title, String author, int totalCopies) {
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.borrowedCopies = 0;
    }
    // Getters
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getAvailableCopies() {
        return totalCopies - borrowedCopies;
    }
    // Borrow behaviour
    public boolean borrowBook() {
        if (getAvailableCopies() > 0) {
            borrowedCopies++;
            return true;
        }
        return false;
    }
    // return behavior: Returns true if successful
    public boolean returnBook() {
        if (borrowedCopies > 0) {
            borrowedCopies--;
            return true;
        }
        return false;
    }
}
