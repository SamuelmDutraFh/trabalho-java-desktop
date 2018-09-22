/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.sistemapedidos.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.ufpr.sistemapedidos.connection.ConnectionFactory;
import org.ufpr.sistemapedidos.model.entity.Cliente;

/**
 *
 * @author samue
 */
public class ClienteDAO {

    private Connection connection;
    private PreparedStatement stmtAdiciona;
    PreparedStatement stmtExcluir;
    PreparedStatement stmtAtualiza;

    public ClienteDAO() throws SQLException {

        this.connection = ConnectionFactory.getConnection();
        this.stmtAdiciona = connection.prepareStatement("insert into cliente (nome,sobrenome,cpf) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        this.stmtExcluir = connection.prepareStatement("delete from cliente WHERE id=?;");
        this.stmtAtualiza = this.connection.prepareStatement("update cliente set nome=?, sobrenome=?, cpf=? WHERE id=?;");

    }

    public List<Cliente> getLista() throws SQLException {
        ResultSet rs = null;
        PreparedStatement stmtLista = this.connection.prepareStatement("select * from cliente");
        try {
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
            while (rs.next()) {
                // criando o objeto Contato
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setCpf(rs.getString("cpf"));
                // adicionando o objeto à lista
                clientes.add(cliente);
            }

            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            rs.close();
            stmtLista.close();
        }

    }

    public void adicionar(Cliente cliente) {

        //String sql = "insert into contatos (nome,email,endereco,dataNascimento) values (?,?,?,?)";
        try {
            // prepared statement para inserção
            //PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmtAdiciona.setString(1, cliente.getNome());
            stmtAdiciona.setString(2, cliente.getSobrenome());
            stmtAdiciona.setString(3, cliente.getCpf());

            // executa
            stmtAdiciona.execute();
            //Seta o id do cliente
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            cliente.setId(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void excluir(Cliente cliente) throws SQLException {

        try {
            stmtExcluir.setInt(1, cliente.getId());
            stmtExcluir.executeUpdate();
        } finally{
            stmtExcluir.close();
        }
        
    }

    public void atualizar(Cliente cliente) throws SQLException {

        try {
            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getSobrenome());
            stmtAtualiza.setString(3, cliente.getCpf());            
            stmtAtualiza.setInt(4, cliente.getId());
            stmtAtualiza.executeUpdate();
        } finally{
            stmtAtualiza.close();
        }
        
    }

}
