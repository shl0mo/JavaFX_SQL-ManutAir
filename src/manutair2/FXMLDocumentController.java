package manutair2;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import manutair2.DBConnection;
import manutair2.FXMLsupervisorTecnicoController;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button supervisorTecnico;

    @FXML
    private Button cliente;

    @FXML
    private Button tecnicodeCampo;
    
    public void handleBotaoCliente(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) cliente.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/cliente.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void handleBotaoSupervisorTecnico() {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) supervisorTecnico.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/supervisorTecnico.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBotaoTecnicoCampo(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) supervisorTecnico.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/tecnicoCampo.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        try {
            
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS clientepf ( nome varchar(255), id varchar(255) PRIMARY KEY, endereco VARCHAR(255), cpf VARCHAR(255), telefone VARCHAR(255), numerocontrato VARCHAR(255), iniciovigencia VARCHAR(255), duracao VARCHAR(255));");
            statement.execute("CREATE TABLE IF NOT EXISTS clientepj ( razaosocial VARCHAR(255), id varchar(255), nomeresponsavel VARCHAR(255), endereco VARCHAR(255), cnpj VARCHAR(255), telefone VARCHAR(255), numerocontrato VARCHAR(255),  iniciovigencia VARCHAR(255), duracao VARCHAR(255));");
            statement.execute("CREATE TABLE IF NOT EXISTS contrato ( numerocontrato VARCHAR(255), id varchar(255), iniciovigencia VARCHAR(255), duracao VARCHAR(255), id_cliente VARCHAR(255))");
            statement.execute("CREATE TABLE IF NOT EXISTS equipamento ( numerocontrato VARCHAR(255), marca varchar(255), modelo VARCHAR(255), serie VARCHAR(255), status BOOLEAN)");
            statement.execute("CREATE TABLE IF NOT EXISTS servico ( id VARCHAR(255), numerocontrato varchar(255), marca VARCHAR(255), modelo VARCHAR(255), numeroserie VARCHAR(255), enderecoequipamento VARCHAR(255), descricaoproblema VARCHAR(255), diaconsulta VARCHAR(255), horaconsulta VARCHAR(255), status VARCHAR(255))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
}
