package Data_Access_Object.DataSources;

import constants.Constants;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by elie on 17-5-20.
 */
public class DataSource {


    private Connection connection = null;
    private BasicDataSource source = new BasicDataSource();


    //used to create a connection to the database
    public DataSource(){

        source.setDriverClassName(Constants.JDBC_DRIVER);
        source.setUrl(Constants.DATABASE_ADDRESS);
        source.setUsername(Constants.USER);
        source.setPassword(Constants.PASSWORD);
    }


    //establishes a connection to the database
    public Connection getConnection(){

        Connection connection1 = null;

        try{

            if(connection != null){

                System.out.println("Cannot establish connection");
            }

            else {

                connection1 = source.getConnection();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return connection1;
    }
}
