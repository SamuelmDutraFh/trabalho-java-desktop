/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.sistemapedidos.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.ufpr.sistemapedidos.connection.ConnectionFactory;
import org.ufpr.sistemapedidos.model.entity.Produto;

/**
 *
 * @author samue
 */
public class ProdutoDAO {
    
    private Connection connection;
    private PreparedStatement stmtAdiciona;
    PreparedStatement stmtExcluir;
    PreparedStatement stmtAtualiza;

    public ProdutoDAO() throws SQLException {

        this.connection = ConnectionFactory.getConnection();
        this.stmtAdiciona = connection.prepareStatement("insert into produto (descricao) values (?)", Statement.RETURN_GENERATED_KEYS);
        this.stmtExcluir = connection.prepareStatement("delete from produto WHERE id=?;");
        this.stmtAtualiza = this.connection.prepareStatement("update produto set descricao=? WHERE id=?;");

    }

    public List<Produto> getLista() throws SQLException {
        ResultSet rs = null;
        PreparedStatement stmtLista = this.connection.prepareStatement("select * from produto");
        try {
            rs = stmtLista.executeQuery();
            List<Produto> produtos = new ArrayList();
            while (rs.next()) {
                // criando o objeto Contato
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                // adicionando o objeto à lista
                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            rs.close();
            stmtLista.close();
        }

    }

    public void adicionar(Produto produto) {

        //String sql = "insert into contatos (nome,email,endereco,dataNascimento) values (?,?,?,?)";
        try {
            // prepared statement para inserção
            //PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmtAdiciona.setString(1, produto.getDescricao());

            // executa
            stmtAdiciona.execute();
            //Seta o id do produto
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            produto.setId(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void excluir(Produto produto) throws SQLException {

        try {
            stmtExcluir.setInt(1, produto.getId());
            stmtExcluir.executeUpdate();
        } finally{
            stmtExcluir.close();
        }
        
    }

    public void atualizar(Produto produto) throws SQLException {

        try {
            stmtAtualiza.setString(1, produto.getDescricao());
            stmtAtualiza.setInt(2, produto.getId());
            stmtAtualiza.executeUpdate();
        } finally{
            stmtAtualiza.close();
        }
    }
    
}
