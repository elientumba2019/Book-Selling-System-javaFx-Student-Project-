package MVC.Model;

/**
 * this class represent a book model
 * the book model is te same as the one that is stored in the database
 * the class has the following features
 * can create a book and provides all the getters and setters methods
 */


import java.io.Serializable;

/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public class Book implements Serializable{


    private String name;
    private String description;
    private String author;
    private String category;
    private String publisher;
    private int inStock;
    private String price;



    public Book(){}



    public Book(String name , String description , String author , String category  , String publisher , int inStock , String price){
        this.name = name;
        this.description = description;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.inStock = inStock;
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book bookModel = (Book) o;

        return getName().equals(bookModel.getName());
    }



    @Override
    public int hashCode() {
        return getName().hashCode();
    }




    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", publisher='" + publisher + '\'' +
                ", inStock=" + inStock +
                ", price='" + price + '\'' +
                '}';
    }
}
