package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceController {

    @FXML
    // List to store references to dynamically created TextFields
    private List<TextField> textFieldList = new ArrayList<>();
    // List to store references to dynamically created sentence panels
    public List<HBox> sentencePanelList = new ArrayList<>();
    // Map to link sentence panels to radio buttons
    private Map<HBox, RadioButton> sentencePanelToRadioButtonMap = new HashMap<>();

    private String concatenateTextFields(HBox sentencePanel) {
        HBox wordBox = (HBox) sentencePanel.lookup("#wordBox");
        StringBuilder concatenatedText = new StringBuilder();

        for (Node node : wordBox.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                concatenatedText.append(textField.getText()).append(" ");
            }
        }

        return concatenatedText.toString().trim();
    }




    @FXML
    private void onEnterKeyPressed(KeyEvent event) {
        if (event.getCode().getName().equals("Enter")) {
            addNewTextFieldToWordBox(event);
        }
    }

    private void addNewTextFieldToWordBox(KeyEvent event) {
        TextField sourceTextField = (TextField) event.getSource();

        // Create a new TextField instance
        TextField newTextField = new TextField();
        newTextField.setPrefHeight(sourceTextField.getPrefHeight());
        newTextField.setOnKeyPressed(this::onEnterKeyPressed);

        // Get the parent sentencePanel
        HBox sentencePanel = (HBox) sourceTextField.getParent();

        // Get the wordBox within the sentencePanel
        HBox wordBox = (HBox) sentencePanel.lookup("#wordBox");

        // Add the new TextField to the wordBox
        wordBox.getChildren().add(newTextField);

        // Request focus on the new TextField
        newTextField.requestFocus();

        textFieldList.add(newTextField);
        String concatenatedText = concatenateTextFields(sentencePanel);
        System.out.println("Concatenated text: " + concatenatedText);
    }

    @FXML
    private void onSentencePanelClick(MouseEvent event) {
        HBox sentencePanel = (HBox) event.getSource();
        RadioButton radioButton = sentencePanelToRadioButtonMap.get(sentencePanel);
        // Request focus on the new TextField
        sentencePanel.requestFocus();

        if (radioButton != null) {
            radioButton.setSelected(true);
        }
    }

    public void setSentencePanelToRadioButtonMapping(HBox sentencePanel, RadioButton radioButton) {
        sentencePanelToRadioButtonMap.put(sentencePanel, radioButton);
    }

    @FXML
    private void onDeleteKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        if (keyCode == KeyCode.DELETE || keyCode == KeyCode.BACK_SPACE) {
            HBox sentencePanel = sentencePanelList.get(sentencePanelList.size() - 1); // Assuming the last panel is selected
            RadioButton radioButton = sentencePanelToRadioButtonMap.get(sentencePanel);

            if (radioButton != null && radioButton.isSelected()) {
                removeLastTextField(sentencePanel);
                String concatenatedText = concatenateTextFields(sentencePanel);
                System.out.println("Concatenated text: " + concatenatedText);
            }
        }
    }



    private void removeLastTextField(HBox sentencePanel) {
        HBox wordBox = (HBox) sentencePanel.lookup("#wordBox");

        if (!textFieldList.isEmpty()) {
            System.out.println(textFieldList.size());
            TextField lastTextField = textFieldList.get(textFieldList.size() - 1);
            wordBox.getChildren().remove(lastTextField);
            textFieldList.remove(lastTextField);
        }
    }




}






