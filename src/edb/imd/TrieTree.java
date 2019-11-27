package edb.imd;

import java.util.ArrayList;

public class TrieTree {
    private TrieNode treeRoot;

    public TrieTree() {
        this.treeRoot = new TrieNode();
    }

    public void insertWord(String wordToInsert) {
        TrieNode insertingWord = treeRoot;
        wordToInsert = wordToInsert.toLowerCase();

        for(int i = 0; i < wordToInsert.length(); i++) {
            if(i + 1 == wordToInsert.length()) {
                insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), true));
            } else {
                insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), false));
            }
        }
    }

    private TrieNode searchPrefix(String wordToSearch) {
        TrieNode searchingWord = treeRoot;

        for(int i = 0; i < wordToSearch.length() && searchingWord != null; i++) {
            searchingWord = searchingWord.checkIfHasChild(wordToSearch.charAt(i));
        }

        return searchingWord;
    }

    public void search(String wordToSearch) {
        wordToSearch = wordToSearch.toLowerCase();
        TrieNode searchWord = searchPrefix(wordToSearch);

        if(searchWord != null && searchWord.getText().equals(wordToSearch)) {
            System.out.println("A Palavra Foi Encontrada");
        } else {
            System.out.println("A Palavra Não Foi Encontrada");
        }
    }

    public void autoComplete(String wordPrefix, int wordsQuantities) {
        wordPrefix = wordPrefix.toLowerCase();
        TrieNode autoCompleteNode = searchPrefix(wordPrefix);

        if(autoCompleteNode == null) {
            System.out.println("Não Foram Encontradas Palavras Cadastradas com o Prefixo");
            return;
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

        if(suggestionsList.size() != 1) {
            System.out.print("Sugestões: ");
            for(String wordFound : suggestionsList) {
                if(!wordFound.equals(wordPrefix)) {
                    System.out.println(wordFound);
                    System.out.print("\t\t   ");
                }
            }
        } else if(!suggestionsList.contains(wordPrefix)) {
            System.out.print("Sugestão: ");
            System.out.println(suggestionsList.get(0));
        }
    }

    public void deleteWord(String wordToDelete) {

    }
}
