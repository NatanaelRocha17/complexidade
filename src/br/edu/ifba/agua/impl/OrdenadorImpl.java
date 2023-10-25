package br.edu.ifba.agua.impl;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.agua.ordenador.Ordenador;

/**
 * Classe na qual, auxilia a ordenação
 * 
 * o algoritmo foi adaptado a partir do que se encontra disponibilizado em:
 * https://www.delftstack.com/howto/java/merge-sort-arraylist-java/
 * 
 * como se trata de um merge sort, a complexidade eh NLogN, porque primeiramente
 * o algoritmo divide a colecao de dados a ser ordenada e, apos a divisao, 
 * junta cada sub-colecao de dados para chegar ao ordenamento.
 */


public class OrdenadorImpl extends Ordenador<ConsumoPorDia> {
    
    public OrdenadorImpl(List<ConsumoPorDia> leituras) {
        super(leituras);
    }

    public void ordenar() {
        ordenar(0, leituras.size() - 1); 
    }

    

    public void ordenar(int inicio, int fim) {
        if (inicio < fim && (fim - inicio) >= 1) {
            int meio = (fim + inicio) / 2;

            ordenar(inicio, meio);
            ordenar(meio + 1, fim);

            ordenar(inicio, meio, fim);
        }
    }

    private void ordenar(int inicio, int meio, int fim) {
        List<ConsumoPorDia> leiturasConsumo = new ArrayList<>();

        int esquerda = inicio;
        int direita = meio + 1;

        while (esquerda <= meio && direita <= fim) {
            if (leituras.get(esquerda).getConsumoMensal() <= leituras.get(direita).getConsumoMensal()) {
                leiturasConsumo.add(leituras.get(esquerda));
                esquerda++;
            } else {
                leiturasConsumo.add(leituras.get(direita));
                direita++;
            }
        }

        while (esquerda <= meio) {
            leiturasConsumo.add(leituras.get(esquerda));
            esquerda++;
        }

        while (direita <= fim) {
            leiturasConsumo.add(leituras.get(direita));
            direita++;
        }

        for (int i = 0; i < leiturasConsumo.size(); inicio++) {
            leituras.set(inicio, leiturasConsumo.get(i++));
        }
    }

}