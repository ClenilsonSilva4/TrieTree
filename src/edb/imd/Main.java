package edb.imd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TrieTree newTree = new TrieTree();

        try {
            BufferedReader wordsToInsert = new BufferedReader(new FileReader(args[0]));
            String line = wordsToInsert.readLine();

            while(line != null) {
                newTree.insertWord(line);
                line = wordsToInsert.readLine();
            }
        } catch (FileNotFoundException notFound) {
            System.out.println("Arquivo Não Encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(args.length < 3) {
            newTree.autoComplete(args[1]);
        } else {
            newTree.autoComplete(args[1], Integer.parseInt(args[2]));
        }
    }
}
