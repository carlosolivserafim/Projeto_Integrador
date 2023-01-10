package model;

public class Cliente {


    private int IU_CLIENTE;

    public int getIU_CLIENTE() {
        return IU_CLIENTE;
    }

    public void setIU_CLIENTE(int IU_CLIENTE) {
        this.IU_CLIENTE = IU_CLIENTE;
    }

    private String nome;
    private String cpfCnpj;
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}


