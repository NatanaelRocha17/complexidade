import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.ifba.agua.impl.Cliente;
import br.edu.ifba.agua.impl.ConsumoPorDia;
import br.edu.ifba.agua.impl.OperacoesImpl;
import br.edu.ifba.agua.impl.SensorImpl;
import br.edu.ifba.agua.operacoes.Operacoes;
import br.edu.ifba.agua.sensor.Sensor;

public class App {

    private static final int TOTAL_DE_CLIENTES = 10;
    private static final int TOTAL_DE_MESES = 4;
    private static final int TOTAL_DE_DIAS_MES = 10;
    private static final int PRESSAO_MINIMA = 15;

    /**
     * complexidade linear, O(N), porque temos um for para gerar o total de 
     * clietes
     */

    public static void main(String[] args) throws Exception {
        Operacoes<Cliente, ConsumoPorDia> operacoes = new OperacoesImpl();
        Map<Cliente, List<ConsumoPorDia>> leiturasDeConsumoDiario = new TreeMap<>();
        Sensor<ConsumoPorDia> sensor = new SensorImpl();

        int[] tubulacao = { 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5 };
        int tubos = 0;
        for (int i = 0; i < TOTAL_DE_CLIENTES; i++) {
            int id = i + 1;
            tubos = tubos + 5;
            leiturasDeConsumoDiario.put(new Cliente(id, ("CLIENTE # " + id), tubos),
                    sensor.gerarLeiturasDiaria(TOTAL_DE_MESES, TOTAL_DE_DIAS_MES));
        }

        // d1
        operacoes.imprimir(new ArrayList<Cliente>(leiturasDeConsumoDiario.keySet()));

        // d2
        operacoes.imprimirLeituras(leiturasDeConsumoDiario);

        // d3
        Map<Cliente, List<ConsumoPorDia>> leiturasOrdenadas = operacoes.ordenar(leiturasDeConsumoDiario);
        // d4
        operacoes.maiorLeituraEMenor(leiturasOrdenadas);

        // d5
        operacoes.encontrarVazamento(tubulacao, PRESSAO_MINIMA, leiturasDeConsumoDiario);

        //d6
        operacoes.imprimirLeiturasDeConsumoPorDia(leiturasDeConsumoDiario, TOTAL_DE_MESES, TOTAL_DE_DIAS_MES);

    }
}
