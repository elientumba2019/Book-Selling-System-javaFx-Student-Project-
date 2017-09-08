package Data_Access_Object.DAO_interfaces;

import MVC.Model.Category;

import java.util.List;
/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public interface CategoryDataAccessObject {

    //get all categories
    List<Category> getAllCategories();

    //get a single category
    Category getCategory(String name);

    //add category
    void addNewCategory(Category category);

    //update a single category
    void updateCategory(String key, Category category);

    //delete a category
    void deleteCategory(String name);
}
