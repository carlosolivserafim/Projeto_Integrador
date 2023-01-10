package model;

import javax.swing.*;

public class Projeto {

    private String nome ;
    private int valor;
    private String status ;
    private String data_inicio ;
    private String entrega_estimada;
    private String progresso ;
    private int IU_PROJETO;
    private Cliente cliente;
    private Colaborador colaborador;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public int getIU_PROJETO() {
        return IU_PROJETO;
    }

    public void setIU_PROJETO(int IU_PROJETO) {
        this.IU_PROJETO = IU_PROJETO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getEntrega_estimada() {
        return entrega_estimada;
    }

    public void setEntrega_estimada(String entrega_estimada) {
        this.entrega_estimada = entrega_estimada;
    }

    public String getProgresso() {
        return progresso;
    }

    public void setProgresso(String progresso) {
        this.progresso = progresso;
    }
}
