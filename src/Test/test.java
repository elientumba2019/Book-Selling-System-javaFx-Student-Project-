package Test;

import Data_Access_Object.DAO_implementations.CategoryDaoImplementation;
import Data_Access_Object.DAO_implementations.PublisherDAOImplementation;
import Data_Access_Object.DAO_interfaces.CategoryDataAccessObject;
import Data_Access_Object.DAO_interfaces.PublisherDataAccessObject;
import MVC.Model.Category;

import java.util.List;

/**
 * Created by elie on 17-5-20.
 */
public class test {

    public  static void  main(String[] args) {

        PublisherDataAccessObject p = new PublisherDAOImplementation();
        CategoryDataAccessObject c = new CategoryDaoImplementation();

        // test getall
       /* List<Publisher> list = p.getAllPublishers();

        for(int c = 0 ; c < list.size() ; c++){
            System.out.println(list.get(c).getName());
        }
        */


       //test get one publisher
       /*
       try {
           Publisher pu = p.getPublisher("david");
           System.out.println(pu.getName() + " : " + pu.getTelephoneNumber());
       }catch (NullPointerException e){
           System.out.println("no data found");
       }
    */



       //test add
       /*
       Publisher po = new Publisher("Revonja" , "kapuya mpoyi" , "111" , "maried");
       p.addPublisher(po);
       */


       //test update
        /*
       Publisher pub = new Publisher("Nana" , "Elie" , "1212" , "couple");

       p.updatePublisher("Nana" , pub);
       */



        //test delete
        /*
        p.deletePublisher("Revonja");
        */


        //test category all

        /*
        List<Category> list = c.getAllCategories();
        for(int c2 = 0 ; c2 < list.size() ; c2++){
            System.out.println(list.get(c2).toString());
        }
        */


        //add category example
        /*Category category = new Category("Science" , "MVC");
        c.addNewCategory(category);
        */


        //update operation
        /*Category category = new Category("Action" , "battle");
        c.updateCategory("Science" , category);
        */

        //delete operation
        c.deleteCategory("Action");

    }

}
