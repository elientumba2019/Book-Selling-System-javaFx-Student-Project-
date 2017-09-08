package Data_Access_Object.DAO_implementations;

import Data_Access_Object.DAO_interfaces.PublisherDataAccessObject;
import Data_Access_Object.DataSources.DataSource;
import MVC.Model.Publisher;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elie on 17-5-20.
 */
public class PublisherDAOImplementation implements PublisherDataAccessObject , Serializable {





    //retrieves a list of all employees from the DB
    @Override
    public List<Publisher> getAllPublishers() {

        //creating a data source;
        DataSource source = new DataSource();

        //creates a connection to the DB
        Connection connection = source.getConnection();

        //statement
        Statement statement = null;

        //result set
        ResultSet resultSet = null;

        List<Publisher> publishers = new ArrayList<>();

        final String query = "SELECT * FROM PUBLISHER";

        try{


            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);


            //fetching from the result set
            while (resultSet.next()){

                Publisher p = new Publisher();
                p.setName(resultSet.getString(1));
                p.setContact(resultSet.getString(2));
                p.setTelephoneNumber(resultSet.getString(3));
                p.setDescription(resultSet.getString(4));

                publishers.add(p);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {

            try{

                if(connection != null){
                    connection.close();
                }
                if(statement != null){
                    statement.close();
                }

                resultSet.close();

            }catch (SQLException e){
                e.printStackTrace();
            }

            System.out.println("Data succesfully retrieved");
        }


        return publishers;

    }








    /*
    retrieves a single publisher from the database
     */
    @Override
    public Publisher getPublisher(String name) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        Publisher publisher = null;
        ResultSet resultSet = null;


        try{

            String query = "SELECT * FROM PUBLISHER WHERE NAME = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1 , name);
            resultSet = statement.executeQuery();


            if(resultSet.next()){

                publisher = new Publisher();
                publisher.setName(resultSet.getString(1));
                publisher.setContact(resultSet.getString(2));
                publisher.setTelephoneNumber(resultSet.getString(3));
                publisher.setDescription(resultSet.getString(4));
            }else {
                System.out.println("no data found !!!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }//closing all connections
        finally {

            try {

                connection.close();
                statement.close();
                resultSet.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return publisher;
    }








    /*
    add a single publisher to the database
     */
    @Override
    public void addPublisher(Publisher publisher) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;


        try{

            String update = "INSERT INTO PUBLISHER VALUES(? , ? , ? , ?)";

            statement = connection.prepareStatement(update);
            statement.setString(1 , publisher.getName());
            statement.setString(2 , publisher.getContact());
            statement.setString(3 , publisher.getTelephoneNumber());
            statement.setString(4 , publisher.getDescription());

            statement.executeUpdate();

            System.out.println("new Record inserted in the database");

        }catch (SQLException e){
            e.printStackTrace();
        }
        // closing all connections
        finally {

            try{

                connection.close();
                statement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }








    /*
    update an existing user using a key provides
    and uses a second publisher as substitute
     */
    @Override
    public void updatePublisher(String name , Publisher publisher) {

        String keyUpdate = name;
        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;

        try{

            String update = "UPDATE PUBLISHER SET NAME = ? , CONTACT = ? , " +
                    "TELEPHONE = ? , DESCRIPTION = ? WHERE NAME = ?";


            statement = connection.prepareStatement(update);
            statement.setString(1 , publisher.getName());
            statement.setString(2 , publisher.getContact());
            statement.setString(3 , publisher.getTelephoneNumber());
            statement.setString(4 , publisher.getDescription());
            statement.setString(5 , name);
            statement.executeUpdate();

            System.out.println("update succesfully accomplished !");

        }catch (SQLException e){
            e.printStackTrace();
        }
        //closing all connection
        finally {
            try{

                connection.close();
                statement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }







    /*
    delete a publisher provided the name of the publisher
     */
    @Override
    public void deletePublisher(String name) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;

        try{

            String delete = "DELETE FROM PUBLISHER WHERE NAME = ?";

            statement = connection.prepareStatement(delete);
            statement.setString(1 , name);
            statement.executeUpdate();


            System.out.println("1 record deleted !");

        }catch (SQLException e){
            e.printStackTrace();
        } // closing all connections
        finally {

            try{

                connection.close();
                statement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
