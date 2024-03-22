/* Vaughn Deutsch
 * March 21, 2024
 * CSE 360 Dr. Carter
 */

package cse360_homework4;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class patientview extends VBox {

    private TextField totalAgatstonField;
    private TextField lmField;
    private TextField ladField;
    private TextField lcxField;
    private TextField rcaField;
    private TextField pdaField;

    public patientview() {
    	//Label declarations
        Label patientGreetingLabel = new Label("Hello ");
        Label patientNameLabel = new Label("");
        Label totalAgatstonLabel = new Label("Total Agatston CAC score ");
        Label lmLabel = new Label("LM: ");
        Label ladLabel = new Label("LAD: ");
        Label lcxLabel = new Label("LCX: ");
        Label rcaLabel = new Label("RCA: ");
        Label pdaLabel = new Label("PDA: ");
        
        //text field declarations
        totalAgatstonField = new TextField();
        lmField = new TextField();
        ladField = new TextField();
        lcxField = new TextField();
        rcaField = new TextField();
        pdaField = new TextField();
        
        // The method for reading every line of the ct results of a patient and entering the information into the text-fields of this form.
        try {
            BufferedReader reader = new BufferedReader(new FileReader("31068CTResults.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Total Agatston CAC Score:")) {
                    totalAgatstonField.setText(line.split(": ")[1]);
                } else if (line.startsWith("LM:")) {
                    lmField.setText(line.split(": ")[1]);
                } else if (line.startsWith("LAD:")) {
                    ladField.setText(line.split(": ")[1]);
                } else if (line.startsWith("LCX:")) {
                    lcxField.setText(line.split(": ")[1]);
                } else if (line.startsWith("RCA :")) {
                    rcaField.setText(line.split(": ")[1]);
                } else if (line.startsWith("PDA:")) {
                    pdaField.setText(line.split(": ")[1]);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading from file: " + e.getMessage());
        }
        
        // The method for reading the first and last names from a patient's file and adding them to the patient name label.
        try {
            BufferedReader reader = new BufferedReader(new FileReader("31068_PatientInfo.txt"));
            String line;
            String firstName = "";
            String lastName = "";
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("First Name:")) {
                    firstName = line.split(": ")[1];
                } else if (line.startsWith("Last Name:")) {
                    lastName = line.split(": ")[1];
                }
            }
            reader.close();
            patientNameLabel.setText(firstName + " " + lastName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading from file: " + e.getMessage());
        }
        
        //grid pane for agatston label because it's aligned differently than everything else.
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, totalAgatstonLabel, totalAgatstonField);

        //creating hbox for table appearance 
        HBox greetingBox = new HBox(patientGreetingLabel, patientNameLabel);
        HBox lmBox = new HBox(lmLabel, lmField);
        HBox ladBox = new HBox(ladLabel, ladField);
        HBox lcxBox = new HBox(lcxLabel, lcxField);
        HBox rcaBox = new HBox(rcaLabel, rcaField);
        HBox pdaBox = new HBox(pdaLabel, pdaField);
        greetingBox.setAlignment(Pos.CENTER);

        // setting vbox padding
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setSpacing(10);

        // adding all components to vbox
        getChildren().addAll(greetingBox, gridPane, lmBox, ladBox, lcxBox, rcaBox, pdaBox);
    }
}
