/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearcher.bll;

/**
 *
 * @author pgn
 */
public class BeginsWithSearch extends SearchTemplate {

    public BeginsWithSearch(String query) {
        super(query, false);
    }

    @Override
    public boolean compare(String word) {
        if(!caseSensitive) 
        {
            return word.toLowerCase().startsWith(query.toLowerCase());
        }
        else
        {
            return word.startsWith(query);
        }
    }

}
