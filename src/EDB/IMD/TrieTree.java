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
                insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), false));
            } else {
                insertingWord = insertingWord.addChildren(new TrieNode(wordToInsert.charAt(i), false));
            }
        }
    }
}
