package com.example.lab3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;



public class adminController implements Initializable {

    public Label demo;

    public TextField iid;
    public TextField iname;
    public TextField iposition; // Changed from idoctor to iposition
    public TextField isalary;   // Changed from iroom to isalary
    @FXML
    private TableView<Admin> tableView;
    @FXML
    private TableColumn<Admin, Integer> id;
    @FXML
    private TableColumn<Admin, String> name;
    @FXML
    private TableColumn<Admin, String> position; // Changed from doctor to position
    @FXML
    private TableColumn<Admin, Integer> salary;  // Changed from room to salary
    ObservableList<Admin> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Admin, String>("name"));
        position.setCellValueFactory(new PropertyValueFactory<Admin, String>("position")); // Updated
        salary.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("salary"));     // Updated
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        list.clear();
        tableView.setItems(list);
        populateTable();
    }

    public void populateTable() {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_Admin";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position"); // Updated
                int monthlySalary = resultSet.getInt("salary");
                int yearlySalary = monthlySalary * 12;// Updated
                tableView.getItems().add(new Admin(id, name, position,yearlySalary )); // Updated
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData(ActionEvent actionEvent) {
        String getid = iid.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_admin WHERE id= '" + getid + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the text fields with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position"); // Updated
                int monthlySalary = resultSet.getInt("salary");
                int yearlySalary = monthlySalary * 12;          // Updated

                iname.setText(name);
                iposition.setText(position); // Updated
                isalary.setText(String.valueOf(yearlySalary)); // Updated
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteData(ActionEvent actionEvent) {
        String getid = iid.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to delete data from the database
            String query = "DELETE FROM tbl_admin WHERE id= '" + getid + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(ActionEvent actionEvent) {
        String getid = iid.getText();
        String getName = iname.getText();
        String getPosition = iposition.getText(); // Updated
        String getSalary = isalary.getText();     // Updated

        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to insert data into the database
            String query = "INSERT INTO tbl_admin(name, position, salary) VALUES ('" + getName + "','" + getPosition + "','" + getSalary + "')"; // Updated
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateData(ActionEvent actionEvent) {
        String getid = iid.getText();
        String getName = iname.getText();
        String getPosition = iposition.getText(); // Updated
        String getSalary = isalary.getText();     // Updated

        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to update data in the database
            String query = "UPDATE tbl_admin SET name='" + getName + "', position='" + getPosition + "', salary='" + getSalary + "' WHERE id = '" + getid + "'"; // Updated
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void logoutClick(ActionEvent actionEvent) {
        try {
            // Load the dashboard FXML file
            Parent dashboardScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

            // Set up the new stage for the login screen
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Dashboard");
            dashboardStage.setScene(new Scene(dashboardScene));

            // Check if the current stage can be accessed
            if (demo.getScene() != null) {
                // Close the current dashboard window
                Stage currentStage = (Stage) demo.getScene().getWindow();
                currentStage.close();
            } else {
                System.out.println("The 'demo' label is not part of an active scene.");
            }

            // Show the login screen
            dashboardStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



