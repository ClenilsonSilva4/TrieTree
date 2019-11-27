package EDB.IMD;

import java.util.ArrayList;

public class TrieNode {
    private boolean isWord;
    private ArrayList <TrieNode> children;
    private String text;
    private TrieNode parent;
    private char nodeLetter;

    public TrieNode() {
        isWord = false;
        children = new ArrayList<>();
        parent = null;
    }

    public TrieNode(char nodeLetter, boolean isWord) {
        this();
        this.nodeLetter = nodeLetter;
        this.isWord = isWord;
    }

    public String getText() {
        return text;
    }

    public boolean isWord() {
        return isWord;
    }

    public char getNodeLetter() {
        return nodeLetter;
    }

    private void setParent(TrieNode trieNode) {
        parent = trieNode;
    }

    public TrieNode getParent() {
        return parent;
    }

    public TrieNode addChildren(TrieNode newChild) {
        if(children.contains(newChild)) {
            children.get(children.indexOf(newChild)).addChildren(newChild);
        } else {
            children.add(newChild);
            newChild.setParent(this);

            if(newChild.isWord()) {
                newChild.assembleWord();
            }
        }

        return newChild;
    }

    private void assembleWord() {
        StringBuilder wordToAssemble = new StringBuilder(String.valueOf(nodeLetter));
        TrieNode gettingParentsLetter = parent;

        while(gettingParentsLetter.getNodeLetter() != 0) {
            wordToAssemble.append(gettingParentsLetter.getNodeLetter());
            gettingParentsLetter = gettingParentsLetter.getParent();
        }

        wordToAssemble.reverse();
        text = wordToAssemble.toString();
    }

    public int howManyChildren() {
        return children.size();
    }

    public TrieNode checkIfHasChild(char nodeChar) {
        for (TrieNode child : children) {
            if (child.getNodeLetter() == nodeChar) {
                return child;
            }
        }

        return null;
    }
}
