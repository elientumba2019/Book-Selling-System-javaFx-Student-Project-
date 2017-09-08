package Data_Access_Object.DAO_implementations;

import Data_Access_Object.DAO_interfaces.CategoryDataAccessObject;
import Data_Access_Object.DataSources.DataSource;
import MVC.Model.Category;

import javax.swing.text.Caret;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * dao that implements methods for managaging categories
 */


/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class CategoryDaoImplementation implements CategoryDataAccessObject , Serializable {


    /*
    retrieves all the categories availlage in the database
     */

    @Override
    public List<Category> getAllCategories() {

        final String query = "SELECT * FROM CATEGORY";
        List<Category> categoryList = new ArrayList<>();
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Category category = null;


        try{

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

                while (resultSet.next()) {

                    category = new Category();
                    category.setName(resultSet.getString(1));
                    category.setDescription(resultSet.getString(2));
                    categoryList.add(category);
            }

            if(category == null){
                    System.out.println("No data found in the database");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{

                connection.close();
                statement.close();
                resultSet.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return categoryList;
    }






    /*
    extract a single category from the database
     */
    @Override
    public Category getCategory(String name) {

        final String query = "SELECT * FROM CATEGORY WHERE NAME = ?";
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Category category = null;


        try{

            statement = connection.prepareStatement(query);
            statement.setString(1 , name);
            resultSet = statement.executeQuery();


            if(resultSet.next()){

                category = new Category();
                category.setName(resultSet.getString(1));
                category.setDescription(resultSet.getString(2));
            }
            else {
                System.out.println("No corresponding record found in the database");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {

            try{

                connection.close();
                statement.close();


            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return category;
    }








    /*
    add a new category to the
    database
     */
    @Override
    public void addNewCategory(Category category) {

        final String insert = "INSERT INTO CATEGORY VALUES(? , ?)";
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;


        try{

            statement = connection.prepareStatement(insert);
            statement.setString(1 , category.getName());
            statement.setString(2 , category.getDescription());
            statement.executeUpdate();

            System.out.println("New category added to the database !");


        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {

            try {
                if (connection != null || statement != null) {
                    connection.close();
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }









    /*
    update an existinf category
     */
    @Override
    public void updateCategory(String key, Category category) {

        final String update = "UPDATE CATEGORY SET NAME = ? , DESCRIPTION = ? WHERE NAME = ?";
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;


        try{

            statement = connection.prepareStatement(update);
            statement.setString(1 , category.getName());
            statement.setString(2 , category.getDescription());
            statement.setString(3 , key);
            statement.executeUpdate();

            System.out.println("Category successfully updated !");

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {

            try {
                if (connection != null) {
                    connection.close();
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


    }







    //deletes a category from the DB
    @Override
    public void deleteCategory(String name) {

        final String delete = "DELETE FROM CATEGORY WHERE NAME = ?";
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement;

        try{

            statement = connection.prepareStatement(delete);
            statement.setString(1 , name);
            statement.executeUpdate();

            System.out.println("Record succesfully deleted");

        }catch (Exception e){
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
