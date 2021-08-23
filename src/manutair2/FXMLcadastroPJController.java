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

import manutair2.DBConnection;
import manutair2.Equipamento;

class ClientePJ {
    private String numeroContrato;
    private String razaoSocial;
    private String endereco;
    private String CNPJ;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String vigencia;
    private String duracao;
    private List<Equipamento> equipamentos;
    
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
    
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
    
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }
    
    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
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
    
    public String getRazaoSocial() {
        return this.razaoSocial;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public String getCNPJ () {
        return this.CNPJ;
    }
    
        public String getNomeResponsavel() {
        return this.nomeResponsavel;
    }
    
    public String getTelefoneResponsavel() {
        return this.telefoneResponsavel;
    }
    
    public void cadastraClientePJ() {
        DBConnection objDBConnection = new DBConnection();
        Connection con = objDBConnection.DBConnection_method();
        try{
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM clientepj");
           
            System.out.println("Resultados: ");
            while (resultado.next()) {
               String resultadoString = "";
               for (int i = 1; i <= 7; i++) {
                   resultadoString += resultado.getString(i) + " | ";
               }
               System.out.println(resultadoString);
            }
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
            statement.execute("INSERT INTO clientepj VALUES ('"+ getRazaoSocial() +"',"+ id +",'"+ getNomeResponsavel() +"','"+ getEndereco() +"','"+ getCNPJ() +"','"+ getTelefoneResponsavel() +"','"+ getNumeroContrato() +"', '"+ getVigencia() +"', '"+ getDuracao() +"');");
            for (int i = 0; i < this.equipamentos.size(); i++) {
                statement.execute("INSERT INTO equipamento VALUES ('"+ getNumeroContrato() +"','"+ this.equipamentos.get(i).getMarca() +"','"+ this.equipamentos.get(i).getModelo() +"','"+ this.equipamentos.get(i).getNumeroSerie() +"', false);");
            }
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! Anote seu número de contrato: " + getNumeroContrato());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

public class FXMLcadastroPJController implements Initializable {
    
    @FXML
    private TextField razaoSocial;

    @FXML
    private TextField nomeResponsavel;

    @FXML
    private TextField telefonePJ;

    @FXML
    private TextField cnpj;

    @FXML
    private Button botaoContinuar;

    @FXML
    private Label CNPJ;
    
    @FXML
    private TextField vigencia;

    @FXML
    private TextField duracao;

    @FXML
    private TextField enderecoPJ;

    @FXML
    private TextArea equipamentos;

    @FXML
    private Button sair;

    @FXML
    private Button voltar;

    @FXML
    private Button inicio;

    @FXML
    void handleBotaoCadastrar(ActionEvent event) {
        if (razaoSocial.getText().equals("") || nomeResponsavel.getText().equals("") || telefonePJ.getText().equals("") || cnpj.getText().equals("") || enderecoPJ.getText().equals("") || equipamentos.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
        } else {
            ClientePJ novo_cliente = new ClientePJ();
            novo_cliente.setRazaoSocial(razaoSocial.getText());
            novo_cliente.setNomeResponsavel(nomeResponsavel.getText());
            novo_cliente.setTelefoneResponsavel(telefonePJ.getText());
            novo_cliente.setCNPJ(cnpj.getText());
            novo_cliente.setEndereco(enderecoPJ.getText());
            novo_cliente.setEquipamentos(equipamentos.getText());
            novo_cliente.setVigencia(vigencia.getText());
            novo_cliente.setDuracao(duracao.getText());
            novo_cliente.cadastraClientePJ();
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