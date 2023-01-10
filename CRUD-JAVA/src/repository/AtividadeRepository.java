package repository;


import model.Atividade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtividadeRepository {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/projetomazon";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }


    public void insere(Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into " +
                "atividade values(?, ?, ?, ?,?,?,?,?)");
        stmt.setInt(1, atividade.getIU_ATIVIDADE());
        stmt.setString(2, atividade.getNome());
        stmt.setInt(3, atividade.getValor());
        stmt.setString(4, atividade.getStatus());
        stmt.setString(5, atividade.getProgresso());
        stmt.setString(6, atividade.getData_inicio());
        stmt.setString(7, atividade.getEntrega_estimada());
        stmt.setInt(8, atividade.getProjeto().getIU_PROJETO());


        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Atividade> busca() throws SQLException, ClassNotFoundException {
        List<Atividade> atividades = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from atividade");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Atividade atividade = new Atividade();
            atividade.setIU_ATIVIDADE(resultSet.getInt(1));
            atividade.setNome(resultSet.getString(2));
            atividade.setValor(Integer.parseInt(resultSet.getString(3)));
            atividade.setStatus(resultSet.getString(4));
            atividade.setProgresso(resultSet.getString(5));
            atividade.setData_inicio(resultSet.getString(5));
            atividade.setEntrega_estimada(resultSet.getString(7));

            atividades.add(atividade);
        }
        connection.close();
        return atividades;
    }


    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(iu_atividade) from atividade");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }


    public void update(Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update atividade " +
                "SET nome = ?, \n" +
                " valor = ?, \n" +
                " status = ?,\n" +
                "progresso = ?,\n" +
                "data_inicio = ?, \n" +
                "entrega_estimada = ? WHERE IU_ATIVIDADE = ?" );

        stmt.setString(1, atividade.getNome());
        stmt.setInt(2, atividade.getValor());
        stmt.setString(3, atividade.getStatus());
        stmt.setString(4, atividade.getProgresso());
        stmt.setString(5, atividade.getData_inicio());
        stmt.setString(6, atividade.getEntrega_estimada());
        stmt.setInt(7, atividade.getIU_ATIVIDADE());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }


    public void delete(Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM atividade" +
                " WHERE iu_atividade = ?");
        stmt.setInt(1, atividade.getIU_ATIVIDADE());
        stmt.executeUpdate();
        connection.close();
    }

}
