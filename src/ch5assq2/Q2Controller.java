/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5assq2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author dinahabib
 */
public class Q2Controller implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> tcId;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Integer> tcGrade;
    @FXML
    private Button buttonShow;
    private EntityManagerFactory emf;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcId.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        this.emf=Persistence.createEntityManagerFactory("Ch5AssPU");
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) {
        if(textArea.getText().equals("Select * From Student Where major='Software Engineering'")){
            EntityManager em = emf.createEntityManager();
        List<Student> student=em.createNamedQuery("Student.findByMajor").getResultList();
        tableView.getItems().setAll(student);
        em.close();
        }else if(textArea.getText().equals("Select * From Student Where grade >90")){
             EntityManager em = emf.createEntityManager();
        List<Student> student=em.createNamedQuery("Student.findByGrade").getResultList();
        tableView.getItems().setAll(student);
        em.close();
        }else if(textArea.getText().equals("Select all pass students in ascending order based on their names")){
            EntityManager em = emf.createEntityManager();
        List<Student> student=em.createNamedQuery("Student.findByPassGrade").getResultList();
        tableView.getItems().setAll(student);
        em.close();
        }
    }
    
}
