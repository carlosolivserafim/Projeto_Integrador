package repository;

import model.Atividade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtividadeDAO implements Interface<Atividade> {

    static List<Atividade> atividades = new ArrayList<>();


    @Override
    public void salvar(Atividade atividade) {
        AtividadeRepository pessoaRepository = new AtividadeRepository();
        try {
            if (atividade.getIU_ATIVIDADE() != 0) {
                pessoaRepository.update(atividade);
            } else {
                atividade.setIU_ATIVIDADE((int) pessoaRepository.proximoId().longValue());
                pessoaRepository.insere(atividade);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        atividades.add(atividade);
    }

    @Override
    public void remover(Atividade pessoa) throws SQLException, ClassNotFoundException {
        AtividadeRepository pessoaRepository = new AtividadeRepository();
        pessoaRepository.delete(pessoa);
    }

    @Override
    public List<Atividade> buscarTodos() {
        System.out.println(atividades);

        AtividadeRepository pessoaRepository = new AtividadeRepository();
        try {
            atividades = pessoaRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return atividades;
    }

    @Override
    public List<Atividade> buscarPorNome(String nome) {
        List<Atividade> pessoasFiltradas = new ArrayList<>();
        for (Atividade pessoa : atividades) {
            if (pessoa.getNome().contains(nome)) {
                pessoasFiltradas.add(pessoa);
            }
        }
        return pessoasFiltradas;
    }

    public Object[] findAtividadeInArray() {
        List<Atividade> pessoas = buscarTodos();
        List<String> pessoasNomes = new ArrayList<>();

        for (Atividade pessoa : pessoas) {
            pessoasNomes.add(pessoa.getNome());
        }

        return pessoasNomes.toArray();
    }

}

