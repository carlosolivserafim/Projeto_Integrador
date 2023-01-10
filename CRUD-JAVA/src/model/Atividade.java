package model;

public class Atividade {

    private String nome ;
    private int valor;
    private String status ;
    private String data_inicio ;
    private String entrega_estimada;
    private String progresso ;
    private int IU_ATIVIDADE;
    private Projeto projeto;

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public int getIU_ATIVIDADE() {
        return IU_ATIVIDADE;
    }

    public void setIU_ATIVIDADE(int IU_ATIVIDADE) {
        this.IU_ATIVIDADE = IU_ATIVIDADE;
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


