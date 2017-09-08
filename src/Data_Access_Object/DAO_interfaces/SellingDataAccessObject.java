package Data_Access_Object.DAO_interfaces;

import MVC.Model.Purchased;

import java.util.List;

/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public interface SellingDataAccessObject {


    /*
    get all purchased books
     */
    List<Purchased> getAllPurchasedBooks();


    /*
    get a book give the date
     */
    List<Purchased> getBook(String date);



    /*
    add a book to the db
     */
    void addBook(Purchased book);



    /*
    delete a book
     */
    void deleteBook(String name);



    /*
    returns a books given its name
     */

    Purchased getBookByName(String name);

}
