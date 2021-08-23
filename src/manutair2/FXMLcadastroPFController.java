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

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;

class DBConnection{
    public Connection DBConnection_method () {
        String url = "jdbc:postgresql://localhost:5432/ManutAir";
        String user = "postgres";
        String password = "pass";
        Connection con = null;
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        }
        try {
           con = DriverManager.getConnection(url, user, password);
           System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
           e.printStackTrace();
        }
        return con;
    }
}

class Equipamento {
    private String marca;
    private String modelo;
    private String numeroSerie;
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    
    public String getMarca() {
        return this.marca;
    }
    
    public String getModelo() {
        return this.modelo;
    }
    
    public String getNumeroSerie() {
        return this.numeroSerie;
    }
}

class ClientePF {
    private String numeroContrato;
    private String nome;
    private String endereco;
    private String telefone;
    private String CPF;
    private String vigencia;
    private String duracao;
    public List<Equipamento> equipamentos;
    
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
    
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    
    public String getVigencia() {
        return this.vigencia;
    }
    
    public String getDuracao() {
        return this.duracao;
    }
    
    public void defineNumeroContrato(int id) {
        String numeroContratoString = "";
        String idString = String.valueOf(id);
        String arrayId[] = idString.split("");
        int i = 5;
        while (i > arrayId.length) {
            numeroContratoString += "0";
            i--;
        }
        numeroContratoString += idString;
        this.numeroContrato = numeroContratoString;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
    public void setEquipamentos(String listaEquipamentos) {
        this.equipamentos = new ArrayList<>();
        String arrayEquipamentos[] = listaEquipamentos.split("\n");
        for (int i = 0; i < arrayEquipamentos.length; i++) {
            Equipamento novo_equipamento = new Equipamento();
            String arrayDadosEquipamento[] = arrayEquipamentos[i].split(",");
            for (int j = 0; j < arrayDadosEquipamento.length; j++) {
                if (j == 0) {
                    novo_equipamento.setMarca(arrayDadosEquipamento[j]);
                } else if (j == 1) {
                    novo_equipamento.setModelo(arrayDadosEquipamento[j]);
                } else if (j == 2) {
                    novo_equipamento.setNumeroSerie(arrayDadosEquipamento[j]);
                }
            }
            this.equipamentos.add(novo_equipamento);
        }
        
    }
    
   public String getNumeroContrato() {
       return this.numeroContrato;
   }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public String getTelefone() {
        return this.telefone;
    }
    
    public String getCPF() {
        return this.CPF;
    }
    
    
    public void cadastraClientePF() {
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        try{
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM clientepf");
            
            ResultSet idResult = statement.executeQuery("SELECT * FROM clientepf");
            int id = 0;
            while (idResult.next()) {
                id++;
            }
            idResult = statement.executeQuery("SELECT * FROM clientepj");
             while (idResult.next()) {
                id++;
            }
            id++;
            System.out.println(id);
            defineNumeroContrato(id);
            statement.execute("INSERT INTO clientepf VALUES ('"+ getNome() +"',"+ id +",'"+ getEndereco() + "','"+ getTelefone() +"','"+ getCPF() +"','"+ getNumeroContrato() +"', '"+ getVigencia() +"', '"+ getDuracao() +"');");
            for (int i = 0; i < this.equipamentos.size(); i++) {
                statement.execute("INSERT INTO equipamento VALUES ('"+ getNumeroContrato() +"','"+ this.equipamentos.get(i).getMarca() +"','"+ this.equipamentos.get(i).getModelo() +"','"+ this.equipamentos.get(i).getNumeroSerie() +"', false);");
            }
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! Anote seu número de contrato: " + getNumeroContrato());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

public class FXMLcadastroPFController implements Initializable {
    
    @FXML
    private Button voltar;

    @FXML
    private Button inicio;
    
    @FXML
    private TextField nomePF;

    @FXML
    private TextField enderecoPF;

    @FXML
    private TextField telefonePF;

    @FXML
    private TextField cpfPF;
    
    @FXML
    private TextArea equipamentos;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button sair;
    
    @FXML
    private TextField vigencia;

    @FXML
    private TextField duracao;

    @FXML
    void handleBotaoCadastrar(ActionEvent event) {
        if (nomePF.getText().equals("") || enderecoPF.getText().equals("") || telefonePF.getText().equals("") || cpfPF.getText().equals("") || equipamentos.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
        } else {
            ClientePF novo_cliente = new ClientePF();
            novo_cliente.setNome(nomePF.getText());
            novo_cliente.setEndereco(enderecoPF.getText());
            novo_cliente.setTelefone(telefonePF.getText());
            novo_cliente.setCPF(cpfPF.getText());
            novo_cliente.setEquipamentos(equipamentos.getText());
            novo_cliente.setVigencia(vigencia.getText());
            novo_cliente.setDuracao(duracao.getText());
            novo_cliente.cadastraClientePF();
        }
    }
    
    @FXML
    void handleBotaoVoltar(ActionEvent event) {
        Stage stage = null;
        Parent myNewScene = null;
        stage = (Stage) voltar.getScene().getWindow();
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}