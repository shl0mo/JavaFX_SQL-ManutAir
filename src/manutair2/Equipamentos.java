package manutair2;

import javafx.beans.property.SimpleStringProperty;

public class Equipamentos {
    private SimpleStringProperty id;
    private SimpleStringProperty marca;
    private SimpleStringProperty modelo;
    private SimpleStringProperty numeroSerie;
    
    Equipamentos(String id, String marca, String  modelo, String numeroSerie) {
        this.id = new SimpleStringProperty(id);
        this.marca = new SimpleStringProperty(marca);
        this.modelo = new SimpleStringProperty(modelo);
        this.numeroSerie = new SimpleStringProperty(numeroSerie);
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
}