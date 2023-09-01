package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinibarController {

    @FXML
    private VBox tester;

    private ToggleGroup globalToggleGroup = new ToggleGroup();

    private int radioButtonCounter = 1; // Counter for generating unique IDs



    @FXML
    private void onClearButtonClick() {
        // Retain the first sentence panel and remove the rest
        int numberOfPanels = tester.getChildren().size();
        if (numberOfPanels > 1) {
            tester.getChildren().remove(1, numberOfPanels);
            // reset the counter for the next radioButton ID
            radioButtonCounter = 1;

        }
    }

    @FXML
    private void onAddButtonClick() {
        // Create a new HBox instance from the provided FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sentence.fxml"));
        try {
            HBox sentencePanel = loader.load();
            SentenceController sentenceController = loader.getController();

            // Get access to the individual elements within the HBox
            RadioButton radioButton = (RadioButton) loader.getNamespace().get("radioButton1");
            TextField textField = (TextField) loader.getNamespace().get("textField");
            //Button deleteButton = (Button) loader.getNamespace().get("deleteButton");

            // Set alignment to center the HBox within the tester VBox
            sentencePanel.setAlignment(Pos.CENTER);

            // Set margin to position the HBox within the tester VBox
            tester.setMargin(sentencePanel, new Insets(5, 0, 5, 4));



            // Assign a unique ID to the radio button
            radioButton.setId("radioButton" + radioButtonCounter);

            // Increment the counter for the next ID
            radioButtonCounter++;

            // Assign the radio button to the global ToggleGroup
            radioButton.setToggleGroup(globalToggleGroup);

            // Add the new HBox to the tester VBox
            tester.getChildren().add(sentencePanel);

            // Add the sentence panel to the list
            sentenceController.sentencePanelList.add(sentencePanel);

            // Set mapping between sentence panel and radio button
            sentenceController.setSentencePanelToRadioButtonMapping(sentencePanel, radioButton);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
