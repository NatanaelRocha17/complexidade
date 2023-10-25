package br.edu.ifba.agua.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.GregorianCalendar;

import br.edu.ifba.agua.sensor.Sensor;

public class SensorImpl implements Sensor<ConsumoPorDia> {

    
    /*
     * Este método gera dados aleatórios para simular o consumo de água de um certo consumidor.
     * Sua complexidade é O(N^3), portanto, cúbica, porque existem 3 loops(for)
     * aninhados
     */
    @Override
    public List<ConsumoPorDia> gerarLeiturasDiaria(int mesTotal, int dias) {
        GregorianCalendar calendario = new GregorianCalendar();

        List<ConsumoPorDia> leiturasDiarias = new ArrayList<>();

        double[][] consumoAgua = new double[mesTotal][dias];

        // Cria um objeto Random para gerar valores aleatórios
        Random random = new Random();

        // Preencher a matriz com os dados de consumo de água
        int mesAtual = calendario.get(Calendar.MONTH) + 1;
        int mesFinal = 12;
        for (int mes = 0; mes < mesTotal; mes++) {
            int diasNoMes = dias; 
            consumoAgua[mes] = new double[diasNoMes]; 
            double consumoMensal = 0.0;
            for (int dia = 0; dia < diasNoMes; dia++) {
                double consumoDiario = 0.0;
                for (int medicao = 0; medicao < 3; medicao++) {
                    // Gerar um valor aleatório entre 1 e 100 litros
                    consumoDiario = consumoDiario + random.nextDouble() * 100 + 1;
                    consumoMensal = consumoMensal + consumoDiario;
                }
                consumoAgua[mes][dia] = consumoDiario;
            }
            int id = mes + 1;
            ConsumoPorDia consumo = new ConsumoPorDia(consumoAgua, id, consumoMensal,
                    (mesAtual-- <= 1 ? mesFinal-- : mesAtual));
            leiturasDiarias.add(consumo);
        }
        return leiturasDiarias;

    }

}
