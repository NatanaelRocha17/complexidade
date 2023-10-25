package br.edu.ifba.agua.operacoes;

import java.util.List;
import java.util.Map;

public interface Operacoes<Cliente, ConsumoPorDia> {

    // d1
    public void imprimir(List<Cliente> clientes);

    // d2

    public void imprimirLeituras(Map<Cliente, List<ConsumoPorDia>> leituras);

    // d3
    public Map<Cliente, List<ConsumoPorDia>> ordenar(Map<Cliente, List<ConsumoPorDia>> leituras);

    // d4
    public void maiorLeituraEMenor(Map<Cliente, List<ConsumoPorDia>> leituras);

    // d5
    public int encontrarVazamento(int[] tubulacao, int pressaoMinima, Map<Cliente, List<ConsumoPorDia>> leiturasPorCliente);

    // d6
    public void imprimirLeiturasDeConsumoPorDia(Map<Cliente, List<ConsumoPorDia>> leiturasDeConsumoDiario, int mesTotal, int diaTotal);

}
