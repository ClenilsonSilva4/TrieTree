package EDB.IMD;

public class TrieTree {
    private TrieNode treeRoot;

    public TrieTree() {
        this.treeRoot = new TrieNode();
    }

    public void insertWord(String wordToInsert) {
        TrieNode insertingWord = new TrieNode(wordToInsert.charAt(0), false);
        insertingWord = treeRoot.addChildren(insertingWord);

        for(int i = 1; i < wordToInsert.length(); i++) {
            if(i + 1 == wordToInsert.length()) {
                insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), true));
            } else {
                insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), false));
            }
        }
    }

    private TrieNode searchPrefix(String wordToSearch) {
        TrieNode searchingWord = treeRoot;

        for (int i = 0; i < wordToSearch.length() && searchingWord != null; i++) {
            searchingWord = searchingWord.checkIfHasChild(wordToSearch.charAt(i));
        }

        return searchingWord;
    }

    public boolean search(String wordToSearch) {
        return searchPrefix(wordToSearch).getText().equals(wordToSearch);
    }

    public void autoComplete(String wordPrefix, int wordsQuantities) {
        TrieNode firstPrefixNode = searchPrefix(wordPrefix);

        if(firstPrefixNode == null || firstPrefixNode.howManyChildren() == 0) {
            return;
        }

        for (int i = 0; i < wordsQuantities; i++) {
            //TODO
        }
    }
}
