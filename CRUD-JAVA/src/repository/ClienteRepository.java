package repository;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/projetomazon";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }


    public void insere(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into " +
                "cliente values(?, ?, ?, ?)");
        stmt.setInt(1, cliente.getIU_CLIENTE());
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getCpfCnpj());
        stmt.setString(4, cliente.getTelefone());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Cliente> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
        List<Cliente> pessoas = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from cliente WHERE iu_cliente = ?");
        stmt.setLong(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt(4) == 0) {
                Cliente cliente = new Cliente();
                cliente.setIU_CLIENTE(resultSet.getInt(1));
                cliente.setNome(resultSet.getString(2));
                cliente.setCpfCnpj(resultSet.getString(3));
                pessoas.add(cliente);
            } else {

            }
        }
        connection.close();
        return pessoas;
    }

    public List<Cliente> busca() throws SQLException, ClassNotFoundException {
        List<Cliente> pessoas = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from cliente");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIU_CLIENTE(resultSet.getInt(1));
                cliente.setNome(resultSet.getString(2));
                cliente.setCpfCnpj(resultSet.getString(3));
                cliente.setTelefone(resultSet.getString(4));
                pessoas.add(cliente);
        }
        connection.close();
        return pessoas;
    }


    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(iu_cliente) from cliente");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }


    public void update(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update cliente " +
                "SET nome = ?, \n" +
                " cpf_cnpj = ?, \n" +
                " telefone = ? WHERE IU_CLIENTE =?" );

        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpfCnpj());
        stmt.setString(3, cliente.getTelefone());
        stmt.setInt(4, cliente.getIU_CLIENTE());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }


    public void delete(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM cliente" +
                " WHERE iu_cliente = ?");
        stmt.setInt(1, cliente.getIU_CLIENTE());
        stmt.executeUpdate();
        connection.close();
    }

}
