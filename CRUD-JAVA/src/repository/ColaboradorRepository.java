package repository;

import model.Colaborador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorRepository {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/projetomazon";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }


    public void insereColab(Colaborador colaborador) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into " +
                "colaborador values(?, ?, ?, ?,?)");
        stmt.setInt(1,colaborador.getIU_COLABORADOR());
        stmt.setString(2, colaborador.getNome());
        stmt.setString(3, colaborador.getMatricula());
        stmt.setString(4, colaborador.getCpf());
        stmt.setString(5, colaborador.getTelefone());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

//    public List<Colaborador> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
//        List<Colaborador> colaborador = new ArrayList<>();
//        Connection connection = getConnection();
//
//        PreparedStatement stmt = connection.prepareStatement("select * from colaborador WHERE iu_colaborador = ?");
//        stmt.setLong(1, id);
//        ResultSet resultSet = stmt.executeQuery();
//
//        while (resultSet.next()) {
//            if (resultSet.getInt(4) == 0) {
//                Colaborador colaboradors = new Colaborador();
//                colaborador.setIU_CLIENTE(resultSet.getInt(1));
//                colaborador.setNome(resultSet.getString(2));
//                colaborador.setCpfCnpj(resultSet.getString(3));
//                colaborador.add(colaboradors);
//            } else {
//
//            }
//        }
//        connection.close();
//        return colaborador;
//    }

    public List<Colaborador> busca() throws SQLException, ClassNotFoundException {
        List<Colaborador> colaborador = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from colaborador");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Colaborador colaboradors = new Colaborador();
            colaboradors.setIU_COLABORADOR(resultSet.getInt(1));
            colaboradors.setNome(resultSet.getString(2));
            colaboradors.setMatricula(resultSet.getString(3));
            colaboradors.setCpf(resultSet.getString(4));
            colaboradors.setTelefone(resultSet.getString(5));
            colaborador.add(colaboradors);
        }
        connection.close();
        return colaborador;
    }


    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(iu_colaborador) from colaborador");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }


    public void update(Colaborador colaborador) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update colaborador " +
                "SET nome = ?, \n" +
                " matricula = ?, \n" +
                " cpf = ?,\n" +
                " telefone = ? WHERE IU_COLABORADOR =?" );

        stmt.setString(1, colaborador.getNome());
        stmt.setString(2, colaborador.getMatricula());
        stmt.setString(3, colaborador.getCpf());
        stmt.setString(4, colaborador.getTelefone());
        stmt.setInt(5, colaborador.getIU_COLABORADOR());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }


    public void delete(Colaborador colaborador) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM colaborador" +
                " WHERE iu_colaborador = ?");
        stmt.setInt(1, Math.toIntExact(colaborador.getIU_COLABORADOR()));
        stmt.executeUpdate();
        connection.close();
    }

}

