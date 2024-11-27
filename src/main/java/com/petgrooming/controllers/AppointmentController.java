package com.petgrooming.controllers;

import com.petgrooming.models.Appointment;
import com.petgrooming.models.User;
import com.petgrooming.utils.DatabaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class AppointmentController {

    @FXML private TextField txtCustomerId, txtGroomerId, txtRemarks;
    @FXML private DatePicker dateAppointment;
    @FXML private ComboBox<String> cmbStatus;
    @FXML private TableView<Appointment> tblAppointments;
    @FXML private TableColumn<Appointment, Integer> colId, colCustomerId, colGroomerId;
    @FXML private TableColumn<Appointment, Timestamp> colDate;
    @FXML private TableColumn<Appointment, String> colStatus, colRemarks;
    @FXML private ComboBox<User> cmbCustomer;
    @FXML private ComboBox<User> cmbGroomer;

    private ObservableList<Appointment> appointmentList;
    private ObservableList<User> customersList = FXCollections.observableArrayList();
    private ObservableList<User> groomersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colGroomerId.setCellValueFactory(new PropertyValueFactory<>("groomerName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        loadUsers();

        // Initialize status options
        cmbStatus.setItems(FXCollections.observableArrayList("Pending", "Completed", "Cancelled"));

        // Load data into table
        appointmentList = FXCollections.observableArrayList(Appointment.getAllAppointments());
        tblAppointments.setItems(appointmentList);
    }

       
    @FXML
    private void loadUsers() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Fetch customers
            String customerQuery = "SELECT * FROM users WHERE role = 'Customer'";
            ResultSet customerResult = connection.createStatement().executeQuery(customerQuery);
            while (customerResult.next()) {
                customersList.add(new User(customerResult.getInt("userId"), customerResult.getString("fullName")));
            }
            cmbCustomer.setItems(customersList);

            // Fetch groomers
            String groomerQuery = "SELECT * FROM users WHERE role = 'Groomer'";
            ResultSet groomerResult = connection.createStatement().executeQuery(groomerQuery);
            while (groomerResult.next()) {
                groomersList.add(new User(groomerResult.getInt("userId"), groomerResult.getString("fullName")));
            }
            cmbGroomer.setItems(groomersList);

            cmbCustomer.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getFullName());
                }
            });
            cmbCustomer.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getFullName());
                }
            });

            cmbGroomer.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getFullName());
                }
            });
            cmbGroomer.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getFullName());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleAddAppointment() {
        User selectedCustomer = cmbCustomer.getValue();
        User selectedGroomer = cmbGroomer.getValue();
        
        Appointment appointment = new Appointment(
            0,
            selectedCustomer.getId(),
            selectedGroomer.getId(),
            Timestamp.valueOf(dateAppointment.getValue().atStartOfDay()),
            cmbStatus.getValue(),
            txtRemarks.getText()
        );
        Appointment.addAppointment(appointment);
        refreshTable();
        clearForm();
    }

    @FXML
    private void handleUpdateAppointment() {
        Appointment selected = tblAppointments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setCustomerId(Integer.parseInt(txtCustomerId.getText()));
            selected.setGroomerId(Integer.parseInt(txtGroomerId.getText()));
            selected.setAppointmentDate(Timestamp.valueOf(dateAppointment.getValue().atStartOfDay()));
            selected.setStatus(cmbStatus.getValue());
            selected.setRemarks(txtRemarks.getText());
            Appointment.updateAppointment(selected);
            refreshTable();
            clearForm();
        }
    }

    @FXML
    private void handleDeleteAppointment() {
        Appointment selected = tblAppointments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Appointment.deleteAppointment(selected.getId());
            refreshTable();
            clearForm();
        }
    }

    @FXML
    private void clearForm() {
        txtCustomerId.clear();
        txtGroomerId.clear();
        txtRemarks.clear();
        cmbStatus.setValue(null);
        dateAppointment.setValue(null);
    }

    private void refreshTable() {
        appointmentList.setAll(Appointment.getAllAppointments());
    }
}
