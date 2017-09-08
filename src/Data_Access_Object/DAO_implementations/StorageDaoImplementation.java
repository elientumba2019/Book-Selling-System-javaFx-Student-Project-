package Data_Access_Object.DAO_implementations;

import Data_Access_Object.DAO_interfaces.StorageDataAccessObject;
import Data_Access_Object.DataSources.DataSource;
import MVC.Model.BookStorage;

import javax.swing.plaf.nimbus.State;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elie on 17-5-22.
 */
public class StorageDaoImplementation implements StorageDataAccessObject , Serializable {



    /*
    get all books from storage
     */
    @Override
    public List<BookStorage> getBooksInStorage() {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM STORAGE";
        List<BookStorage> list = null;


        try{

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            list = new ArrayList<>();

            while (resultSet.next()){

                BookStorage book = new BookStorage();
                book.setName(resultSet.getString(1));
                book.setDate(resultSet.getString(2));
                book.setQuantity(resultSet.getInt(3));
                list.add(book);
            }


            if(list == null){
                System.out.println("no data  found");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return list;
    }








    /*
    gets a book from the storage given its name
     */
    @Override
    public BookStorage getBookFromStorage(String date) {

         DataSource source = new DataSource();
         Connection connection = source.getConnection();
         PreparedStatement statement = null;
         ResultSet resultSet = null;
         final String query = "SELECT * FROM STORAGE WHERE DATE = ?";
         BookStorage book = null;

         try {

             statement = connection.prepareStatement(query);
             statement.setString(1 , date);
             resultSet = statement.executeQuery();

             if(resultSet.next()){

                 book = new BookStorage();
                 book.setName(resultSet.getString(1));
                 book.setDate(resultSet.getString(2));
                 book.setQuantity(resultSet.getInt(3));
             }

             if(book == null){
                 System.out.println("The book was not found !");
             }


         }catch (SQLException e){
             e.printStackTrace();
         }
         finally {
             try {

                 connection.close();

             }catch (Exception e){
                 e.printStackTrace();
             }
         }


         return book;
    }








    /*
    completel removes a book from the database
     */
    @Override
    public void deleteBookFromStorage(String book) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        String delete = "DELETE FROM STORAGE WHERE BOOK = ?";
        PreparedStatement statement = null;


        try{

            statement = connection.prepareStatement(delete);
            statement.setString(1 , book);
            statement.executeUpdate();


            System.out.println("book successfully deleted");


        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {

                connection.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }








    /*
    insert a new book to the database
     */
    @Override
    public void addBookToStorage(BookStorage book){


        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        final  String insert = "INSERT INTO STORAGE VALUES(? , CURDATE() , ?)";

        try{

            statement = connection.prepareStatement(insert);
            statement.setString(1 , book.getName());
            statement.setInt(2 , book.getQuantity());
            statement.executeUpdate();


            System.out.println("new book added to category");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }









    /*
    gets a book given its name

     */

    @Override
    public BookStorage getBook(String name) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM STORAGE WHERE BOOK = ?";
        BookStorage book = null;

        try {

            statement = connection.prepareStatement(query);
            statement.setString(1 , name);
            resultSet = statement.executeQuery();

            if(resultSet.next()){

                book = new BookStorage();
                book.setName(resultSet.getString(1));
                book.setDate(resultSet.getString(2));
                book.setQuantity(resultSet.getInt(3));
            }

            if(book == null){
                System.out.println("The book was not found ! ");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {

                connection.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return book;
    }








    /*
    updates a book in storage
     */
    @Override
    public void updateBookInStorage(String name, BookStorage newBook) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        final String update = "UPDATE STORAGE SET BOOK = ? , DATE = CURDATE() , QUANTITY = ? WHERE BOOK = ?";


        try{

            statement = connection.prepareStatement(update);
            statement.setString(1 , newBook.getName());
            statement.setInt(2 , newBook.getQuantity());
            statement.setString(3 , name);

            System.out.println("Book updated !");

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {

            try{

                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
