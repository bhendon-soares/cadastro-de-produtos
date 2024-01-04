package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;
/**
 * @author Bhendon Soares
 */

public class ProdutoDAO {
    
    public void create(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        try {
            pst = con.prepareStatement("INSERT INTO produtos (cod, status, data_cadastro, nome, descricao, qtd_estoque, estoque_minimo, "
                                              + "estoque_maximo, preco_compra, preco_venda, fator_lucro, ncm, codigo_barras) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            pst.setString(1, p.getCodigo());
            pst.setString(2, p.getStatus());
            pst.setString(3, p.getData_cadastro());
            pst.setString(4, p.getNome());
            pst.setString(5, p.getDescricao());
            pst.setInt(6, p.getQtd_estoque());
            pst.setInt(7, p.getEstoque_minimo());
            pst.setInt(8, p.getEstoque_maximo());
            pst.setFloat(9, p.getPreco_compra());
            pst.setFloat(10, p.getPreco_venda());
            pst.setFloat(11, p.getFator_lucro());
            pst.setInt(12, p.getNcm());
            pst.setInt(13, p.getCodigo_barras());
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex);
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, pst);
        }
    }
    
    public List<Produto> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("SELECT * FROM produtos");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setCodigo(rs.getString("cod"));
                produto.setStatus(rs.getString("status"));
                produto.setData_cadastro(rs.getString("data_cadastro"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd_estoque(rs.getInt("qtd_estoque"));
                produto.setEstoque_minimo(rs.getInt("estoque_minimo"));
                produto.setEstoque_maximo(rs.getInt("estoque_maximo"));
                produto.setPreco_compra(rs.getFloat("preco_compra"));
                produto.setPreco_venda(rs.getFloat("preco_venda"));
                produto.setFator_lucro(rs.getFloat("fator_lucro"));
                produto.setNcm(rs.getInt("ncm"));
                produto.setCodigo_barras(rs.getInt("codigo_barras"));
                produtos.add(produto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        
        return produtos;
    }
    
    public void update(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        try {
            pst = con.prepareStatement("UPDATE produtos SET status = ?, data_cadastro = ?, nome = ?, descricao = ?, qtd_estoque = ?, estoque_minimo = ?, "
                                              + "estoque_maximo = ?, preco_compra = ?, preco_venda = ?, fator_lucro = ?, ncm = ?, codigo_barras = ? WHERE id = ?;");
            pst.setString(1, p.getStatus());
            pst.setString(2, p.getData_cadastro());
            pst.setString(3, p.getNome());
            pst.setString(4, p.getDescricao());
            pst.setInt(5, p.getQtd_estoque());
            pst.setInt(6, p.getEstoque_minimo());
            pst.setInt(7, p.getEstoque_maximo());
            pst.setFloat(8, p.getPreco_compra());
            pst.setFloat(9, p.getPreco_venda());
            pst.setFloat(10, p.getFator_lucro());
            pst.setInt(11, p.getNcm());
            pst.setInt(12, p.getCodigo_barras());
            pst.setInt(13, p.getId());
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex);
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, pst);
        }
    }
    
    public void delete(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        try {
            pst = con.prepareStatement("DELETE FROM produtos WHERE id = ?;");
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex);
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, pst);
        }
    }
    
}
