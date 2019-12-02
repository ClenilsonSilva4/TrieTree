package edb.imd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class TrieTree {
    private TrieNode treeRoot;
    @FXML private TextField wordPrefix;
    @FXML private TextArea wordsSuggestions;
    private int wordsAdded;

    public TrieTree() {
        this.treeRoot = new TrieNode();
        wordsAdded = 0;
    }

    public void insertWord(String wordToInsert) {
        TrieNode insertingWord = treeRoot;
        wordToInsert = wordToInsert.toLowerCase();
        if(!search(wordToInsert)) {
            for(int i = 0; i < wordToInsert.length(); i++) {
                if(i + 1 == wordToInsert.length()) {
                    insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), true));
                } else {
                    insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), false));
                }
            }

            wordsAdded++;
        }
    }

    private TrieNode searchPrefix(String wordToSearch) {
        TrieNode searchingWord = treeRoot;
        wordToSearch = wordToSearch.toLowerCase();

        for(int i = 0; i < wordToSearch.length() && searchingWord != null; i++) {
            searchingWord = searchingWord.checkIfHasChild(wordToSearch.charAt(i));
        }

        return searchingWord;
    }

    public boolean search(String wordToSearch) {
        wordToSearch = wordToSearch.toLowerCase();
        TrieNode searchWord = searchPrefix(wordToSearch);

        if(searchWord == null || searchWord.getText() == null) {
            return false;
        }

        return searchWord.getText().equals(wordToSearch);
    }

    public void autoComplete(String wordPrefix) {
        autoComplete(wordPrefix, wordsAdded);
    }

    public ArrayList<String> autoComplete(String wordPrefix, int wordsQuantities) {
        wordPrefix = wordPrefix.toLowerCase();
        TrieNode autoCompleteNode = searchPrefix(wordPrefix);

        if(autoCompleteNode == null) {
            return null;
        }

        ArrayList<String> wordsSuggestions = new ArrayList<>();
        autoCompleteNode.getWord(wordsQuantities, wordsSuggestions);
        ArrayList<String> suggestionsList = new ArrayList<>();

        while(!wordsSuggestions.isEmpty()) {
            int minorWordSize = wordsSuggestions.get(0).length();
            int index = 0;
            for (int i = 0; i < wordsSuggestions.size(); i++) {
                if(wordsSuggestions.get(i).length() < minorWordSize) {
                    minorWordSize = wordsSuggestions.get(i).length();
                    index = i;
                }
            }
            suggestionsList.add(wordsSuggestions.get(index));
            wordsSuggestions.remove(index);
        }

        return suggestionsList;
    }

    public void deleteWord(String wordToDelete) {
        TrieNode deleteWord = searchPrefix(wordToDelete);

        if(deleteWord == null || wordToDelete.length() == 0) {
            return;
        }

        if(deleteWord.getChildrenQuantity() == 0 && deleteWord.isWord()) {
            int index = wordToDelete.length();

            while(deleteWord.getChildrenQuantity() < 2) {
                deleteWord = deleteWord.getParent();
                index--;
            }

            deleteWord.removeChild(deleteWord.checkIfHasChild(wordToDelete.charAt(index)));
        } else if(deleteWord.isWord()) {
            deleteWord.setIsWord(false);
        }
    }

    public void KeyPressed(ActionEvent keyEvent) {
        wordsSuggestions.setText("");
        ArrayList<String> wordsSuggestionsList = autoComplete(wordPrefix.getText(), wordsAdded);
        if(wordsSuggestionsList != null) {
            for(String wordToSuggest : wordsSuggestionsList) {
                wordsSuggestions.appendText(wordToSuggest + "\n");
            }
        }
    }
}
