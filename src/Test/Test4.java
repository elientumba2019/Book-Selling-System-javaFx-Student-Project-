package Test;

import Data_Access_Object.DAO_implementations.SellingDaoImplementation;
import Data_Access_Object.DAO_interfaces.SellingDataAccessObject;
import MVC.Model.Purchased;

import java.util.List;

/**
 * Created by elie on 17-5-23.
 */
public class Test4 {

    public  static void main(String[] args){

        SellingDataAccessObject object = new SellingDaoImplementation();

        /*Purchased book = new Purchased();
        book.setBookName("new book");
        book.setPrice(123+"");
        book.setQuantity(3);

        object.addBook(book);
        */


        /*List<Purchased> list = object.getBook("2017-05-23");
        for(int c = 0 ; c < list.size() ; c++){
            System.out.println(list.get(c).toString());
        }
        */

        object.deleteBook("new book");
    }
}
