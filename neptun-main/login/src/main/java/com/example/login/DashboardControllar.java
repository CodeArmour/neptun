package com.example.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class DashboardControllar implements Initializable {
    @FXML
    private Button addStudent_btn;

    @FXML
    private Button addstudent_add_btn;

    @FXML
    private Button addstudent_clear_btn;

    @FXML
    private TableColumn<StudentsData, String> addstudent_col_first_name;

    @FXML
    private TableColumn<StudentsData, String> addstudent_col_gender;

    @FXML
    private TableColumn<StudentsData, String> addstudent_col_gmail;

    @FXML
    private TableColumn<StudentsData, String> addstudent_col_last_name;

    @FXML
    private TableColumn<StudentsData, String> addstudent_col_missing;

    @FXML
    private TableColumn<StudentsData, String> addstudent_col_neptun;

    @FXML
    private TableColumn<StudentsData, String> addstudent_col_type;

    @FXML
    private Button addstudent_delete_btn;

    @FXML
    private TextField addstudent_first_name;

    @FXML
    private AnchorPane addstudent_form;

    @FXML
    private ComboBox<String> addstudent_gender;

    @FXML
    private TextField addstudent_gmail;

    @FXML
    private TextField addstudent_last_name;

    @FXML
    private TextField addstudent_missing;

    @FXML
    private TextField addstudent_neptun;

    @FXML
    private TextField addstudent_search;

    @FXML
    private TableView<StudentsData> addstudent_table;

    @FXML
    private ComboBox<String> addstudent_type;

    @FXML
    private Button addstudent_update_btn;

    @FXML
    private Button dash_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashform_student_num_lable;

    @FXML
    private Label n_student_num_lable;

    @FXML
    private Label l_student_num_lable;

    @FXML
    private Button logOut_btn;

    @FXML
    private Button mark_clear;

    @FXML
    private TableColumn<StudentsData, String> mark_col_neptun;

    @FXML
    private TableColumn<StudentsData, String> mark_col_status;

    @FXML
    private TableColumn<StudentsData, String> mark_col_sum;

    @FXML
    private TableColumn<StudentsData, String> mark_col_zh1;

    @FXML
    private TableColumn<StudentsData, String> mark_col_zh2;

    @FXML
    private Label mark_name_first;

    @FXML
    private Label mark_name_last;

    @FXML
    private TextField mark_neptun;

    @FXML
    private TableView<StudentsData> mark_table;

    @FXML
    private Label mark_type;

    @FXML
    private Button mark_update;

    @FXML
    private TextField mark_zh1_mark;

    @FXML
    private TextField mark_zh2_mark;


    @FXML
    private Button marks_btn;

    @FXML
    private AnchorPane marks_form;

    @FXML
    private Label username_menu;


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;



    public void studentsNum(){
        String sql = "select count(id) from students_info";

        DatabaseConnection connectNow = new DatabaseConnection();
        connect = connectNow.getConnection();

        int count = 0;
        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                count = result.getInt("count(id)");
            }

            dashform_student_num_lable.setText(String.valueOf(count));

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void studentsNumN(){
        String sql = "select count(neptun) from students where type = 'N'";

        DatabaseConnection connectNow = new DatabaseConnection();
        connect = connectNow.getConnection();

        int count = 0;
        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                count = result.getInt("count(neptun)");
            }

            n_student_num_lable.setText(String.valueOf(count));

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void studentsNumL(){
        String sql = "select count(neptun) from students where type = 'L'";

        DatabaseConnection connectNow = new DatabaseConnection();
        connect = connectNow.getConnection();

        int count = 0;
        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                count = result.getInt("count(neptun)");
            }

            l_student_num_lable.setText(String.valueOf(count));

        }catch (Exception e){
            System.out.println(e);
        }

    }



    public ObservableList<StudentsData> addStudentsListData(){

        ObservableList<StudentsData> listData = FXCollections.observableArrayList();
        String sql = "select * from students";

        DatabaseConnection connectNow = new DatabaseConnection();
        connect = connectNow.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery(sql);
            StudentsData studentsData;

            while (result.next()){
                studentsData = new StudentsData(result.getString("neptun"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("gender"),
                        result.getString("gmail"),
                        result.getInt("missing"),
                        result.getString("type") );

                listData.add(studentsData);

            }

        }catch (Exception e){
            System.out.println(e);
        }
        return listData;
    }

    public void addstudent_search() {

        FilteredList<StudentsData> filter = new FilteredList<>(addStudentlist, e -> true);

        addstudent_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getNeptun().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirst_name().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLast_name().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else return false;

            });
        });

        SortedList<StudentsData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addstudent_table.comparatorProperty());
        addstudent_table.setItems(sortList);

    }


    private ObservableList<StudentsData> addStudentlist;
    public void addStudentShowListData(){
        addStudentlist = addStudentsListData();

        addstudent_col_neptun.setCellValueFactory(new PropertyValueFactory<>("neptun"));
        addstudent_col_first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        addstudent_col_last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        addstudent_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addstudent_col_gmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
        addstudent_col_missing.setCellValueFactory(new PropertyValueFactory<>("missing"));
        addstudent_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));

        addstudent_table.setItems(addStudentlist);
    }

    public void addStundentSelect(){

        StudentsData studentsData = addstudent_table.getSelectionModel().getSelectedItem();
        int num = addstudent_table.getSelectionModel().getSelectedIndex();

        if ((num -1) < -1){return;}

        addstudent_neptun.setText(studentsData.getNeptun());
        addstudent_first_name.setText(studentsData.getFirst_name());
        addstudent_last_name.setText(studentsData.getLast_name());
        addstudent_gmail.setText(studentsData.getGmail());
        addstudent_missing.setText(String.valueOf(studentsData.getMissing()));

    }

    public void addMarksSelect(){
        StudentsData studentsData = mark_table.getSelectionModel().getSelectedItem();
        int num = addstudent_table.getSelectionModel().getSelectedIndex();

        if ((num -1) < -1){return;}

        mark_neptun.setText(studentsData.getNeptun());
        mark_name_first.setText(studentsData.getFirst_name());
        mark_name_last.setText(studentsData.getLast_name());
        mark_zh1_mark.setText(String.valueOf(studentsData.getZh1()));
        mark_zh2_mark.setText(String.valueOf(studentsData.getZh2()));
        mark_type.setText(studentsData.getType());



    }

    private String[] genderList = {"Male","Female"};
    public void addGenderList(){
        List<String> listG = new ArrayList<>();
        for (String data : genderList){
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        addstudent_gender.setItems(listData);
    }

    private String[] typeList = {"N","L"};
    public void addTypeList(){
        List<String> listT = new ArrayList<>();
        for (String data : typeList){
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        addstudent_type.setItems(listData);
    }

    public void setAddStudent_btnOnAction(){
        String sql = "insert into students "
                +"(neptun, first_name, last_name, gender, gmail, missing, type)"
                +" values (?,?,?,?,?,?,?)";

        DatabaseConnection connectNow = new DatabaseConnection();
        connect = connectNow.getConnection();

        try{


            if (addstudent_neptun.getText().isEmpty()
                    ||addstudent_first_name.getText().isEmpty()
                    ||addstudent_last_name.getText().isEmpty()
                    ||addstudent_gender.getSelectionModel().getSelectedItem()==null
                    ||addstudent_gmail.getText().isEmpty()
                    ||addstudent_missing.getText().isEmpty()
                    ||addstudent_type.getSelectionModel().getSelectedItem()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erorr");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();

            }else {

                String check = "select neptun from students where neptun = '" + addstudent_col_neptun.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erorr");
                    alert.setHeaderText(null);
                    alert.setContentText("the student neptun kod " + addstudent_col_neptun.getText() + " was already exist");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addstudent_neptun.getText());
                    prepare.setString(2, addstudent_first_name.getText());
                    prepare.setString(3, addstudent_last_name.getText());
                    prepare.setString(4, (String) addstudent_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addstudent_gmail.getText());
                    prepare.setString(6, addstudent_missing.getText());
                    prepare.setString(7, (String) addstudent_type.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();


                    String insertInfo = "insert into students_info "+
                            "(neptun,zh1,zh2,sum,status) "+
                            "values(?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertInfo);

                    prepare.setString(1, addstudent_neptun.getText());
                    prepare.setString(2, "0.0");
                    prepare.setString(3, "0.0");
                    prepare.setString(4, "0.0");
                    prepare.setString(5, "failed");
                    prepare.executeUpdate();


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!!!");
                    alert.showAndWait();


                    addStudentShowListData();
                    setAddstudent_clear_btnOnAction();
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setAddstudent_update_btnOnAction(){
        String sql = "update students set " +
                "first_name ='" +addstudent_first_name.getText()+"'," +
                "last_name= '"+addstudent_last_name.getText() + "'," +
                "gender = '"+addstudent_gender.getSelectionModel().getSelectedItem()+"'," +
                "gmail ='"+addstudent_gmail.getText()+"'," +
                "missing ='" +addstudent_missing.getText()+"'," +
                "type = '" +addstudent_type.getSelectionModel().getSelectedItem() +"' " +
                "where neptun = '"+addstudent_neptun.getText()+"'";


        try{

            if (addstudent_neptun.getText().isEmpty()
                    ||addstudent_first_name.getText().isEmpty()
                    ||addstudent_last_name.getText().isEmpty()
                    ||addstudent_gender.getSelectionModel().getSelectedItem()==null
                    ||addstudent_gmail.getText().isEmpty()
                    ||addstudent_missing.getText().isEmpty()
                    ||addstudent_type.getSelectionModel().getSelectedItem()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erorr");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();

            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE the "+addstudent_neptun.getText()+" data?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);



                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!!!");
                    alert.showAndWait();

                    addStudentShowListData();
                    setAddstudent_clear_btnOnAction();

                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setAddstudent_delete_btnOnAction(){

        String sql = "delete from students where neptun ='"+addstudent_neptun.getText()+"'";

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE the "+addstudent_neptun.getText()+" data?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){
                statement = connect.createStatement();
                statement.executeUpdate(sql);


                String deleteInfo ="delete from students_info where neptun= '"+addstudent_neptun.getText()+"'";
                prepare = connect.prepareStatement(deleteInfo);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!!!");
                alert.showAndWait();

                addStudentShowListData();
                setAddstudent_clear_btnOnAction();
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void setAddstudent_clear_btnOnAction(){
        addstudent_neptun.setText("");
        addstudent_first_name.setText("");
        addstudent_last_name.setText("");
        addstudent_gender.getSelectionModel().clearSelection();
        addstudent_gmail.setText("");
        addstudent_missing.setText("");
        addstudent_type.getSelectionModel().clearSelection();
    }


    public ObservableList<StudentsData> marksListData(){
        ObservableList<StudentsData> listData = FXCollections.observableArrayList();
        String sql = "select * from students_info";

        DatabaseConnection connectNow = new DatabaseConnection();
        connect = connectNow.getConnection();


        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            StudentsData studentsData;

            while (result.next()){

                studentsData = new StudentsData(
                        result.getString("neptun"),
                        result.getDouble("zh1"),
                        result.getDouble("zh2"),
                        result.getDouble("sum"),
                        result.getString("status")
                        );

                listData.add(studentsData);

            }
        }catch (Exception e){
            System.out.println(e);
        }

        return listData;

    }

    private ObservableList<StudentsData> marksList;

    public void marksListShow(){
        marksList= marksListData();

        mark_col_neptun.setCellValueFactory(new PropertyValueFactory<>("neptun"));
        mark_col_zh1.setCellValueFactory(new PropertyValueFactory<>("zh1"));
        mark_col_zh2.setCellValueFactory(new PropertyValueFactory<>("zh2"));
        mark_col_sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        mark_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        mark_table.setItems(marksList);
    }

    public void mark_zh_mark(){
        String sql = "update students_info set zh1='"+mark_zh1_mark.getText()+"',zh2='"+mark_zh2_mark.getText()+ "' where neptun = '"+addstudent_neptun.getText()+"'";

        try{

            if (mark_zh1_mark.getText().isEmpty() || mark_zh2_mark.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erorr");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();

            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE the "+addstudent_neptun.getText()+" data?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);



                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!!!");
                    alert.showAndWait();

                    marksListShow();
                    setMark_clearOnAction();

                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setMark_clearOnAction(){

        mark_neptun.setText("");
        mark_zh1_mark.setText("");
        mark_zh2_mark.setText("");
        mark_name_first.setText("");
        mark_name_last.setText("");
        mark_type.setText("");
    }







    public void swichForm(ActionEvent event){

        if (event.getSource()== dash_btn){
            dashboard_form.setVisible(true);
            addstudent_form.setVisible(false);
            marks_form.setVisible(false);

            dash_btn.setStyle("-fx-border-color: #fff;");
            addStudent_btn.setStyle("-fx-border-color: transparent;");
            marks_btn.setStyle("-fx-border-color: transparent;");

            studentsNum();
            studentsNumN();
            studentsNumL();

        }else if (event.getSource()== addStudent_btn){
            dashboard_form.setVisible(false);
            addstudent_form.setVisible(true);
            marks_form.setVisible(false);

            addStudent_btn.setStyle("-fx-border-color: #fff;");
            marks_btn.setStyle("-fx-border-color: transparent;");
            dash_btn.setStyle("-fx-border-color: transparent;");

            addGenderList();
            addTypeList();
            addstudent_search();

        }else if (event.getSource()== marks_btn){
            dashboard_form.setVisible(false);
            addstudent_form.setVisible(false);
            marks_form.setVisible(true);

            marks_btn.setStyle("-fx-border-color: #fff;");
            dash_btn.setStyle("-fx-border-color: transparent;");
            addStudent_btn.setStyle("-fx-border-color: transparent;");


            marksListShow();



        }
    }
    public  void  setLogOut_btnOnAction(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Messege");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure ?");
        Optional<ButtonType> option = alert.showAndWait();

        try{
            if (option.get().equals(ButtonType.OK)){

                logOut_btn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addStudentShowListData();
        addGenderList();
        addTypeList();


        marksListShow();
        studentsNum();
        studentsNumN();
        studentsNumL();
    }
}