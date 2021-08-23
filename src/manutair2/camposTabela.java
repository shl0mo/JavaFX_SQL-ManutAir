package manutair2;

import javafx.beans.property.SimpleStringProperty;

public class camposTabela {
    private SimpleStringProperty id;
    private SimpleStringProperty marca;
    private SimpleStringProperty modelo;
    private SimpleStringProperty numeroSerie;
    private SimpleStringProperty descricaoProblema;
    
    camposTabela (String id, String marca, String  modelo, String  numeroSerie, String descricaoProblema) {
        this.id = new SimpleStringProperty(id);
        this.marca = new SimpleStringProperty(marca);
        this.modelo = new SimpleStringProperty(modelo);
        this.numeroSerie = new SimpleStringProperty(numeroSerie);
        this.descricaoProblema = new SimpleStringProperty(descricaoProblema);
    }

    public final String getId() {
        return id.get();
    }

    public final String getMarca() {
        return marca.get();
    }

    public final String getModelo() {
        return modelo.get();
    }

    public final String getNumeroSerie() {
        return numeroSerie.get();
    }

    public final String getDescricaoProblema() {
        return descricaoProblema.get();
    }
}