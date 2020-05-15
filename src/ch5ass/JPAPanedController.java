/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5ass;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author dinahabib
 */
public class JPAPanedController implements Initializable {

    @FXML
    private TextField textFieldStId;
    @FXML
    private TextField textFieldCoId;
    @FXML
    private TextField textFieldSemester;
    @FXML
    private TableView<Registration> tableView;
    @FXML
    private TableColumn<Registration, Integer> tcStId;
    @FXML
    private TableColumn<Registration, Integer> tcCoId;
    @FXML
    private TableColumn<Registration, String> tcSemester;
    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonAdd;
    private EntityManagerFactory emf;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcStId.setCellValueFactory(new PropertyValueFactory("studentId"));
        tcCoId.setCellValueFactory(new PropertyValueFactory("courseId"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
        this.emf=Persistence.createEntityManagerFactory("Ch5AssPU");
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Registration> registration=em.createNamedQuery("Registration.findAll").getResultList();
        tableView.getItems().setAll(registration);
        em.close();
    }

    @FXML
    private void buttonAddHandle(ActionEvent event) {
        Registration registrationt=new Registration();
        registrationt.setStudentId(Integer.parseInt(textFieldStId.getText()));
        registrationt.setCourseId(Integer.parseInt(textFieldCoId.getText()));
        registrationt.setSemester(textFieldSemester.getText());
        EntityManager em=this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(registrationt);
        em.getTransaction().commit();
        em.close();
    }
    
}
