package manutair2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLalocacaoController {
    
    @FXML
    private TextField data;
    
    @FXML
    private TextField hora;

    @FXML
    private Button botaoDefinir;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoInicio;

    @FXML
    void handleBotaoDefinir(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        File file = new File("dados_servico.txt");
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        int num_linha = 0;
        String marca = "";
        String modelo = "";
        String numeroSerie = "";
        while (br.ready()) {
            String linha = br.readLine();
            System.out.println("Linha: " + linha);
            if (num_linha == 0) {
                marca = linha;
            } else if (num_linha == 1) {
                modelo = linha;
            } else {
                numeroSerie = linha;
            }
            num_linha++;
        }
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        Statement statement = con.createStatement();
        statement.execute("UPDATE servico SET status = 'Em andamento' WHERE marca = '"+ marca +"' AND modelo = '"+ modelo +"' AND numeroserie = '"+ numeroSerie +"';");
        statement.execute("UPDATE servico SET diaconsulta = '"+ data.getText() +"' WHERE marca = '"+ marca +"' AND modelo = '"+ modelo +"' AND numeroserie = '"+ numeroSerie +"';");
        statement.execute("UPDATE servico SET horaconsulta = '"+ hora.getText() +"' WHERE marca = '"+ marca +"' AND modelo = '"+ modelo +"' AND numeroserie = '"+ numeroSerie +"';");
        JOptionPane.showMessageDialog(null, "Alocação realizada!");
    }

    @FXML
    void handleBotaoInicio(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) botaoInicio.getScene().getWindow();
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
        stage = (Stage) botaoVoltar.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/supervisorTecnico.fxml"));
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
