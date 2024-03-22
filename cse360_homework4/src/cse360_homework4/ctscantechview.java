/* Vaughn Deutsch
 * March 21, 2024
 * CSE 360 Dr. Carter
 */

package cse360_homework4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ctscantechview extends VBox {

    private TextField patientIDField;
    private TextField totalAgatstonField;
    private TextField lmField;
    private TextField ladField;
    private TextField lcxField;
    private TextField rcaField;
    private TextField pdaField;

    public ctscantechview() {
        // Create labels
        Label patientIDLabel = new Label("Patient ID: ");
        Label totalAgatstonLabel = new Label("Total Agatston CAC score ");
        Label vesselLevelAgatstonLabel = new Label("Vessel level Agatston CAC score ");
        Label lmLabel = new Label("LM: ");
        Label ladLabel = new Label("LAD: ");
        Label lcxLabel = new Label("LCX: ");
        Label rcaLabel = new Label("RCA: ");
        Label pdaLabel = new Label("PDA: ");

        // Create text fields
        patientIDField = new TextField();
        totalAgatstonField = new TextField();
        lmField = new TextField();
        ladField = new TextField();
        lcxField = new TextField();
        rcaField = new TextField();
        pdaField = new TextField();

        // Create save button
        Button saveButton = new Button("Save");
        
        //This button creates a new file based on the patient's ID and enters the data from the text fields into the text file.
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validateFields()) {
                    String patientID = patientIDField.getText();
                    String fileName = patientID + "CTResults.txt";
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                        writer.write("Total Agatston CAC Score: " + totalAgatstonField.getText() + "\n");
                        writer.write("LM: " + lmField.getText() + "\n");
                        writer.write("LAD: " + ladField.getText() + "\n");
                        writer.write("LCX: " + lcxField.getText() + "\n");
                        writer.write("RCA : " + rcaField.getText() + "\n");
                        writer.write("PDA: " + pdaField.getText() + "\n");
                        writer.close();
                        System.out.println("File created successfully: " + fileName); // debug message

                        Stage stage = (Stage) saveButton.getScene().getWindow();
                        stage.close();

                        // Open the maingui
                        maingui mainGUI = new maingui();
                        Stage newStage = new Stage();
                        mainGUI.start(newStage);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.err.println("Error creating file: " + e.getMessage()); // debug error message
                    }
                } else {
                    System.err.println("Error: Please fill in all fields."); // error message if any of the fields are empty
                }
            }
        });
        
        
        //Gridpane because these items must be aligned differently than those in the hbox.
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, patientIDLabel, patientIDField);
        gridPane.addRow(1, totalAgatstonLabel, totalAgatstonField);

        // hboxes because these values have their own alignment differing from that in the gridpane.
        HBox vesselLevelAgatstonBox = new HBox(vesselLevelAgatstonLabel);
        HBox lmBox = new HBox(lmLabel, lmField);
        HBox ladBox = new HBox(ladLabel, ladField);
        HBox lcxBox = new HBox(lcxLabel, lcxField);
        HBox rcaBox = new HBox(rcaLabel, rcaField);
        HBox pdaBox = new HBox(pdaLabel, pdaField);
        
        // Add save button to HBox for alignment
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        VBox.setMargin(buttonBox, new Insets(-20, 0, 0, 0)); // the top margin changed so the save button properly appears

        // padding and alignment for the vbox
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setSpacing(10);

        // adds all components to the vbox
        getChildren().addAll(gridPane, vesselLevelAgatstonBox,
                lmBox, ladBox, lcxBox, rcaBox, pdaBox, buttonBox);
    }
    
    private boolean validateFields() {
        if (patientIDField.getText().isEmpty() ||
                totalAgatstonField.getText().isEmpty() ||
                lmField.getText().isEmpty() ||
                ladField.getText().isEmpty() ||
                lcxField.getText().isEmpty() ||
                rcaField.getText().isEmpty() ||
                pdaField.getText().isEmpty()) {
            return false; // if any field is empty, return false
        }
        return true; //if they're all full, return true
    }
}
