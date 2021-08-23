package manutair2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class FXMLtipoCadastroController implements Initializable {
    
    @FXML
    private Button cadastrarPJ;

    @FXML
    private Button cadastrarPF;
    
    @FXML
    private Button voltar;

    @FXML
    private Button inicio;

    @FXML
    void handleBotaoInicio(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) inicio.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleBotaoVoltar(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) inicio.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/cliente.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void handleCadastroPF(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) cadastrarPF.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/cadastroPF.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleCadastroPJ(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) cadastrarPF.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/cadastroPJ.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
