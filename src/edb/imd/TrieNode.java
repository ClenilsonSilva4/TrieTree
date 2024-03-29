package edb.imd;

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

    public void removeChild(TrieNode childToRemove) {
        children.remove(childToRemove);
    }

    public String getText() {
        return text;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setIsWord(boolean word) {
        isWord = word;
        if(word) {
            assembleWord();
        }
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
        if (checkIfHasChild(newChild.getNodeLetter()) != null) {
            TrieNode hasChild = checkIfHasChild(newChild.getNodeLetter());
            if(newChild.isWord()){
                hasChild.setIsWord(true);
            }
            return hasChild;
        } else {
            children.add(newChild);
            newChild.setParent(this);

            if(newChild.isWord()) {
                newChild.assembleWord();
            }
            return newChild;
        }
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

    public TrieNode checkIfHasChild(char nodeChar) {
        for(TrieNode child : children) {
            if(child.getNodeLetter() == nodeChar) {
                return child;
            }
        }

        return null;
    }

    public int getWord(int wordsQuantities, ArrayList<String> wordsList) {
        if(isWord()) {
            wordsList.add(this.text);
            wordsQuantities--;
        }

        for (int i = 0; i < children.size() && wordsQuantities > 0; i++) {
            wordsQuantities = children.get(i).getWord(wordsQuantities, wordsList);
        }

        return wordsQuantities;
    }

    public int getChildrenQuantity() {
        return children.size();
    }
}
