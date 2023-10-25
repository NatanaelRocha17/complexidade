package br.edu.ifba.agua.sensor;

import java.util.List;

import br.edu.ifba.agua.impl.ConsumoPorDia;

public interface Sensor<Consumo> {
    public List<ConsumoPorDia> gerarLeiturasDiaria(int mes, int dias);
}
