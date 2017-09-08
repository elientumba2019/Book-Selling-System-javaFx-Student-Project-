package MVC.Model;


/**
 * the model user login represent a user that is about to
 * login into the system only the user name and password are
 * retuired compared to the UserRegister that constains more than
 * 2 attributes
 */


import java.io.Serializable;

/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */

public class UserLogin implements Serializable{


    private String username;
    private String password;





    public UserLogin(){}






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLogin)) return false;

        UserLogin userLogin = (UserLogin) o;

        if (!getUsername().equals(userLogin.getUsername())) return false;
        return getPassword().equals(userLogin.getPassword());
    }





    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }




    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public UserLogin(String username , String password){
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
