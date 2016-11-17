/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearcher.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import wordsearcher.bll.BeginsWithSearch;
import wordsearcher.bll.ContainsSearch;
import wordsearcher.bll.EndsWithSearch;
import wordsearcher.bll.ExactSearch;
import wordsearcher.bll.WordManager;
import wordsearcher.gui.model.WordModel;
import wordsearcher.bll.IWordSearcher;

/**
 *
 * @author pgn
 */
public class MainController implements Initializable {

    @FXML
    private ListView lstWords;

    @FXML
    private TextField txtQuery;

    @FXML
    private RadioButton radioBegin;

    @FXML
    private ToggleGroup SearchTypes;

    @FXML
    private RadioButton radioContains;

    @FXML
    private RadioButton radioEnds;

    @FXML
    private RadioButton radioExact;

    @FXML
    private CheckBox chkCaseSensitive;

    /**
     * The word model (Part of the MVC pattern. Resides in the GUI layer.
     */
    private WordModel model;

    /**
     * The manager of words. Resides in the logic layer. Performs logical
     * operations.
     */
    private WordManager wordManager;

    @FXML
    private ComboBox<String> comboLimitation;
    @FXML
    private Label lblResultCount;

    /**
     * Constructs the Controller.
     */
    public MainController() {
        model = new WordModel();
        wordManager = new WordManager();
    }

    /**
     * Performs a word search.
     *
     * @param event
     */
    @FXML
    void handleSearch(ActionEvent event) {
        try {
            String query = txtQuery.getText().trim();
            List<String> searchResult = null;
            IWordSearcher searchStrategy;
            boolean isCaseSensitive = chkCaseSensitive.isSelected();
            if (radioBegin.isSelected()) {
                searchStrategy = new BeginsWithSearch(query, isCaseSensitive);
            } else if (radioContains.isSelected()) {
                searchStrategy = new ContainsSearch(query, isCaseSensitive);
            } else if (radioEnds.isSelected()) {
                searchStrategy = new EndsWithSearch(query, isCaseSensitive);
            } else {
                searchStrategy = new ExactSearch(query, isCaseSensitive);
            }
            searchResult = wordManager.search(searchStrategy);

            String limit = comboLimitation.getValue();
            try 
            {
                int intLimit = Integer.parseInt(limit);
                searchResult = searchResult.subList(0, intLimit);
            } catch (NumberFormatException | IndexOutOfBoundsException nfe) {
                //Do nothing
            }
            model.setWords(searchResult);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Initializes the controller to the default setting.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboLimitation.setItems(FXCollections.observableArrayList("No limit", "10", "20", "50", "100"));
        comboLimitation.getSelectionModel().select(0);

        //Databinding:
        lstWords.setItems(model.getWords());
        lblResultCount.textProperty().bind(model.getCount().asString());
        
        try {
            List<String> allWords = wordManager.getAllWords();
            model.setWords(allWords);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
