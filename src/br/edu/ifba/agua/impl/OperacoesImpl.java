package br.edu.ifba.agua.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import br.edu.ifba.agua.operacoes.Operacoes;
import br.edu.ifba.agua.ordenador.Ordenador;

public class OperacoesImpl implements Operacoes<Cliente, ConsumoPorDia> {

    /**
     * imprime os dados dos clientes
     * 
     * a complexidade deste metodo eh linear, O(N), porque o total de passos de
     * execução
     */
    @Override
    public void imprimir(List<Cliente> clientes) {
        System.out.println("====CLIENTES====");
        for (Cliente cliente : clientes) {
            System.out.println("Nome do cliente: " + cliente.getNome() + " Numero da tubulação: "
                    + cliente.getNumeroDaTubulacaoResidencia());
        }
    }

    /*
     * Complexidade O(logN) na qual é uma busca binária que consiste em dividir o
     * array em partes menores, para realizar uma busca
     */
    public int encontrarVazamento(int[] tubulacao, int pressaoMinima,
        Map<Cliente, List<ConsumoPorDia>> leiturasPorCliente) {
            System.out.println("\nEXIBIÇÃO DE CLIENTES QUE SE ENCONTRAM PERTO DE UM VAZAMENTO");
        int esquerda = 0;
        int direita = tubulacao.length - 1;

        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;

            if (tubulacao[meio] < pressaoMinima) {
                // A pressão é menor do que o limite, o vazamento está à esqAuerda
                direita = meio - 1;
            } else {
                // A pressão é igual ou maior do que o limite, o vazamento está à direita
                esquerda = meio + 1;
            }
        }

        int posicaoVazamento = esquerda;

        // Complexidade liner O(N) pois existe esse for para perrcorer os clintes
        for (Entry<Cliente, List<ConsumoPorDia>> leitura : leiturasPorCliente.entrySet()) {
            String nomeConsumidor = leitura.getKey().getNome();
            Cliente consumidor = leitura.getKey();
            int posicaoConsumidor = consumidor.getNumeroDaTubulacaoResidencia();

            // Verifique se o consumidor está até 5 unidades de distância do vazamento
            if (Math.abs(posicaoConsumidor - posicaoVazamento) <= 5) {
                System.out.println(nomeConsumidor + " está próximo ao vazamento na tubulação " + posicaoConsumidor);
            }
        }
        // Quando a busca binária terminar, "esquerda" conterá a posição estimada do
        // vazamento
        return esquerda;
    }

    /**
     * imprime o consumo de cada mês por cada cliente
     * 
     * a complexidade é quadratica O(N^2), porque existe um loop aninhado em
     * um mais externo
     */
    @Override
    public void imprimirLeituras(Map<Cliente, List<ConsumoPorDia>> leituras) {
        System.out.println("\n====EXIBIÇÃO DE CONSUMO DE AGUA DE CADA CLIENTE====");
        for (Cliente cliente : leituras.keySet()) {
            System.out.println("\nConsumo do(a) cliente " + cliente.getNome());
            for (int i = 0; i < leituras.get(cliente).size(); i++) {
                System.out.println("Mes " + leituras.get(cliente).get(i).getMes() + " - Leitura(Litros): "
                        + leituras.get(cliente).get(i).getConsumoMensal());
            }
        }
    }

    /**
     * imprime a menor e maior leitura dos clientes
     * 
     * a complexidade deste metodo eh linear, O(N), porque o total de passos de
     * execução
     * cresce linearmente em relação ao tamanho da entrada de dados depedendo dos
     * consumidores
     */
    public void maiorLeituraEMenor(Map<Cliente, List<ConsumoPorDia>> leituras) {
        System.out.println("\n====EXIBIÇÃO MAIOR E MENOR CONSUMO DE CADA CLIENTE====");
        for (Cliente cliente : leituras.keySet()) {
            System.out.println("\nConsumo do(a) cliente " + cliente.getNome());
            System.out.println("Menor consumo do cliente: " + leituras.get(cliente).get(0).getConsumoMensal());
            System.out.println("Maior consumo do cliente "
                    + leituras.get(cliente).get(leituras.get(cliente).size() - 1).getConsumoMensal());

        }
    }

    /**
     * realiza a ordenacao das leituras de consumo de água por cada cliente
     * 
     * a complexidade deste método é, N^2LOGN, porque existe um loop neste metodo,
     * mas ele realiza uma chamada ao algoritmo de ordenacao cuja complexidade eh
     * NLogN
     */
    public Map<Cliente, List<ConsumoPorDia>> ordenar(Map<Cliente, List<ConsumoPorDia>> leituras) {
        Map<Cliente, List<ConsumoPorDia>> leiturasOrdenadas = new TreeMap<>();

        for (Cliente cliente : leituras.keySet()) {
            List<ConsumoPorDia> leiturasParaOrdenar = leituras.get(cliente);
            Ordenador<ConsumoPorDia> ordenador = new OrdenadorImpl(leiturasParaOrdenar);
            //chamada do método ordenar
            ordenador.ordenar();

            leiturasOrdenadas.put(cliente, leiturasParaOrdenar);
        }

        return leiturasOrdenadas;
    }

    /*
     * Este método imprime a leitura do consumo de cada dia(que é medido 3 vezes) e
     * sua
     * complexidade é O(N^3), portanto, cúbica, porque existem 3 loops(for)
     * aninhados
     */
    @Override
    public void imprimirLeiturasDeConsumoPorDia(Map<Cliente, List<ConsumoPorDia>> leiturasDeConsumoDiario, int mesTotal,
            int diaTotal) {
        System.out.println("\nCONSUMO DE CADA CLIENTE MONITORADO POR CADA MES E CADA DIA\n");
        for (Map.Entry<Cliente, List<ConsumoPorDia>> entry : leiturasDeConsumoDiario.entrySet()) {
            Cliente cliente = entry.getKey();
            List<ConsumoPorDia> leituras = entry.getValue();
            System.out.println("\nConsumo de " + cliente.getNome() + " por dia:");
            for (int mes = 0; mes < mesTotal; mes++) {
                System.out.println("Mes: " + (mes + 1));
                for (int dia = 0; dia < diaTotal; dia++) {
                    // Verifique se o índice 'mes' é válido para a lista 'leituras'
                    if (mes < leituras.size()) {
                        ConsumoPorDia consumoDia = leituras.get(mes);
                        // Verifique se o índice 'dia' é válido para o consumo do mês atual
                        if (dia < consumoDia.getConsumoDiario(mes, dia)) {
                            double consumoDiario = consumoDia.getConsumoDiario(mes, dia);
                            System.out.println("Dia " + (dia + 1) + ": " + consumoDiario + " litros");
                        } else {
                            System.out.println("Dia " + (dia + 1) + ": Nenhum dado disponível");
                        }
                    } else {
                        System.out.println("Mês " + (mes + 1) + ": Nenhum dado disponível");
                    }
                }
            }
           
        }

    }

}
