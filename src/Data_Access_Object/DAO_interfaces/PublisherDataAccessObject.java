package Data_Access_Object.DAO_interfaces;

import MVC.Model.Publisher;

import java.util.List;

/**
 * Created by NSAMPI NTUMBA ELIE
 * STUDENT ID : 201532120148
 */
public interface PublisherDataAccessObject {

    //get a list of all publishers in the DB
    List<Publisher> getAllPublishers();

    //get a Sigle publisher
    Publisher getPublisher(String name);

    //add a publisher to the DB
    void addPublisher(Publisher publisher);

    //update a publisher in the DB
    void updatePublisher(String name, Publisher publisher);

    //delete a publisher in theDB
    void deletePublisher(String name);
}
