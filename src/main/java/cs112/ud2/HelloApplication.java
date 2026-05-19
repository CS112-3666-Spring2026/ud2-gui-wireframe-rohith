package cs112.ud2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
/**
 * Unified GUI Layout and Model Integration for UD2.
 *
 * @author Rohith Mekala
 * @version 1.0
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Personal Finance Tracker");

        // 1. Title Header Region
        Label titleLabel = new Label("Personal Finance Tracker");

        // 2. Input Form Formations (Left Region)
        Label formTitle = new Label("Add New Transaction:");
        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Description (e.g., Target)");

        TextField amountInput = new TextField();
        amountInput.setPromptText("Amount (e.g., 45.00)");

        ComboBox<String> categorySelector = new ComboBox<>();
        categorySelector.getItems().addAll("Food", "Rent", "Utilities", "Entertainment", "Salary");
        categorySelector.setValue("Food"); // Safe fallback default

        CheckBox taxDeductibleCheck = new CheckBox("Tax Deductible");
        Button submitButton = new Button("Submit Transaction");

        // Collect inputs into a single vertical column
        VBox leftFormContainer = new VBox();
        leftFormContainer.setPadding(new Insets(10));
        leftFormContainer.setSpacing(10);
        leftFormContainer.getChildren().addAll(formTitle, descriptionInput, amountInput, categorySelector, taxDeductibleCheck, submitButton);

        // 3. Ledger History View Formations (Center Region)
        Label ledgerTitle = new Label("Transaction History Stream:");
        ListView<String> transactionLogDisplay = new ListView<>();
        transactionLogDisplay.getItems().add("System initial log: No entries recorded yet.");

        Label netBalanceLabel = new Label("Current Account Net Balance: $0.00");

        // Collect ledger items into a single vertical column
        VBox centerLedgerContainer = new VBox();
        centerLedgerContainer.setPadding(new Insets(10));
        centerLedgerContainer.setSpacing(10);
        centerLedgerContainer.getChildren().addAll(ledgerTitle, transactionLogDisplay, netBalanceLabel);

        categorySelector.setOnAction(event -> {
            System.out.println("Console Alert: Category dropdown changed to: " + categorySelector.getValue());
        });

        //INTEGRATE THE UD1 EXPENSE CLASS VIA BUTTON CLICK ---
        submitButton.setOnAction(event -> {
            System.out.println("\nHello! Submit button clicked.");

            //Pull the text values directly from UI input boxes
            String rawDescription = descriptionInput.getText();
            String rawAmountText = amountInput.getText();
            String chosenCategory = categorySelector.getValue();
            boolean isTaxDeductible = taxDeductibleCheck.isSelected();

            double parsedAmount = Double.parseDouble(rawAmountText);

            Expense backendLoggedExpense = new Expense(rawDescription, parsedAmount, chosenCategory, isTaxDeductible);

            System.out.println("SUCCESSFULLY BUILT MODEL CLASS OBJECT FROM LIVE FRONTEND FIELDS:");
            System.out.println(backendLoggedExpense);

            //Update our visual application list view dynamically
            transactionLogDisplay.getItems().add(backendLoggedExpense.toString());

            // Clear out input fields so they are clean for the next entry
            descriptionInput.clear();
            amountInput.clear();
        });

        //ASSEMBLE WINDOW ARCHITECTURE (BORDERPANE)
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(titleLabel);
        mainLayout.setLeft(leftFormContainer);
        mainLayout.setCenter(centerLedgerContainer);
        BorderPane.setMargin(titleLabel, new Insets(10));

        //Display the window scene
        Scene primaryWindowScene = new Scene(mainLayout, 650, 350);
        primaryStage.setScene(primaryWindowScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}