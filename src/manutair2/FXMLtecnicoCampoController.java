package manutair2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import manutair2.DBConnection;
import manutair2.camposTabela;

public class FXMLtecnicoCampoController {
    @FXML
    private Label mainLabel;

    @FXML
    private TextField id;

    @FXML
    private Button selecionar;

    @FXML
    private Button inicio;
    
    @FXML
    private Button voltar;

    
    @FXML
    private TableColumn<camposTabela, String> colunaId;

    @FXML
    private TableColumn<camposTabela, String> colunaMarca;

    @FXML
    private TableColumn<camposTabela, String> colunaModelo;

    @FXML
    private TableColumn<camposTabela, String> colunaNumeroSerie;

    @FXML
    private TableColumn<camposTabela, String> colunaProblema;
    
    @FXML
    private TableView<camposTabela> tabela;
    
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

    ObservableList<camposTabela> lista = FXCollections.observableArrayList();
    
    @FXML
    void handleBotaoSelecionar(ActionEvent event) throws IOException, SQLException {
        if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Especifique um id para fazer a a alocação");
        } else {
            boolean temId = false;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId().equals(id.getText())) {
                    DBConnection objDBConnection = new DBConnection();
                    Connection con = objDBConnection.DBConnection_method();
                    Statement statement = con.createStatement();
                    temId = true;
                    statement.execute("UPDATE servico SET status = 'Concluída' WHERE marca = '"+ lista.get(i).getMarca() +"' AND modelo = '"+ lista.get(i).getModelo() +"' AND numeroserie = '"+ lista.get(i).getNumeroSerie() +"';");
                    lista.remove(lista.get(i));
                    tabela.setItems(lista);
                    JOptionPane.showMessageDialog(null, "Visita realizada!");
                }
            }
            if (!temId) {
                JOptionPane.showMessageDialog(null, "Especifique um id válido");
            }
        }
    } 

    
    public ObservableList<camposTabela> getDados() {
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        try{
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM servico WHERE status = 'Em andamento'");
            
            int id = 1;
            String marca = "";
            String modelo = "";
            String numeroSerie = "";
            String descricaoProblema = "";
            while(resultado.next()) {
                String resultadoString = "";
                for (int i = 1; i <= 10; i++) {
                    switch (i) {
                        case 3:
                            marca = resultado.getString(i);
                            break;
                        case 4:
                            modelo = resultado.getString(i);
                            break;
                        case 5:
                            numeroSerie = resultado.getString(i);
                            break;
                        case 7:
                            descricaoProblema = resultado.getString(i);
                            break;
                        default:
                            break;
                    }
                    resultadoString += resultado.getString(i) + "|";
                }
                lista.add(new camposTabela("" + id + "", marca, modelo, numeroSerie, descricaoProblema));
                id++;
                System.out.println(resultadoString);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void initialize() {
        colunaId.setCellValueFactory(new PropertyValueFactory<camposTabela, String>("Id"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<camposTabela, String>("Marca"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<camposTabela, String>("Modelo"));
        colunaNumeroSerie.setCellValueFactory(new PropertyValueFactory<camposTabela, String>("NumeroSerie"));
        colunaProblema.setCellValueFactory(new PropertyValueFactory<camposTabela, String>("DescricaoProblema"));
        tabela.setItems(getDados());
    }
}
