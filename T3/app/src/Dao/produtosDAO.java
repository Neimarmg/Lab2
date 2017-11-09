package Dao;

import Dao.Jdbc.ConnectionFactory;
import M.Negocio.Globais;
import M.Produtos;
import M.Utilitarios;
import V.View;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;



public class produtosDAO implements Serializable{
    
    Utilitarios utilitarios = new Utilitarios();
    List<Produtos> listaDeProdutos = new ArrayList<Produtos>();
    private Connection con = ConnectionFactory.getConnection();
    
    public void carregaPessoa(Connection connection,PreparedStatement prepara, Produtos produtos) throws SQLException{
        prepara.setString(Globais.getContador(true, false),produtos.getDescProruto());        
        prepara.setInt(Globais.getContador(true, false),produtos.getCodMarca());
        prepara.setFloat(Globais.getContador(true, false),produtos.getValorNotacao());
        prepara.setInt(Globais.getContador(true, false),produtos.getCodNotacao());        
        prepara.setFloat(Globais.getContador(true, false),produtos.getPreco());      
    }
    
        
    public void inserir (Produtos produtos){
 
        ConnectionFactory.setSql("INSERT INTO produtos(codProduto, descProduto, codMarca, valorNotacao, codNotacao, preco) VALUES (?,?,?,?,?,?)");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(Globais.getContador(true, true),0);           
            carregaPessoa(con, prepara, produtos); 
            ConnectionFactory.executaSql("Salva", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));
          
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
    

    public void atualiza (Produtos produtos){

        ConnectionFactory.setSql("UPDATE pessoa SET codPessoa=?, nome=?, cidade=?, codTipoPessoa=?, codProfissao=?, cpf=?, email=?, Ativa=? WHERE codPessoa=?");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());           
            prepara.setInt(Globais.getContador(true, true), produtos.getCodProduto()); //Pula primeira posição da tabela 
            carregaPessoa(con, prepara, produtos);                   
            prepara.setInt(9,produtos.getCodProduto());             
            ConnectionFactory.executaSql("Altera", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true));

        } catch(SQLException e){ 
            //se comando sql nao estiver correto ira imprimir o erro gerado
            e.printStackTrace();
        }
    }
    
    
    public void exclui (Produtos produtos){
        ConnectionFactory.setSql("DELETE FROM pessoa WHERE codPessoa=?");
        try{
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(1,produtos.getCodProduto()); //deletando pelo id que eh inteiro
            ConnectionFactory.executaSql("apaga", prepara.execute(), ConnectionFactory.fechaConexao(con, prepara, true ));

        } catch(SQLException e){ 
            e.printStackTrace();
        }
    }
    
    
    public List<Produtos> listarTodos(){ //procurar todos nao tem parametr00o

        //montando o sql
        ConnectionFactory.setSql(
                "SELECT \n" +
                    "codPessoa,\n" +
                    "nome, \n" +
                    "cidade,\n" +
                    "codTipoPessoa,\n" +
                    "tipo.utilitario as Tipo,\n" +
                    "codProfissao,\n" +
                    "profissao.utilitario as profissao, \n" +
                    "cpf, \n" +
                    "email, \n" +
                    "ativa \n" +
                    "\n" +
                "FROM pessoa\n" +
                    "LEFT JOIN utilitarios as tipo  ON\n" +
                        "pessoa.codTipoPessoa = tipo.codUtilitario\n" +
                    "\n" +
                    "LEFT JOIN utilitarios as profissao ON	\n" +
                        "pessoa.codProfissao = profissao.codUtilitario");

        try{
            //preparando PreparedStatment com o SQL
            //quem prepara eh o connection
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            //executando ---CONSULTA--- no banco de dados o comando sql
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet

            while(resultado.next()){ //buscando valor das colunas, registro por registro
                Produtos produtos  = new Produtos();
                
                    /*pessoa.setCodPessoa(resultado.getInt("codPessoa"));
                    pessoa.setNome(resultado.getString("nome"));
                    pessoa.setCidade(resultado.getString("cidade"));
                    pessoa.setCodTipoPessoa(resultado.getInt("codTipoPessoa"));                        
                    utilitarios.setUtilitario(resultado.getString("tipo"));
                    pessoa.setCodProfissao(resultado.getInt("codProfissao"));
                    utilitarios.setObs(resultado.getString("profissao"));
                    pessoa.setCpf(resultado.getString("cpf"));
                    pessoa.setEmail(resultado.getString("email"));
                    pessoa.setAtiva(resultado.getString("ativa"));*/              
 
                listaDeProdutos.add(produtos);
            }
            ConnectionFactory.fechaConexao(con, prepara, true );

            View.msgr("Listando Todos os Registros");
            
            } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }

            return listaDeProdutos;
    }
    
    
    public void imprime(){
        listarTodos();
        
        for (Produtos produtos:listaDeProdutos)
            View.msg("\n"+produtos.getCodProduto()
                  +","+produtos.getDescProruto()
                  +", "+produtos.getCodMarca()                          
                
                    );
        
        View.msgl();
              
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