package EDB.IMD;

public class Main {

    public static void main(String[] args) {
        TrieTree teste = new TrieTree();

        teste.insertWord("Teste");

        System.out.println(teste.search("Teste"));
    }
}
