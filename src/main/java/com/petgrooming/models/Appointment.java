package com.petgrooming.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.petgrooming.utils.DatabaseConnection;

public class Appointment {

    private int id;	
    private int customerId;
    private int groomerId;
    private Timestamp appointmentDate;
    private String status;
    private String remarks;
    private String customerName; 
    private String groomerName; 

    // Constructors
    public Appointment() {}

    public Appointment(int id, int customerId, int groomerId, Timestamp appointmentDate, String status, String remarks) {
        this.id = id;
        this.customerId = customerId;
        this.groomerId = groomerId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.remarks = remarks;
    }
    
    // Constructor for full details
    public Appointment(int id, int customerId, String customerName, int groomerId, String groomerName, 
                       Timestamp appointmentDate, String status, String remarks) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.groomerId = groomerId;
        this.groomerName = groomerName;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.remarks = remarks;
    }

    // Getters and setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGroomerName() {
        return groomerName;
    }

    public void setGroomerName(String groomerName) {
        this.groomerName = groomerName;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getGroomerId() { return groomerId; }
    public void setGroomerId(int groomerId) { this.groomerId = groomerId; }

    public Timestamp getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Timestamp appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    // CRUD Methods
    public static boolean addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (customerId, groomerId, appointmentDate, status, remarks) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointment.getCustomerId());
            stmt.setInt(2, appointment.getGroomerId());
            stmt.setTimestamp(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getStatus());
            stmt.setString(5, appointment.getRemarks());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateAppointment(Appointment appointment) {
        String query = "UPDATE appointments SET customerId = ?, groomerId = ?, appointmentDate = ?, status = ?, remarks = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointment.getCustomerId());
            stmt.setInt(2, appointment.getGroomerId());
            stmt.setTimestamp(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getStatus());
            stmt.setString(5, appointment.getRemarks());
            stmt.setInt(6, appointment.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAppointment(int id) {
        String query = "DELETE FROM appointments WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = """
            SELECT a.id, 
                   a.customerId, 
                   c.fullName AS customerName, 
                   a.groomerId, 
                   g.fullName AS groomerName, 
                   a.appointmentDate, 
                   a.status, 
                   a.remarks 
            FROM appointments a
            JOIN users c ON a.customerId = c.userId
            JOIN users g ON a.groomerId = g.userId
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getString("customerName"),
                        rs.getInt("groomerId"),
                        rs.getString("groomerName"),
                        rs.getTimestamp("appointmentDate"),
                        rs.getString("status"),
                        rs.getString("remarks")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
