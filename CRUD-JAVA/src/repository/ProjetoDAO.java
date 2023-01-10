package repository;

import model.Projeto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO implements Interface<Projeto> {

    static List<Projeto> projetos = new ArrayList<>();


    @Override
    public void salvar(Projeto projeto) {
        ProjetoRepository pessoaRepository = new ProjetoRepository();
        try {
            if (projeto.getIU_PROJETO() != 0) {
                pessoaRepository.update(projeto);
            } else {
                projeto.setIU_PROJETO((int) pessoaRepository.proximoId().longValue());
                pessoaRepository.insere(projeto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        projetos.add(projeto);
    }

    @Override
    public void remover(Projeto projeto) throws SQLException, ClassNotFoundException {
        ProjetoRepository pessoaRepository = new ProjetoRepository();
        pessoaRepository.delete(projeto);
    }

    @Override
    public List<Projeto> buscarTodos() {
        System.out.println(projetos);

        ProjetoRepository pessoaRepository = new ProjetoRepository();
        try {
            projetos = pessoaRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return projetos;
    }

//    public List<Projeto> buscarPorId(Long id) throws SQLException, ClassNotFoundException {
//        ProjetoRepository seguroRepository = new ProjetoRepository();
//        List<Projeto> projetos1 = seguroRepository.buscaPorId(id);
//        return projetos1;
//    }

    @Override
    public List<Projeto> buscarPorNome(String nome) {
        List<Projeto> pessoasFiltradas = new ArrayList<>();
        for (Projeto pessoa : projetos) {
            if (pessoa.getNome().contains(nome)) {
                pessoasFiltradas.add(pessoa);
            }
        }
        return pessoasFiltradas;
    }

    public Object[] findProjetoInArray() {
        List<Projeto> pessoas = buscarTodos();
        List<String> pessoasNomes = new ArrayList<>();

        for (Projeto pessoa : pessoas) {
            pessoasNomes.add(pessoa.getNome());
        }

        return pessoasNomes.toArray();
    }

}


