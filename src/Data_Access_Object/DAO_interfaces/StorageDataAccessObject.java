package Data_Access_Object.DAO_interfaces;

import MVC.Model.Book;
import MVC.Model.BookStorage;


import java.util.List;
/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public interface StorageDataAccessObject {

    // gets all book from the database
    List<BookStorage> getBooksInStorage();

    //get one from the database
    BookStorage getBookFromStorage(String name);

    //delete
    void deleteBookFromStorage(String book);


    //add
    void addBookToStorage(BookStorage book);


    //gets a book given its name
    BookStorage getBook(String name);
    
    //updates a book instorage
    void updateBookInStorage(String name , BookStorage newBook);
}
