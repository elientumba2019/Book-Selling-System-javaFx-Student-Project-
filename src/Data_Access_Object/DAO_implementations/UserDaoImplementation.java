package Data_Access_Object.DAO_implementations;

import Data_Access_Object.DAO_interfaces.UserDataAccessObject;
import Data_Access_Object.DataSources.DataSource;
import MVC.Model.UserLogin;
import MVC.Model.UserRegister;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by elie on 17-5-24.
 */
public class UserDaoImplementation implements UserDataAccessObject  , Serializable{



    /*
    checks whether the user is
    in the Db
     */
    @Override
    public boolean isUser(UserLogin user) {

        UserRegister userRegister = null;

        userRegister = getUser(user.getUsername());


        if(userRegister == null){
            return false;
        }


        return  true;
    }







    /*
    registers a new user to the database
     */
    @Override
    public boolean registerUser(UserRegister user) {

        if(!user.valid()){
            return  false;
        }

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        final String insert = "INSERT INTO USERS VALUES(? , ? , ? , ?)";

        try{

            statement = connection.prepareStatement(insert);
            statement.setString(1 , user.getFirstName());
            statement.setString(2 , user.getLastName());
            statement.setString(3 , user.getUsername());
            statement.setString(4 , user.getPassword());
            statement.executeUpdate();


            System.out.println("User succesfully registered !");

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

        return  true;
    }





    /*
    gets a user from the db
     */
    @Override
    public UserRegister getUser(String username) {

        DataSource source = new DataSource();
        Connection connection = source.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM USERS WHERE USERNAME = ?";
        UserRegister user = null;


        try{

            statement = connection.prepareStatement(query);
            statement.setString(1 , username);
            resultSet = statement.executeQuery();


            while (resultSet.next()){
                user = new UserRegister();

                user.setFirstName(resultSet.getString(1));
                user.setLastName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));

            }

            if(user == null){
                System.out.println("the user does not exist ! ");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }


}
