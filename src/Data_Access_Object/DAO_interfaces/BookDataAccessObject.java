package Data_Access_Object.DAO_interfaces;

import MVC.Model.Book;

import java.util.List;

/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public interface BookDataAccessObject {

    // get all books
    List<Book> getAllBooks();

    //get a book by its name
    Book getBookByName(String name);

    //get books by its author
    List<Book> getBooksByAuthor(String author);

    //getBooks by categoru
    List<Book> getBooksByCategory(String category);

    //get book by its publisher
    List<Book> getBooksByPublisher(String publisher);

    //add book
    void addBook(Book book);

    //update book
    void update(String name, Book newBook);


    //void delete Book
    void deleteBook(String name);
}
