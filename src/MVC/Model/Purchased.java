package MVC.Model;

import java.io.Serializable;


/**
 * the purchased model represent a book that is going to be stored in the
 * purchased class that is located in the database
 */



/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class Purchased implements Serializable{


    private String bookName;
    private String price;
    private String date;
    private int quantity;



    public Purchased(){}


    public Purchased(String bookName , String price , String date , int quantity){
        this.bookName = bookName;
        this.price = price;
        this.date = date;
        this.quantity = quantity;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchased)) return false;

        Purchased purchased = (Purchased) o;

        return getBookName().equals(purchased.getBookName());
    }




    @Override
    public int hashCode() {
        return getBookName().hashCode();
    }



    @Override
    public String toString() {
        return "Purchased{" +
                "bookName='" + bookName + '\'' +
                ", price='" + price + '\'' +
                ", date='" + date + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
