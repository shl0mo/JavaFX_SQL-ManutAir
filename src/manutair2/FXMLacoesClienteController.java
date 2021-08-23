package manutair2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class FXMLacoesClienteController {
    
    @FXML
    private Button cadastrarEquipamento;

    @FXML
    private Label mainLabel;

    @FXML
    private Button inicio;

    @FXML
    private Button sair;

    @FXML
    private Button removerEquipamento;

    @FXML
    private Button solicitarReparo;
    
    @FXML
    private Button voltar;
    
    
    @FXML
    public void hadleBotaoCadastrarEquipamento(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) cadastrarEquipamento.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/cadastrarEquipamento.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void hadleBotaoSolicitarReparo(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) solicitarReparo.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/solicitarReparo.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
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
            myNewScene = FXMLLoader.load(getClass().getResource("views/loginCliente.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleBotaoRemoverEquipamento(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) removerEquipamento.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/removerEquipamento.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void initialize() {
        try {
            File file = new File("contrato_nome.txt");
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            int num_linha = 0;
            String numero_contrato = "";
            String nome_cliente = "";
            while (br.ready()) {
                String linha = br.readLine();
                System.out.println("Linha: " + linha);
                if (num_linha == 0) {
                    numero_contrato = linha;
                } else {
                    nome_cliente = linha;
                }
                num_linha++;
            }
            mainLabel.setText("Bem, vindo " + nome_cliente + "! Selecione uma das ações abaixo: ");
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
}
