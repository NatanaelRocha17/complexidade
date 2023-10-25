package br.edu.ifba.agua.impl;

import java.util.Arrays;

/**
 * classe para incremetar o consumo Diario
 * cuja complexidade, de forma geral, eh constante, O(1)
 */
public class ConsumoPorDia {
    private int id;
    private double consumoMensal;
    private double[][] consumoDiario;
    private int mes;

    public ConsumoPorDia(double[][] consumoDiario, int id, double consumoMensal, int mes) {
        this.consumoDiario = consumoDiario;
        this.id = id;
        this.consumoMensal = consumoMensal;
        this.mes = mes;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getConsumoDiario(int mes, int dia) {
        return consumoDiario[mes][dia];
    }

    public void setConsumoDiario(int mes, int dia, double consumoDiario) {
        this.consumoDiario[mes][dia] = consumoDiario;
    }

    public double getConsumoMensal() {
        return consumoMensal;
    }

    public void setConsumoMensal(double consumoMensal) {
        this.consumoMensal = consumoMensal;
    }


    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }



    @Override
    public String toString() {
        return "ConsumoPorDia [id=" + id + ", consumoMensal=" + consumoMensal + ", consumoDiario="
                + Arrays.toString(consumoDiario) + ", mes=" + mes + "]";
    }
    
    
}
