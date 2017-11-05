package Dao;

import Dao.Jdbc.ConnectionFactory;
import M.Pessoa;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class pessoaDAO implements Serializable{

    
    private Connection con = ConnectionFactory.getConnection();
    

    public void carregaPessoa(Connection connection,PreparedStatement prepara, Pessoa pessoa, boolean fechaConexao) throws SQLException{
        prepara.setString(2,pessoa.getNome()); 
        prepara.setString(3,pessoa.getCidade());
        prepara.setInt(4,pessoa.getCodTipoPessoa()); 
        prepara.setInt(5,pessoa.getCodProfissao()); 
        prepara.setString(6,pessoa.getCpf()); 
        prepara.setString(7,pessoa.getEmail()); 
        prepara.setString(8,pessoa.getAtiva());
        
        //executando no banco de dados o comando sql
        ConnectionFactory.executaSql("Salva", prepara.execute(), ConnectionFactory.fechaConexao(connection, prepara, fechaConexao));
    }
    
        
    public void novo (Pessoa pessoa){
 
        ConnectionFactory.setSql("INSERT INTO pessoa(codPessoa, nome, cidade, codTipoPessoa, codProfissao, cpf, email, Ativa) VALUES (?,?,?,?,?,?,?,?)");
        try{
            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(1,0);           
            //Carraga parametros comuns
            carregaPessoa(con, prepara, pessoa, true); 
          
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
    
    /*
    public void atualiza (Pessoa pessoa){

            ConnectionFactory.setSql("UPDATE pessoa SET codPessoa=?, nome=?, cidade=?, codTipoPessoa=?, codProfissao=?, cpf=?, email=?, Ativa=? WHERE id=?");
            try{
                    //preparando PreparedStatment com o SQL
                    //quem prepara eh o connection
                    PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
                    //substitindo os pontos de interrogacao com os dados
                    prepara.setInt(1,pessoa.getCodPessoa()); //atualizando pelo id que eh inteiro
                    carregaPessoa(con, prepara, pessoa, false);                   
                    prepara.close();

                    System.out.println("Registro Usuario -alterado- com sucesso");

            } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }
    }*/
    
    
    public void exclui (Pessoa pessoa){
            //montando o sql
        ConnectionFactory.setSql("DELETE FROM pessoa WHERE codPessoa=?");

        try{
                //preparando PreparedStatment com o SQL
                //quem prepara eh o connection
                PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
                prepara.setInt(1,pessoa.getCodPessoa()); //deletando pelo id que eh inteiro
                
                //executando no banco de dados o comando sql
                ConnectionFactory.executaSql("apaga", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true ));
                

        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
}