/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5ass;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author dinahabib
 */
public class JPAPaneController implements Initializable {

    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldMajor;
    @FXML
    private TextField textFieldGrade;
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
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonAddCourse;
    
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
        EntityManager em = emf.createEntityManager();
        List<Student> student=em.createNamedQuery("Student.findAll").getResultList();
        tableView.getItems().setAll(student);
        em.close();
    }

    @FXML
    private void buttonAddHandle(ActionEvent event) {
        Student student=new Student();
        student.setId(Integer.parseInt(textFieldId.getText()));
        student.setName(textFieldName.getText());
        student.setMajor(textFieldMajor.getText());
        student.setGrade(Integer.parseInt(textFieldGrade.getText()));
        EntityManager em=this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void buttonUpdateHandle(ActionEvent event) {
        EntityManager em=this.emf.createEntityManager();
        Student s=em.find(Student.class, tableView.getSelectionModel().getSelectedItem().getId());
        s.setName(textFieldName.getText());
        s.setMajor(textFieldMajor.getText());
        s.setGrade(Integer.parseInt(textFieldGrade.getText()));
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void buttonDeleteHandle(ActionEvent event) {
        EntityManager em=this.emf.createEntityManager();
        Student s=em.find(Student.class, tableView.getSelectionModel().getSelectedItem().getId());
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void buttonAddCourseHandle(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("JPAPaned.fxml"));
        Parent root=(Parent) fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
