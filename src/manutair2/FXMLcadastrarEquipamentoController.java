package manutair2;

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

import java.io.*;

import manutair2.DBConnection;
import manutair2.Equipamento;


public class FXMLcadastrarEquipamentoController {
    
   @FXML
    private Label mainLabel;

    @FXML
    private Button inicio;
    
    @FXML
    private Button voltar;

    @FXML
    private TextArea equipamentos;

    @FXML
    private Button botaoCadastrar;
    

    @FXML
    void handleBotaoCadastrar(ActionEvent event) {
        if (equipamentos.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo de dados");
        } else {
            DBConnection objDBConnection = new DBConnection();
            Connection con = objDBConnection.DBConnection_method();
            try{
                Statement statement = con.createStatement();
                ClientePF novo_cliente = new ClientePF();
                novo_cliente.setEquipamentos(equipamentos.getText());
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
                for (int i = 0; i < novo_cliente.equipamentos.size(); i++) {
                    statement.execute("INSERT INTO equipamento VALUES ('"+ numero_contrato +"','"+ novo_cliente.equipamentos.get(i).getMarca() +"','"+ novo_cliente.equipamentos.get(i).getModelo() +"','"+ novo_cliente.equipamentos.get(i).getNumeroSerie() +"', false);");
                }
                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

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
        stage = (Stage) voltar.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/acoesCliente.fxml"));
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
