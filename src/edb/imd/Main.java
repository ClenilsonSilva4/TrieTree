package edb.imd;

public class Main {

    public static void main(String[] args) {
        TrieTree teste = new TrieTree();

        teste.insertWord("Teste");
        teste.insertWord("Testar");
        teste.insertWord("Testando");
        teste.insertWord("Testado");

        teste.autoComplete("Test", 4);

        teste.deleteWord("Teste");
    }
}