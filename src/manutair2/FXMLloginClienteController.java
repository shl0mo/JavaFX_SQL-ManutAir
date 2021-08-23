package manutair2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;

public class FXMLloginClienteController {
    
    public String nomeCliente = "";
    
    @FXML
    private Label mainLabel;

    @FXML
    public TextField numeroContrato;

    @FXML
    private Button entrarSistema;

    @FXML
    private Button voltar;

    @FXML
    private Button inicio;

    @FXML
    private Button sair;

    @FXML
    public void handleBotaoEntrarSistema(ActionEvent event) {
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        try{
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM clientepf");
            
            boolean encontrado = false;
            
            while (resultado.next()) {
                if (resultado.getString(6).equals(numeroContrato.getText())) {
                    encontrado = true;
                    break;
                }
            }
            
            if (!encontrado) {
                resultado = statement.executeQuery("SELECT * FROM clientepj");
                while (resultado.next()) {
                    if (resultado.getString(7).equals(numeroContrato.getText())) {
                        encontrado = true;
                        break;
                    }
                }
            }
            
            if (encontrado) {
                resultado = statement.executeQuery("SELECT nome FROM clientepf WHERE numerocontrato = '" + numeroContrato.getText() + "'");
                while (resultado.next()) {
                    nomeCliente = resultado.getString(1);
                }
                Stage stage = null;
                Parent myNewScene = null;
                stage = (Stage) entrarSistema.getScene().getWindow();
                try {
                    File arq = new File("contrato_nome.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(arq.getAbsolutePath()));
                    writer.write(numeroContrato.getText());
                    writer.write("\n");
                    writer.write(nomeCliente);
                    writer.write("\n");
                    writer.close();
                    myNewScene = FXMLLoader.load(getClass().getResource("views/acoesCliente.fxml"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.show();
            } else {
                JOptionPane.showMessageDialog(null, "O número de contrato inserido não foi encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleBotaoInicio(ActionEvent event) {
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
    public void handleBotaoVoltar(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) voltar.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/cliente.fxml"));
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
