package MVC.Model;

import java.io.Serializable;

/**
 * Created by elie on 17-5-19.
 */
public class Storage  implements Serializable{


    private String bookName;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;

        Storage storage = (Storage) o;

        return getBookName().equals(storage.getBookName());
    }

    @Override
    public int hashCode() {
        return getBookName().hashCode();
    }

    @Override
    public String toString() {
        return "Storage{" +
                "bookName='" + bookName + '\'' +
                ", date='" + date + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    private int quantity;



    public Storage(){}


    public Storage(String bookName , String date , int quantity){
        this.bookName = bookName;
        this.date = date;
        this.quantity = quantity;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
