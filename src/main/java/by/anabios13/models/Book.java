package by.anabios13.models;

import javax.validation.constraints.NotEmpty;

public class Book {
    private int bookId;
    private int foreignId;
    @NotEmpty(message = "Book must have author!")
    private String author;

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    @NotEmpty(message = "Name of book should not be empty")
    private String nameOfBook;
    private int yearOfRelease;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getForeignId() {
        return foreignId;
    }

    public void setForeignId(int foreignId) {
        this.foreignId = foreignId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
