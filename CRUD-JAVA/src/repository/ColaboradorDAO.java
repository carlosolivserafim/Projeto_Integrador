package repository;

import model.Colaborador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ColaboradorDAO implements Interface<Colaborador> {

        static List<Colaborador> pessoas = new ArrayList<>();


                @Override
                public void salvar(Colaborador pessoa) {
                    ColaboradorRepository colaboradorRepository = new ColaboradorRepository();
                        try {
                        if (pessoa.getIU_COLABORADOR() != 0) {
                            colaboradorRepository.update(pessoa);
                        } else {
                            pessoa.setIU_COLABORADOR((int) colaboradorRepository.proximoId().longValue());
                            colaboradorRepository.insereColab(pessoa);
                        }
                        } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                        }

                    pessoas.add(pessoa);
                        }

                @Override
                public void remover(Colaborador pessoa) throws SQLException, ClassNotFoundException {
                    ColaboradorRepository pessoaRepository = new ColaboradorRepository();
                        pessoaRepository.delete(pessoa);
                        }

                @Override
                public List<Colaborador> buscarTodos() {
                        System.out.println(pessoas);

                    ColaboradorRepository pessoaRepository = new ColaboradorRepository();
                        try {
                        pessoas = pessoaRepository.busca();
                        } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                        }

                        return pessoas;
                        }

                @Override
                public List<Colaborador> buscarPorNome(String nome) {
                        List<Colaborador> pessoasFiltradas = new ArrayList<>();
                        for (Colaborador pessoa : pessoas) {
                        if (pessoa.getNome().contains(nome)) {
                        pessoasFiltradas.add(pessoa);
                        }
                        }
                        return pessoasFiltradas;
                        }

                public Object[] findColaboradorInArray() {
                        List<Colaborador> pessoas = buscarTodos();
                        List<String> pessoasNomes = new ArrayList<>();

                        for (Colaborador pessoa : pessoas) {
                        pessoasNomes.add(pessoa.getNome());
                        }

                        return pessoasNomes.toArray();
                        }

                        }
