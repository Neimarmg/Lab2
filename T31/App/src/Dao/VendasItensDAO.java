package Dao;

import Dao.Jdbc.ConnectionFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.VendaIntens;
import model.negocio.Globais;



public class VendasItensDAO implements Serializable{

    public VendasItensDAO() {
    }
    
    

    List<VendaIntens> listaDeVendaItens = new ArrayList<VendaIntens>();
    private Connection con = ConnectionFactory.getConnection();
    
    public void carregaPessoa(Connection connection,PreparedStatement prepara, VendaIntens vendaIntens) throws SQLException{
        prepara.setInt(Globais.getContador(true, false),vendaIntens.getCodVendaPedido());
        prepara.setInt(Globais.getContador(true, false),vendaIntens.getCodProduto());        
        prepara.setInt(Globais.getContador(true, false),vendaIntens.getQtVenda());
        prepara.setDouble(Globais.getContador(true, false),vendaIntens.getDesconto());
        prepara.setDouble(Globais.getContador(true, false),vendaIntens.getAcrescimo());
        prepara.setDouble(Globais.getContador(true, false),vendaIntens.getValorBruto());
        prepara.setDouble(Globais.getContador(true, false),vendaIntens.getValorTotal());
        prepara.setDouble(Globais.getContador(true, false),vendaIntens.getValorLiquido());        
    }
    
        
    public void inserir (VendaIntens vendaIntens){
 
        ConnectionFactory.setSql("INSERT INTO vendaintens(codVendaIntens, codVendaPedido, codProduto, qtVenda, desconto, acrescimo, totalValorBruto, valorTotal , totalValorLiquido) VALUES (?,?,?,?,?,?,?,?,?)");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(Globais.getContador(true, true),0);           
            carregaPessoa(con, prepara, vendaIntens); 
            ConnectionFactory.executaSql("Salva", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));
          
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
    

    public void atualiza (VendaIntens vendaIntens){

        ConnectionFactory.setSql("UPDATE vendaintens(codVendaIntens=?, codVendaPedido=?, codProduto=?, qtVenda=?, desconto=?, acrescimo, totalValorBruto=?, valorTotal=? , totalValorLiquido=?) WHERE codVendaIntens=?");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());           
            prepara.setInt(Globais.getContador(true, true), vendaIntens.getCodVendaIntens()); //Pula primeira posição da tabela 
            carregaPessoa(con, prepara, vendaIntens);                   
            prepara.setInt(Globais.getContador(true, false),vendaIntens.getCodVendaIntens());             
            ConnectionFactory.executaSql("Altera", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));

        } catch(SQLException e){ 
            //se comando sql nao estiver correto ira imprimir o erro gerado
            e.printStackTrace();
        }
    }
    
    
    public void exclui (VendaIntens vendaIntens){
        ConnectionFactory.setSql("DELETE FROM vendaintens WHERE codVendaIntens=?");
        //View.
        try{
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(1,vendaIntens.getCodVendaIntens()); //deletando pelo id que eh inteiro
            ConnectionFactory.executaSql("apaga", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true ));

        } catch(SQLException e){ 
            e.printStackTrace();
        }
    }
    
    /*
    public List<Produtos> listarTodos(){ //procurar todos nao tem parametr00o
   
        ConnectionFactory.setSql("CALL cProdutos(0);");

        try{
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet

            while(resultado.next()){ //buscando valor das colunas, registro por registro
                VendaIntens vendaIntens  = new VendaIntens();
                
                    vendaIntens.setCodProduto(resultado.getInt("codProduto"));
                    vendaIntens.setDescProruto(resultado.getString("descProduto"));
                    vendaIntens.setCodMarca(resultado.getInt("codMarca"));
                    vendaIntens.setNomeMarca(resultado.getString("marca"));                        
                    vendaIntens.setValorNotacao(resultado.getFloat("valorNotacao"));
                    vendaIntens.setNotacao(resultado.getString("notacao"));
                    vendaIntens.setPreco(resultado.getFloat("preco"));
             
 
                listaDeVendaItens.add(vendaIntens);
            }
            ConnectionFactory.fechaConexao(con, prepara, true );

            View.msgr("Listando Todos os Registros");
            
            } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }

            return listaDeVendaItens;
    }
    
    
    public void imprime(){
        listarTodos();             
        View.msg("id "+"Descrição ");
        for (Produtos produtos:listaDeVendaItens)
            View.msg("\n"+produtos.getCodProduto()
                  +", "+produtos.getDescProruto()
                  +", "+produtos.getCodMarca()                          
                  +", "+produtos.getNomeMarca()
                  +", "+produtos.getValorNotacao()
                  +", "+produtos.getNotacao()
                  +", "+produtos.getPreco()
            );
        
        View.msgl();
              
    }*/
    
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