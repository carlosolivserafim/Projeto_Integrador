package model;

public class Colaborador  {
    private String nome;
    private String cpf;
    private String telefone;
    private String matricula;
    private int IU_COLABORADOR;

    public int getIU_COLABORADOR() {
        return IU_COLABORADOR;
    }

    public void setIU_COLABORADOR(int IU_COLABORADOR) {
        this.IU_COLABORADOR = IU_COLABORADOR;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
