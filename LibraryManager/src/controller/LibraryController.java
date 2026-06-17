package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;

public class LibraryController {

    private ObservableList<Book> books =
            FXCollections.observableArrayList();

    public ObservableList<Book> getBooks() {
        return books;
    }

    public void addBook(String title, String author) {

        if (!title.isEmpty() && !author.isEmpty()) {

            books.add(
                    new Book(title, author)
            );
        }
    }

    public ObservableList<Book> searchBook(String keyword) {

        ObservableList<Book> results =
                FXCollections.observableArrayList();

        for (Book book : books) {

            if (book.getTitle()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                results.add(book);
            }
        }

        return results;
    }
}