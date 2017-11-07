package Dao;

import Dao.Jdbc.ConnectionFactory;
import M.Negocio.Globais;
import M.Pessoa;
import V.View;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class pessoaDAO implements Serializable{

  
    private Connection con = ConnectionFactory.getConnection();
    

    public void carregaPessoa(Connection connection,PreparedStatement prepara, Pessoa pessoa, boolean fechaConexao) throws SQLException{
        prepara.setString(2,pessoa.getNome()); 
        View.msg(Globais.getContador(true, false, false));
        prepara.setString(3,pessoa.getCidade());
        //View.msg(Globais.getContador(true, false, false));
        prepara.setInt(4,pessoa.getCodTipoPessoa()); 
        //View.msg(Globais.getContador(true, false, false));
        prepara.setInt(5,pessoa.getCodProfissao());
        //View.msg(Globais.getContador(true, false, false));
        prepara.setString(6,pessoa.getCpf()); 
        //View.msg(Globais.getContador(true, false, false));
        prepara.setString(7,pessoa.getEmail()); 
        //View.msg(Globais.getContador(true, false, false));
        prepara.setString(8,pessoa.getAtiva());
        //View.msg(Globais.getContador(true, false, false));
        //View.msg(Globais.getContador(false, false, true ));
    }
    
        
    public void novo (Pessoa pessoa){
 
        ConnectionFactory.setSql("INSERT INTO pessoa(codPessoa, nome, cidade, codTipoPessoa, codProfissao, cpf, email, Ativa) VALUES (?,?,?,?,?,?,?,?)");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            View.msg(Globais.getContador(true, true, true));
            prepara.setInt(1,0);           
            carregaPessoa(con, prepara, pessoa, true); 
            ConnectionFactory.executaSql("Salva", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));
          
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
    

    public void atualiza (Pessoa pessoa){

        ConnectionFactory.setSql("UPDATE pessoa SET codPessoa=?, nome=?, cidade=?, codTipoPessoa=?, codProfissao=?, cpf=?, email=?, Ativa=? WHERE codPessoa=?");
        try{
            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            
            prepara.setInt(1, pessoa.getCodPessoa()); //Pula primeira posição da tabela 
            carregaPessoa(con, prepara, pessoa, false);                   
            prepara.setInt(9,pessoa.getCodPessoa()); // 
            
            ConnectionFactory.executaSql("Altera", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));

        } catch(SQLException e){ 
            //se comando sql nao estiver correto ira imprimir o erro gerado
            e.printStackTrace();
        }
    }
    
    
    public void exclui (Pessoa pessoa){
            //montando o sql
        ConnectionFactory.setSql("DELETE FROM pessoa WHERE codPessoa=?");
        try{
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(1,pessoa.getCodPessoa()); //deletando pelo id que eh inteiro
            ConnectionFactory.executaSql("apaga", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true ));

        } catch(SQLException e){ 
            e.printStackTrace();
        }
    }
}