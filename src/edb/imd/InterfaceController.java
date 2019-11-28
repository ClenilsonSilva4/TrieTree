package edb.imd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InterfaceController extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader wordsInterface = new FXMLLoader(getClass().getResource("SuggestionsInterface.fxml"));
        Parent root = wordsInterface.load();
        primaryStage.setTitle("Sugestões de Palavras");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

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

        if(args.length == 3) {
            newTree.autoComplete(args[1], Integer.getInteger(args[2]));
        } else {
            newTree.autoComplete(args[1]);
        }
    }
}