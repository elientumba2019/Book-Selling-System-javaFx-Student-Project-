package Test;

import Data_Access_Object.DAO_implementations.BookDaoImplementation;
import Data_Access_Object.DAO_interfaces.BookDataAccessObject;

/**
 * Created by elie on 17-5-21.
 */
public class Test2 {

    public static void main(String[] args){

        BookDataAccessObject bookDataAccessObject = new BookDaoImplementation();
        /*Book book = new Book();

        book.setName("new");
        book.setAuthor("elie");
        book.setCategory("mbolo");
        book.setDescription("matako");
        book.setPrice("22.5");
        book.setInStock(20);
        book.setPublisher("lalala");

        bookDataAccessObject.addBook(book);
        */


       /* List<Book> books = bookDataAccessObject.getAllBooks();
        for(int c = 0 ; c < books.size() ; c++){
            System.out.println(books.get(c).toString());
        }
        */

       /*Book book = bookDataAccessObject.getBookByName("new");
       System.out.println(book.toString());*/



        /*List<Book> books = bookDataAccessObject.getBooksByAuthor("elie");
        for(int c = 0 ; c < books.size() ; c++){
            System.out.println(books.get(c).toString());
        }
        */



        /*List<Book> books = bookDataAccessObject.getBooksByCategory("mbolo");
        for(int c = 0 ; c < books.size() ; c++){
            System.out.println(books.get(c).toString());
        }*/


        /*List<Book> books = bookDataAccessObject.getBooksByPublisher("lalala");
        for(int c = 0 ; c < books.size() ; c++){
            System.out.println(books.get(c).toString());
        }
        */


        /*Book book = new Book();

        book.setName("Old");
        book.setAuthor("elie");
        book.setCategory("mbolo");
        book.setDescription("matako");
        book.setPrice("22.5");
        book.setInStock(20);
        book.setPublisher("lalala");

        bookDataAccessObject.update("new" , book);
        */


        bookDataAccessObject.deleteBook("Old");

    }
}
