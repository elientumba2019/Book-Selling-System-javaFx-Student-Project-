package Data_Access_Object.DAO_implementations;

import Data_Access_Object.DAO_interfaces.BookDataAccessObject;
import Data_Access_Object.DataSources.DataSource;
import MVC.Model.Book;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * thie DAO implement methods for creating adding and deleting
 * a book from the database
 */


/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class BookDaoImplementation implements BookDataAccessObject , Serializable {



    /*
    get all the books in the DB
     */
    @Override
    public List<Book> getAllBooks() {


        List<Book> list = null;
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM BOOK";



        try{

            list = new ArrayList<>();
            statement  = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                Book book = new Book();
                book.setName(resultSet.getString(1));
                book.setDescription(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setCategory(resultSet.getString(4));
                book.setPublisher(resultSet.getString(5));
                book.setInStock(resultSet.getInt(6));
                book.setPrice(resultSet.getString(7));

                list.add(book);
            }


            if(list == null){
                System.out.println("database empty !");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{

                connection.close();
                resultSet.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return list;
    }







    /*
    return a book given its name
     */
    @Override
    public Book getBookByName(String name) {

        final String query = "SELECT * FROM BOOK WHERE NAME = ?";
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet  = null;
        Book book = null;


        try{

            statement = connection.prepareStatement(query);
            statement.setString(1 , name);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                book = new Book(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7));
            }


            System.out.println("Book retrieved from the db");

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{

                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        return  book;
    }






    /*
    get books given the name of the author
     */
    @Override
    public List<Book> getBooksByAuthor(String author) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Book> books = null;
        final String query = "SELECT * FROM BOOK WHERE AUTHOR = ?";



        try{

            statement = connection.prepareStatement(query);
            statement.setString(1 , author);
            ResultSet resultSet = statement.executeQuery();

            books = new ArrayList<>();

            while (resultSet.next()){

                Book book = new Book(resultSet.getString(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getString(5) ,
                        resultSet.getInt(6) ,
                        resultSet.getString(7));
                books.add(book);

                System.out.println("Books found ");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try
            {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return books;
    }







    /*
    return books givem their category
     */
    @Override
    public List<Book> getBooksByCategory(String category) {


        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Book> books = null;
        final String query = "SELECT * FROM BOOK WHERE CATEGORY = ?";



        try{

            statement = connection.prepareStatement(query);
            statement.setString(1 , category);
            ResultSet resultSet = statement.executeQuery();

            books = new ArrayList<>();

            while (resultSet.next()){

                Book book = new Book(resultSet.getString(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getString(5) ,
                        resultSet.getInt(6) ,
                        resultSet.getString(7));
                books.add(book);
            }


            System.out.println("Books  found by category");

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try
            {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return books;
    }






    /*
    returns all books given the publisher name
     */
    @Override
    public List<Book> getBooksByPublisher(String publisher) {



        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Book> books = null;
        final String query = "SELECT * FROM BOOK WHERE PUBLISHER = ?";



        try{

            statement = connection.prepareStatement(query);
            statement.setString(1 , publisher);
            ResultSet resultSet = statement.executeQuery();

            books = new ArrayList<>();

            while (resultSet.next()){

                Book book = new Book(resultSet.getString(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getString(5) ,
                        resultSet.getInt(6) ,
                        resultSet.getString(7));
                books.add(book);
            }


            System.out.println("Books found by publisher");

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try
            {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return books;
    }






    /*
    adds a new book to the database
     */
    @Override
    public void addBook(Book book) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        final String insert = "INSERT INTO BOOK VALUES(? , ? , ? , ? , ? , ? , ?)";


        try{

            statement = connection.prepareStatement(insert);
            statement.setString(1 , book.getName());
            statement.setString(2 , book.getDescription());
            statement.setString(3 , book.getAuthor());
            statement.setString(4 , book.getCategory());
            statement.setString(5 , book.getPublisher());
            statement.setInt(6 , book.getInStock());
            statement.setString(7 , book.getPrice());
            statement.executeUpdate();

            System.out.println("new Book added to the database");

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {

            try {

                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }




    /*
    updates a book in  the database
     */
    @Override
    public void update(String name, Book newBook) {


        String key = name;
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        final String query = "UPDATE BOOK SET NAME = ? , DESCRIPTION = ? , AUTHOR = ? , CATEGORY = ? , " +
                "PUBLISHER = ? , INSTOCK = ? , PRICE = ? WHERE NAME = ?";


        try{

            statement = connection.prepareStatement(query);
            statement.setString(1 , newBook.getName());
            statement.setString(2 , newBook.getDescription());
            statement.setString(3 , newBook.getAuthor());
            statement.setString(4 , newBook.getCategory());
            statement.setString(5 , newBook.getPublisher());
            statement.setInt(6 , newBook.getInStock());
            statement.setString(7 , newBook.getPrice());
            statement.setString(8 , key);

            statement.executeUpdate();

            System.out.println("updated");

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try
            {
                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                try {

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }







    /*
    deletes a book
     */
    @Override
    public void deleteBook(String name) {

        String key = name;
        final String delete = "DELETE FROM BOOK WHERE NAME = ?";
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;


        try{

            statement = connection.prepareStatement(delete);
            statement.setString(1 , key);
            statement.executeUpdate();


            System.out.println("deleted !");

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{

                connection.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
