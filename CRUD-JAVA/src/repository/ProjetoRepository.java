package repository;

import model.Projeto;
import model.Colaborador;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoRepository  {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url = "jdbc:mysql://localhost:3306/projetomazon";
    Connection connection = DriverManager.getConnection(url, "root", "");

    return connection;
}


        public void insere(Projeto projeto) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("insert into " +
                    "projeto values(?, ?, ?, ?,?,?,?,?,?)");
            stmt.setInt(1, projeto.getIU_PROJETO());
            stmt.setString(2, projeto.getNome());
            stmt.setInt(3, projeto.getValor());
            stmt.setString(4, projeto.getStatus());
            stmt.setString(5, projeto.getData_inicio());
            stmt.setString(6, projeto.getEntrega_estimada());
            stmt.setString(7, projeto.getProgresso());
            stmt.setInt(8, projeto.getCliente().getIU_CLIENTE());
            stmt.setInt(9, projeto.getColaborador().getIU_COLABORADOR());

            int i = stmt.executeUpdate();
            System.out.println(i + " linhas inseridas");
            connection.close();
        }

//        public List<Projeto> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
//            List<Projeto> projetos = new ArrayList<>();
//            Connection connection = getConnection();
//
//            PreparedStatement stmt = connection.prepareStatement("select * from projeto WHERE iu_projeto = ?");
//            stmt.setLong(1, id);
//            ResultSet resultSet = stmt.executeQuery();
//
//            while (resultSet.next()) {
//                Projeto projeto = new Projeto();
//                projeto.setIU_PROJETO((int) resultSet.getLong(1));
//
//                //FK
//                ClienteRepository clienteRepository = new ClienteRepository();
//                projeto.setIU_PROJETO(clienteRepository.buscaPorId(resultSet.getLong(2)).get(0));
//
//                //FK
//                SeguradoraRepository seguradoraRepository = new SeguradoraRepository();
//                seguro.setSeguradora(seguradoraRepository.buscaPorId(resultSet.getLong(3)).get(0));
//
//                seguro.setTipo(TipoSeguro.getTipoById(resultSet.getInt(4)));
//                seguro.setValorApolice(resultSet.getBigDecimal(5));
//                seguro.setValorPremio(resultSet.getBigDecimal(6));
//                seguros.add(seguro);
//
//
//
//                    Projeto projeto = new Projeto();
//                    projeto.setIU_CLIENTE(resultSet.getInt(1));
//                    projeto.setNome(resultSet.getString(2));
//                    projeto.setCpfCnpj(resultSet.getString(3));
//                    projetos.add(projeto);
//
//
//            }
//            connection.close();
//            return projetos;
//        }

        public List<Projeto> busca() throws SQLException, ClassNotFoundException {
            List<Projeto> projetos = new ArrayList<>();
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("select * from projeto");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Projeto projeto = new Projeto();
                projeto.setIU_PROJETO(resultSet.getInt(1));
                projeto.setNome(resultSet.getString(2));
                projeto.setValor(Integer.parseInt(resultSet.getString(3)));
                projeto.setStatus(resultSet.getString(4));
                projeto.setData_inicio(resultSet.getString(5));
                projeto.setEntrega_estimada(resultSet.getString(6));
                projeto.setProgresso(resultSet.getString(7));
//                projeto.setColaborador(resultSet.getString(8));
//                projeto.setStatus(resultSet.getString(9));
                projetos.add(projeto);
            }
            connection.close();
            return projetos;
        }


        public Integer proximoId() throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("select max(iu_projeto) from projeto");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            }
            return 1;
        }


        public void update(Projeto projeto) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("update projeto " +
                    "SET nome = ?, \n" +
                    " valor = ?, \n" +
                    " status = ?,\n" +
                    "data_inicio = ?,\n" +
                    "entrega_estimada = ?,\n" +
                    "progresso = ?  WHERE IU_PROJETO =?" );

            stmt.setString(1, projeto.getNome());
            stmt.setInt(2, projeto.getValor());
            stmt.setString(3, projeto.getStatus());
            stmt.setString(4, projeto.getData_inicio());
            stmt.setString(5, projeto.getEntrega_estimada());
            stmt.setString(6, projeto.getProgresso());
            stmt.setInt(7, projeto.getIU_PROJETO());

            int i = stmt.executeUpdate();
            System.out.println(i + " linhas atualizadas");
            connection.close();
        }


        public void delete(Projeto projeto) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM projeto" +
                    " WHERE iu_projeto = ?");
            stmt.setInt(1, projeto.getIU_PROJETO());
            stmt.executeUpdate();
            connection.close();
        }

}

