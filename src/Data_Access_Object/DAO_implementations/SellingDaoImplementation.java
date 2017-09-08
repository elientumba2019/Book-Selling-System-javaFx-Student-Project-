package Data_Access_Object.DAO_implementations;

import Data_Access_Object.DAO_interfaces.SellingDataAccessObject;
import Data_Access_Object.DataSources.DataSource;
import MVC.Model.Purchased;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by elie on 17-5-23.
 */
public class SellingDaoImplementation implements SellingDataAccessObject  , Serializable{


    /*
    get all books purchased
     */
    @Override
    public List<Purchased> getAllPurchasedBooks() {

        List<Purchased> books = null;
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM PURCHASED";



        try{

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            books = new ArrayList<>();

            while (resultSet.next()){

                Purchased purchased = new Purchased();
                purchased.setBookName(resultSet.getString(1));
                purchased.setPrice(resultSet.getString(2));
                purchased.setDate(resultSet.getString(3));
                purchased.setQuantity(resultSet.getInt(4));

                books.add(purchased);

            }

            if(books == null){
                System.out.println("no books found in the database");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {

                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        return books;
    }







    /*
    gets a single book give its date
     */
    @Override
    public List<Purchased> getBook(String date) {

        List<Purchased> books = null;
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM PURCHASED WHERE DATE = ?";


        try {

            statement = connection.prepareStatement(query);
            statement.setString(1 , date);
            resultSet = statement.executeQuery();
            books = new ArrayList<>();

            while (resultSet.next()){

                Purchased purchased2 = new Purchased();
                purchased2.setBookName(resultSet.getString(1));
                purchased2.setPrice(resultSet.getString(2));
                purchased2.setDate(resultSet.getString(3));
                purchased2.setQuantity(resultSet.getInt(4));
                books.add(purchased2);
            }




        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }








    /*
    purchases a book
     */
    @Override
    public void addBook(Purchased book) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        final String query = "INSERT INTO PURCHASED VALUES(? , ? , CURDATE() , ?)";


        try{

                statement = connection.prepareStatement(query);
                statement.setString(1 , book.getBookName());
                statement.setString(2 , book.getPrice());
                statement.setInt(3 , book.getQuantity());
                statement.executeUpdate();

                System.out.println("book purchased");


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{

                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }







    /*
    deletes a book from the db
     */
    @Override
    public void deleteBook(String name) {


        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        final String delete = "DELETE FROM PURCHASED WHERE BOOK = ?";


        try{

            statement = connection.prepareStatement(delete);
            statement.setString(1 , name);
            statement.executeUpdate();

            System.out.println("Book deleted !!");

        }catch (Exception e){
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
    gets a book given its name
     */
    @Override
    public Purchased getBookByName(String name) {

        Purchased books = null;
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM PURCHASED WHERE BOOK = ?";


        try {

            statement = connection.prepareStatement(query);
            statement.setString(1 , name);
            resultSet = statement.executeQuery();

            if(resultSet.next()){

                books = new Purchased();
                books.setBookName(resultSet.getString(1));
                books.setPrice(resultSet.getString(2));
                books.setDate(resultSet.getString(3));
                books.setQuantity(resultSet.getInt(4));

            }




        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }
}
