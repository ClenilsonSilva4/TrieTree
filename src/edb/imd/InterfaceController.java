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
import java.util.List;

public class InterfaceController extends Application {
    private TrieTree tree;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader wordsInterface = new FXMLLoader(getClass().getResource("SuggestionsInterface.fxml"));
        Parent root = wordsInterface.load();
        tree = wordsInterface.getController();
        primaryStage.setTitle("Sugestões de Palavras");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.setResizable(false);
        insertWords();
        primaryStage.show();
    }

    private void insertWords() {
        List<String> args = this.getParameters().getRaw();
        TrieTree newTree = getTree();

        try {
            BufferedReader wordsToInsert = new BufferedReader(new FileReader(args.get(0)));
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

        if(args.size() < 3) {
            newTree.autoComplete(args.get(1));
        } else {
            newTree.autoComplete(args.get(1), Integer.parseInt(args.get(2)));
        }
    }

    public TrieTree getTree() {
        return tree;
    }

    public static void main(String[] args) {
        launch(args);
    }
}