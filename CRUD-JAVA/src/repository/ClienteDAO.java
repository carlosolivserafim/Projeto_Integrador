package repository;

import model.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ClienteDAO implements Interface<Cliente> {

    static List<Cliente> pessoas = new ArrayList<>();


    @Override
    public void salvar(Cliente pessoa) {
        ClienteRepository pessoaRepository = new ClienteRepository();
        try {
            if (pessoa.getIU_CLIENTE() != 0) {
                pessoaRepository.update(pessoa);
            } else {
                pessoa.setIU_CLIENTE((int) pessoaRepository.proximoId().longValue());
                pessoaRepository.insere(pessoa);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        pessoas.add(pessoa);
    }

    @Override
    public void remover(Cliente pessoa) throws SQLException, ClassNotFoundException {
        ClienteRepository pessoaRepository = new ClienteRepository();
        pessoaRepository.delete(pessoa);
    }

    @Override
    public  List<Cliente> buscarTodos() {
        System.out.println(pessoas);

        ClienteRepository pessoaRepository = new ClienteRepository();
        try {
            pessoas = pessoaRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return pessoas;
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> pessoasFiltradas = new ArrayList<>();
        for (Cliente pessoa : pessoas) {
            if (pessoa.getNome().contains(nome)) {
                pessoasFiltradas.add(pessoa);
            }
        }
        return pessoasFiltradas;
    }

    public Object[] findPessoasInArray() {
        List<Cliente> pessoas = buscarTodos();
        List<String> pessoasNomes = new ArrayList<>();

        for (Cliente pessoa : pessoas) {
            pessoasNomes.add(pessoa.getNome());
        }

        return pessoasNomes.toArray();
    }

}
