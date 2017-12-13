package Dao;

import Dao.Jdbc.ConnectionFactory;

import model.Produtos;
import model.Utilitarios;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.negocio.Globais;



public class UtilitariosDAO implements Serializable{
    
    Utilitarios utilitarios = new Utilitarios();
    public List<Utilitarios> listaUtilitarios = new ArrayList<Utilitarios>();

    public List<Utilitarios> getListaDeUtilitarios() {
        return listaUtilitarios;
    }
    private Connection con = ConnectionFactory.getConnection();

    public Connection getCon() {
        return con;
    }
    
    
    
    public void carregaPessoa(Connection connection,PreparedStatement prepara, Utilitarios utilitarios) throws SQLException{
        prepara.setString(Globais.getContador(true, false),utilitarios.getUtilitario());        
        prepara.setInt(Globais.getContador(true, false),utilitarios.getCodTipoUtilirario());
        prepara.setString(Globais.getContador(true, false),utilitarios.getObs());
        prepara.setInt(Globais.getContador(true, false),utilitarios.getCodSubGrupo());
        prepara.setString(Globais.getContador(true, false),utilitarios.getFavorita());

      
    }
    
        
    public void inserir (Utilitarios utilitarios){
 
        ConnectionFactory.setSql("INSERT INTO utilitarios(codUtilitario, utilitario, codTipoUtilirario, Obs, `codSubGrupo, favorita) VALUES (?,?,?,?,?,?)");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(Globais.getContador(true, true),0);           
            carregaPessoa(con, prepara, utilitarios); 
            ConnectionFactory.executaSql("Salva", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));
          
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
    
/*
    public void atualiza (Utilitarios utilitarios){

        ConnectionFactory.setSql("UPDATE produtos SET codProduto=?, descProduto=?, codMarca=?, valorNotacao=?, codNotacao=?, preco=? WHERE codProduto=?");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());           
            prepara.setInt(Globais.getContador(true, true), produtos.getCodProduto()); //Pula primeira posição da tabela 
            carregaPessoa(con, prepara, produtos);                   
            prepara.setInt(Globais.getContador(true, false),produtos.getCodProduto());             
            ConnectionFactory.executaSql("Altera", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));

        } catch(SQLException e){ 
            //se comando sql nao estiver correto ira imprimir o erro gerado
            e.printStackTrace();
        }
    }
    */
    
    public void exclui (Utilitarios utilitarios){
        ConnectionFactory.setSql("DELETE FROM utilitarios WHERE codUtilitario=?");
        try{
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(1,utilitarios.getCodUtilitario()); //deletando pelo id que eh inteiro
            ConnectionFactory.executaSql("apaga", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true ));

        } catch(SQLException e){ 
            e.printStackTrace();
        }
    }
    
    
    public List<Utilitarios> listarTodos(String idUtilitario, String idTipoUtilitarios){ //procurar todos nao tem parametr00o
        ConnectionFactory.setSql("call cUtilitarios ("+idUtilitario+","+idTipoUtilitarios+")");

        try{
            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet

            while(resultado.next()){ //buscando valor das colunas, registro por registro
                Utilitarios utilitarios  = new Utilitarios();
                
                    utilitarios.setCodUtilitario(resultado.getInt("CodUtilitario"));
                    utilitarios.setUtilitario(resultado.getString("utilitario"));
                    //utilitarios.setCodTipoUtilirario(resultado.getInt("codTipoUtilirario"));
                  
                listaUtilitarios.add(utilitarios);
            }
            ConnectionFactory.fechaConexao(con, prepara, true );

            //View.msgr("Listando Todos os Registros");
            
            } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }

            return listaUtilitarios;
    }
    
    
    public void imprime(String idUtilitario, String idTipoUtilitarios){
        listarTodos(idUtilitario,idTipoUtilitarios);             
        view.View.msg("id "+"Descrição ");
        for (Utilitarios utilitarios:listaUtilitarios)
            view.View.msg(
                   "\n"+ utilitarios.getCodUtilitario()
                  +", "+ utilitarios.getUtilitario()
            );
        
        view.View.msgl();
              
    }
    
}








/*public List<Usuario> listarTodos(){ //procurar todos nao tem parametro
			
			List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
			
			//montando o sql
			String sql = "SELECT * FROM usuario";
			
			try{
				//preparando PreparedStatment com o SQL
				//quem prepara eh o connection
				PreparedStatement prepara = con.prepareStatement(sql);
				//executando ---CONSULTA--- no banco de dados o comando sql
				ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet
				
				while(resultado.next()){ //buscando valor das colunas, registro por registro
					
					Usuario usu  = new Usuario();

					int id = resultado.getInt("id");
					String nome = resultado.getString("nome");
					String login = resultado.getString("login");
					String senha = resultado.getString("senha");

					usu.setId(id);
					usu.setNome(nome);
					usu.setLogin(login);
					usu.setSenha(senha);

					listaDeUsuarios.add(usu);
				}
				prepara.close();

				System.out.println("Listando Todos os Registros");

			} catch(SQLException e){ 
				//se comando sql nao estiver correto ira imprimir o erro gerado
				e.printStackTrace();
			}
			
			return listaDeUsuarios;
		}*/