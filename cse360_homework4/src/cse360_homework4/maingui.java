/* Vaughn Deutsch
 * March 21, 2024
 * CSE 360 Dr. Carter
 */

package cse360_homework4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
public class maingui extends Application {

    @Override
    public void start(Stage primaryStage) {
        // label declaration
        Label titleLabel = new Label("Welcome to Heart and Health Imaging and Recording System");
        
        // button declarations
        Button patientIntakeButton = new Button("Patient Intake");
        Button ctScanTechViewButton = new Button("CT Scan Tech View");
        Button patientViewButton = new Button("Patient View");
        
        // buttons that redirect user to the different GUI pages.
        patientIntakeButton.setOnAction(e -> openPatientIntakeForm(primaryStage));
        ctScanTechViewButton.setOnAction(e -> openCTScanTechViewForm(primaryStage));
        patientViewButton.setOnAction(e -> openPatientViewForm(primaryStage));
        
        // defines the layout and adds components.
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(titleLabel, patientIntakeButton, ctScanTechViewButton, patientViewButton);
        
        // creates the scene
        Scene scene = new Scene(root, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Heart and Health Imaging System");
        primaryStage.show();
    }
    
    //method for redirecting the user to the patient intake form when they press the patient intake form button.
    private void openPatientIntakeForm(Stage primaryStage) {
        Stage intakeStage = new Stage(); 
        patientintakeform intakeForm = new patientintakeform(); 
        Scene intakeScene = new Scene(intakeForm, 400, 300); 
        intakeStage.setScene(intakeScene); 
        intakeStage.setTitle("Patient Intake Form"); 
        intakeStage.initOwner(primaryStage); 
        //closes the main gui and opens ct scan tech view.
        primaryStage.close();
        intakeStage.show(); 
    }
    
    //method for redirecting the user to the CT Scan Tech view form when they click ct scan tech view button.
    private void openCTScanTechViewForm(Stage primaryStage) {
        Stage ctscantechviewStage = new Stage(); 
        ctscantechview ctscantechviewForm = new ctscantechview(); 
        Scene intakeScene = new Scene(ctscantechviewForm, 400, 300); 
        ctscantechviewStage.setScene(intakeScene); 
        ctscantechviewStage.setTitle("CT Scan Tech View"); 
        ctscantechviewStage.initOwner(primaryStage); 
        //closes the main gui and opens ct scan tech view.
        primaryStage.close();
        ctscantechviewStage.show(); 
    }
    
    //method for redirecting the user to the patient view when they click the patient view button. 
    private void openPatientViewForm(Stage primaryStage) {
        Stage patientviewStage = new Stage();
        patientview patientviewForm = new patientview(); 
        Scene intakeScene = new Scene(patientviewForm, 400, 300);
        patientviewStage.setScene(intakeScene);
        patientviewStage.setTitle("CT Scan Tech View");
        patientviewStage.initOwner(primaryStage); 
        //closes the main gui and opens patient view
        primaryStage.close();
        patientviewStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}