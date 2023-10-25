package br.edu.ifba.agua.impl;



/**
 * classe para simular a coisa monitorada, um paciente,
 * cuja complexidade, de forma geral, eh constante, O(1)
 */
public class Cliente implements Comparable<Cliente> {
    
    private int id;
    private String nome;
    private int numeroDaTubulacaoResidencia;


    
    public Cliente(int id, String nome, int numeroDaTubulacaoResidencia) {
        this.id = id;
        this.nome = nome;
        this.numeroDaTubulacaoResidencia = numeroDaTubulacaoResidencia;
    }



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public int getNumeroDaTubulacaoResidencia() {
        return numeroDaTubulacaoResidencia;
    }



    public void setNumeroDaTubulacaoResidencia(int numeroDaTubulacaoResidencia) {
        this.numeroDaTubulacaoResidencia = numeroDaTubulacaoResidencia;
    }



    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", numeroDaTubulacaoResidencia=" + numeroDaTubulacaoResidencia
                + "]";
    }
    
    
    public int compareTo(Cliente outroCliente) {
        // Lógica de comparação aqui (por exemplo, comparar por ID)
        return Integer.compare(this.id, outroCliente.id);
    }

}
