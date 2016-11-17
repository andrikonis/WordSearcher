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
public class EndsWithSearch extends QuerySearchTemplate {

    public EndsWithSearch(String query, boolean caseSensitive) {
        super(query, caseSensitive);
    }

    @Override
    public boolean compare(String word) {
        if (caseSensitive) {
            return word.endsWith(query);
        } else {
            return word.toLowerCase().endsWith(query.toLowerCase());
        }
    }

}
