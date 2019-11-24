package EDB.IMD;

import java.util.ArrayList;

public class TrieNode {
    private boolean isWord;
    private ArrayList <TrieNode> children;
    private String text;
    private char nodeChar;

    public TrieNode() {
        this.isWord = false;
        this.children = new ArrayList<>();
    }

    public TrieNode(char nodeChar, boolean isWord) {
        this();
        this.nodeChar = nodeChar;
        this.isWord = isWord;
    }

    public TrieNode addChildren(TrieNode newChild) {
        if(children.contains(newChild)) {
            children.get(children.indexOf(newChild)).addChildren(newChild);
        } else {
            children.add(newChild);
        }

        return newChild;
    }

    public boolean isWord() {
        return isWord;
    }

    public char getNodeChar() {
        return nodeChar;
    }
}
