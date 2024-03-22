/* Vaughn Deutsch
 * March 21, 2024
 * CSE 360 Dr. Carter
 */

package cse360_homework4;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class patientintakeform extends VBox {

	// text field declarations
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField phoneNumberField;
    private TextField healthHistoryField;
    private TextField insuranceIDField;

    public patientintakeform() {
        // label declarations
        Label titleLabel = new Label("Patient Intake Form");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label emailLabel = new Label("Email:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label healthHistoryLabel = new Label("Health History:");
        Label insuranceIDLabel = new Label("Insurance ID:");

        // text field initialization
        firstNameField = new TextField();
        lastNameField = new TextField();
        emailField = new TextField();
        phoneNumberField = new TextField();
        healthHistoryField = new TextField();
        insuranceIDField = new TextField();

        // save button declaration
        Button saveButton = new Button("Save");
        
        //This button creates a new file based on the patient's ID and enters the data from the text fields into the text file.
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String patientID = generatePatientID();
                String fileName = patientID + "_PatientInfo.txt";
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write("First Name: " + firstNameField.getText() + "\n");
                    writer.write("Last Name: " + lastNameField.getText() + "\n");
                    writer.write("Email: " + emailField.getText() + "\n");
                    writer.write("Phone Number: " + phoneNumberField.getText() + "\n");
                    writer.write("Health History: " + healthHistoryField.getText() + "\n");
                    writer.write("Insurance ID: " + insuranceIDField.getText() + "\n");
                    writer.close();
                    System.out.println("File created successfully: " + fileName); // debug message
                    
                    //closes the intake form gui
                    Stage stage = (Stage) saveButton.getScene().getWindow();
                    stage.close();

                    //opens the main gui
                    maingui mainGUI = new maingui();
                    Stage newStage = new Stage();
                    mainGUI.start(newStage);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error creating file: " + e.getMessage()); // error message
                }
            }
        });
        
        

        // the labels and text fields in a grid pane for the sake of appearing like a table.
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, firstNameLabel, firstNameField);
        gridPane.addRow(1, lastNameLabel, lastNameField);
        gridPane.addRow(2, emailLabel, emailField);
        gridPane.addRow(3, phoneNumberLabel, phoneNumberField);
        gridPane.addRow(4, healthHistoryLabel, healthHistoryField);
        gridPane.addRow(5, insuranceIDLabel, insuranceIDField);

        // save button in hbox alignment so it can sit in the bottom right
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        // Set padding and alignment for the main VBox
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setSpacing(10);

        // Adds all the components to the vbox
        getChildren().addAll(titleLabel, gridPane, buttonBox);
    }
    
    //Method for making a random 5 digit patient ID.
    private String generatePatientID() {
        Random random = new Random();
        int patientIDNumber = random.nextInt(90000) + 10000; // Generate a random number between 10000 and 99999
        return String.valueOf(patientIDNumber);
    }

}