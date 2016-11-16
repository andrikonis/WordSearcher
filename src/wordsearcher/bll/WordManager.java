/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearcher.bll;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import wordsearcher.dal.WordDAO;

/**
 *
 * @author pgn
 */
public class WordManager {

    private WordDAO wordDAO;

    /**
     * Constructs a wordManager object.
     */
    public WordManager() {
        wordDAO = new WordDAO();
        
    }

    /**
     * Gets all the searchable words.
     * @return List of all words;
     */
    public List<String> getAllWords() throws FileNotFoundException {
        List<String> allWords = wordDAO.getAllWords();
        return allWords;
    }

    /**
     * Performs the search for words 
     * @param query
     * @return 
     */
    public List<String> beginSearch(String query) throws FileNotFoundException 
    {
        List<String> allWords = getAllWords();
        List<String> result = new ArrayList<>();
        for(String word : allWords)
        {
            if(word.startsWith(query))
            {
                result.add(word);
            }
        }
        return result;
    }

    public List<String> containsSearch(String query) {
        return null;
    }

    public List<String> endsWithSearch(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> exactSearch(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
