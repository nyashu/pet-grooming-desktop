# **Pet Grooming Service Application**

A JavaFX desktop application to manage pet grooming services, using MySQL as the database.

---

## **Prerequisites**
Before you begin, ensure you have the following tools installed:

1. **JDK 17+**: [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)  
2. **Eclipse IDE**: [Download Eclipse](https://www.eclipse.org/downloads/)  
3. **MySQL 8.0+**: [Download MySQL](https://dev.mysql.com/downloads/)  
4. **JavaFX SDK 17+**: [Download JavaFX](https://gluonhq.com/products/javafx/)

---

## **Setup Instructions**

### **1. Clone the Repository**
Clone the repository to your local machine:
```bash
git clone https://github.com/nyashu/pet-grooming-desktop.git
```

### **2. Import the Project into Eclipse**
1. Open Eclipse and go to File → Import → Existing Projects into Workspace.
2. Select the cloned project folder.
3. Add the JavaFX SDK:
    - Go to Project → Properties → Java Build Path → Libraries → Add External JARs.
    - Select the lib folder from your JavaFX SDK installation.


### **3. Configure the MySQL Database**
1. Create the database:
```sql
CREATE DATABASE petgroomingdb;
```

2. Import the provided petgroomingdb.sql file:
```sql
mysql -u <username> -p petgroomingdb < petgroomingdb.sql
```

3. Update the Hibernate.cfg.xml file with your MySQL credentials
```sql
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/petgroomingdb</property>
<property name="hibernate.connection.username">username</property>
<property name="hibernate.connection.password">password</property>
```

### **4. Run the Application**
1. Configure JavaFX VM Arguments: Go to Run Configurations → Arguments → VM Arguments, and add:
```bash
--module-path "path_to_javafx_lib" --add-modules javafx.controls,javafx.fxml
```
2. Right-click on Main.java → Run As → Java Application.

## **Features**

The Pet Grooming Service application provides functionality based on user roles. Below are the features available for each role:

---

### **Admin**
- Manage users:
- Add, update, and delete customer, groomer, and staff accounts.
- Manage grooming service schedules and appointments.
- Assign groomers to appointments and track service details.
- Track payments, generate reports, and manage business growth.
- Assign roles and permissions to groomers and customers.

---

### **Groomer**
- View assigned appointments and manage schedule.
- Update grooming service status and progress.
- Submit invoices for services rendered.
- Manage personal profile and availability.

---

### **Customer**
- Request grooming services and schedule appointments.
- View appointment history and manage future bookings.
- Make payments and view invoice history.
- Update personal details and manage account settings.

