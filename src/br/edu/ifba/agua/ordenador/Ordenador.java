package br.edu.ifba.agua.ordenador;

import java.util.List;

/**
 * Essa é a classe na qual determina as funcionalidades do ordenador, 
 * cuja complexidade, de forma geral, eh constante, O(1)
 */
public abstract class Ordenador<Sensor> {

    protected List<Sensor> leituras = null;

    public Ordenador(List<Sensor> leituras) {
        this.leituras = leituras;
    }

    public List<Sensor> getLeituras() {
        return this.leituras;
    }
    
    public abstract void ordenar();

    

}
