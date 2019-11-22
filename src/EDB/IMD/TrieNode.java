package EDB.IMD;

import java.util.ArrayList;

public class TrieNode {
    private boolean isWord;
    private ArrayList <Character> children;
    private String text;
    private char nodeChar;

    public TrieNode() {
        this.isWord = false;
        this.children = new ArrayList<>();
    }

    public void addChildren() {

    }
}
