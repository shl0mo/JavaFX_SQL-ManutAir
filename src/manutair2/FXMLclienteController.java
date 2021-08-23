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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLclienteController {
    
    @FXML
    private Button botaoSim;

    @FXML
    private Button botaoNao;
    
    @FXML
    private Button voltar;
    
     @FXML
    void handleBotaoVoltar(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) voltar.getScene().getWindow();
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
    public void handleBotaoNao(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) botaoNao.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/tipoCadastro.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleBotaoSim(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) botaoSim.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/loginCliente.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
