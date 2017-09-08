package Test;

import Data_Access_Object.DAO_implementations.StorageDaoImplementation;
import Data_Access_Object.DAO_interfaces.StorageDataAccessObject;
import MVC.Model.BookStorage;

import java.util.List;

/**
 * Created by elie on 17-5-22.
 */
public class Test3 {

    public static void main(String[] args) {

        StorageDataAccessObject s = new StorageDaoImplementation();

       /* BookStorage bookStorage = new BookStorage();
        bookStorage.setName("Elie");
        bookStorage.setQuantity(30);

        s.addBookToStorage(bookStorage);
        */


        /*List<BookStorage> list = s.getBooksInStorage();
        for(int c = 0 ; c < list.size() ; c++){
            System.out.println(list.get(c).toString());
        }
        */


        /*BookStorage book = s.getBookFromStorage("Elie");
        System.out.println(book.toString());
        *
        */


        BookStorage bookStorage = new BookStorage();
        bookStorage.setName("bobo");
        bookStorage.setQuantity(21);

    }
}
