package Dao;

import Dao.Jdbc.ConnectionFactory;
import Dao.Jdbc.Util;
import M.Negocio.Globais;
import M.VendaPedido;
import V.View;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;




public class VendasPedidoDAO implements Serializable{

    private Connection con = ConnectionFactory.getConnection();
    
    public static void carregaVendaPedido(Connection connection,PreparedStatement prepara, VendaPedido vendaPedido) throws SQLException{
        prepara.setInt(Globais.getContador(true, false),vendaPedido.getCodCliente());        
        prepara.setString(Globais.getContador(true, false),Globais.dataSql(vendaPedido.getDataVenda()));
   
    }
    
        
    public void inserir (VendaPedido vendaPedido){
 
        ConnectionFactory.setSql("INSERT INTO vendapedido(codVendaPedido, codCliente, dataVenda) VALUES (?,?,?)");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(Globais.getContador(true, true),0);           
            carregaVendaPedido(con, prepara, vendaPedido); 
            ConnectionFactory.executaSql("Salva", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));
            
            ConnectionFactory.executaSql("SELECT max(codVendaPedido) as id FROM vendapedido", true, true);
            Util.setPk(con, prepara);
            View.msg(Util.getPk());
            
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
    

    public void atualiza (VendaPedido vendaPedido){

        ConnectionFactory.setSql("UPDATE vendapedido SET codVendaPedido=?, codCliente=?, dataVenda=? WHERE codVendaPedido=?");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());           
            prepara.setInt(Globais.getContador(true, true), vendaPedido.getCodVendaPedido()); //Pula primeira posição da tabela 
            carregaVendaPedido(con, prepara, vendaPedido);                   
            prepara.setInt(Globais.getContador(true, false),vendaPedido.getCodVendaPedido());             
            ConnectionFactory.executaSql("Altera", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));
      
        
            
        } catch(SQLException e){ 
            //se comando sql nao estiver correto ira imprimir o erro gerado
            e.printStackTrace();
        }
    }
    
    
    public void exclui (VendaPedido vendaPedido){
        ConnectionFactory.setSql("DELETE FROM vendapedido WHERE codVendaPedido=?");
        try{
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(1,vendaPedido.getCodVendaPedido()); //deletando pelo id que eh inteiro
            ConnectionFactory.executaSql("apaga", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true ));

        } catch(SQLException e){ 
            e.printStackTrace();
        }
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