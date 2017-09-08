package MVC.Model;


/**
 * this model represent a category that a book can have the category consist
 * of two attributes the name and the description
 * getters and setters methods are provided
 */


import java.io.Serializable;

/**
 * created by : NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */


public class Category implements Serializable{


    private String name;
    private String description;


    public Category(){}


    public Category(String name , String description){
        this.name = name;
        this.description = description;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        return getName().equals(category.getName());
    }



    @Override
    public int hashCode() {
        return getName().hashCode();
    }



    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
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
}
