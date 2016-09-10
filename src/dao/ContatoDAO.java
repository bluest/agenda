package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Contato;
import model.TipoEnum;

public class ContatoDAO {
	PreparedStatement pst;
    String sql;
    
    public void salvar(Contato contato) throws SQLException{
        sql="insert into contato values(?, ?, ?, ?, ?, ?, ?, ?)";
        pst=Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, contato.getNome());
        pst.setString(3, contato.getEndereco());
        pst.setString(4, contato.getTelefone());
        pst.setString(5, contato.getCelular());
        pst.setString(6, contato.getEmail());
        pst.setInt(7, contato.getTipo().getCodigo());
        pst.setString(8, contato.getObservacao());
        pst.execute();
        pst.close();
    }
    
        public List<Contato> listarContatos() throws SQLException{
        List<Contato> listaContatos;
        listaContatos = new ArrayList<>();
        sql="select * from contato order by nome";
        pst=Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaContatos.add(new Contato(rs.getInt("codigo"), rs.getString("nome"), rs.getString("endereco"), rs.getString("telefone"), rs.getString("celular"), rs.getString("email"), TipoEnum.fromInt(rs.getInt("tipo")), rs.getString("observacao")));
        }
        pst.close();
        return listaContatos;
    }

    public void excluir(Contato contato) throws SQLException{
        sql="delete from contato where codigo=?";
        pst=Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, contato.getCodigo());
        pst.execute();
        pst.close();
    } 
    
    
    public void alterar(Contato contato) throws SQLException{
        sql="update contato set nome=?, endereco=?, telefone=?, celular=?, email=?, tipo=?, observacao=? where codigo=?";
        pst=Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, contato.getNome());
        pst.setString(2, contato.getEndereco());
        pst.setString(3, contato.getTelefone());
        pst.setString(4, contato.getCelular());
        pst.setString(5, contato.getEmail());
        pst.setInt(6, contato.getTipo().getCodigo());
        pst.setString(7, contato.getObservacao());
        pst.setInt(8, contato.getCodigo());
        pst.execute();
        pst.close();
    }
}
