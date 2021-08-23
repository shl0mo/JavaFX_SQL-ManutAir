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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

import manutair2.DBConnection;
import manutair2.Equipamentos;


public class FXMLsolicitarReparoController {
    @FXML
    private Label mainLabel;

    @FXML
    private TextField textFieldId;

    @FXML
    private Button solicitar;

    @FXML
    private Button inicio;
    
    @FXML
    private TextField enderecoEquipamento;

    @FXML
    private TableColumn<Equipamentos, String> colunaId;

    @FXML
    private TableColumn<Equipamentos, String> colunaMarca;

    @FXML
    private TableColumn<Equipamentos, String> colunaModelo;

    @FXML
    private TableColumn<Equipamentos, String> colunaNumeroSerie;
    
    @FXML
    private TableView<Equipamentos> tabela;

    @FXML
    private Button voltar;
    
    @FXML
    private TextArea descricaoProblema;

    ObservableList<Equipamentos> lista = FXCollections.observableArrayList();

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
    

    @FXML
    void handleBotaoSolicitar(ActionEvent event) {
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        try{
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM servico");
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
            resultado = statement.executeQuery("SELECT * FROM servico");
            int id = 0;
            while (resultado.next()) {
                id++;
            }
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId().equals(textFieldId.getText())) {
                    System.out.println("lista: " + lista.get(i).getId());
                    System.out.println("textField: " + textFieldId.getText());
                    statement.execute("INSERT INTO servico(id, numerocontrato, marca, modelo, numeroserie, enderecoequipamento, descricaoproblema, status) VALUES("+ id +", '"+ numero_contrato +"', '"+ lista.get(i).getMarca() +"', '"+ lista.get(i).getModelo() +"', '"+ lista.get(i).getNumeroSerie() +"','"+ enderecoEquipamento.getText() +"','"+ descricaoProblema.getText() +"', 'Aberto')");
                    statement.execute("UPDATE equipamento SET status = true WHERE numerocontrato = '"+ numero_contrato +"' AND marca = '"+ lista.get(i).getMarca() +"' AND modelo = '"+ lista.get(i).getModelo() +"' AND serie = '"+ lista.get(i).getNumeroSerie() +"'");
                    //id, numerocontrato, marca, modelo, numeroserie, enderecoequipamento, descricaoproblema, diaconsulta, horaconsulta, status
                    lista.remove(lista.get(i));
                    tabela.setItems(lista);
                    JOptionPane.showMessageDialog(null, "Serviço solicitado");
                    break;
                }
            }
            
        } catch(Exception e) {
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
    
    public ObservableList<Equipamentos> getDados() {
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        try{
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
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM equipamento WHERE numerocontrato = '"+ numero_contrato +"' AND status = false");
            
            int id = 1;
            String marca = "";
            String modelo = "";
            String numeroSerie = "";
            while(resultado.next()) {
                String resultadoString = "";
                for (int i = 1; i <= 4; i++) {
                    switch (i) {
                        case 2:
                            marca = resultado.getString(i);
                            break;
                        case 3:
                            modelo = resultado.getString(i);
                            break;
                        case 4:
                            numeroSerie = resultado.getString(i);
                            System.out.println("NUMERO SERIE: "+ numeroSerie);
                            break;
                        default:
                            break;
                    }
                    resultadoString += resultado.getString(i) + "|";
                }
                lista.add(new Equipamentos("" + id + "", marca, modelo, numeroSerie));
                id++;
                System.out.println(resultadoString);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void initialize() {
        colunaId.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("Id"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("Marca"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("Modelo"));
        colunaNumeroSerie.setCellValueFactory(new PropertyValueFactory<Equipamentos, String>("NumeroSerie"));
        tabela.setItems(getDados());
    }
}
