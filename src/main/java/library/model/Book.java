package library.model;

public class Book {
    private int id;
    private String name;
    private String author;
    private int dateOfPresent;

    public Book() {
    }

    public Book(String name, String author, int dateOfPresent) {
        this.name = name;
        this.author = author;
        this.dateOfPresent = dateOfPresent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDateOfPresent() {
        return dateOfPresent;
    }

    public void setDateOfPresent(int dateOfPresent) {
        this.dateOfPresent = dateOfPresent;
    }
}
