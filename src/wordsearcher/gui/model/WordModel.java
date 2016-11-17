/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearcher.gui.model;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pgn
 */
public class WordModel {

    /**
     * The observable list, used for data binding the view to the model.
     */
    private final ObservableList<String> items;
    
    private final StringProperty count;

    public WordModel() 
    {
        items = FXCollections.observableArrayList();
        count = new SimpleStringProperty("N/A");
    }

    /**
     * Gets the observable list of words.
     * @return 
     */
    public ObservableList<String> getWords() {
        return items;
    }

    public StringProperty getCount() {
        return count;
    }

    
    
    /**
     * Updates the word model to hold a new list of words.
     * @param words 
     */
    public void setWords(List<String> words)
    {
        items.clear();
        items.addAll(words);
        count.set(items.size() + "");
    }
    
}