package Dao;

import Dao.Jdbc.ConnectionFactory;
import M.Negocio.Globais;
import M.Pessoa;
import V.View;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;



public class pessoaDAO implements Serializable{

  
    private Connection con = ConnectionFactory.getConnection();
    
    public void carregaPessoa(Connection connection,PreparedStatement prepara, Pessoa pessoa, boolean fechaConexao) throws SQLException{
        prepara.setString(Globais.getContador(true, false),pessoa.getNome());        
        prepara.setString(Globais.getContador(true, false),pessoa.getCidade());
        prepara.setInt(Globais.getContador(true, false),pessoa.getCodTipoPessoa());
        prepara.setInt(Globais.getContador(true, false),pessoa.getCodProfissao());        
        prepara.setString(Globais.getContador(true, false),pessoa.getCpf()); 
        prepara.setString(Globais.getContador(true, false),pessoa.getEmail()); 
        prepara.setString(Globais.getContador(true, false),pessoa.getAtiva());
    }
    
        
    public void inserir (Pessoa pessoa){
 
        ConnectionFactory.setSql("INSERT INTO pessoa(codPessoa, nome, cidade, codTipoPessoa, codProfissao, cpf, email, Ativa) VALUES (?,?,?,?,?,?,?,?)");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            prepara.setInt(Globais.getContador(true, true),0);           
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
            
            prepara.setInt(Globais.getContador(true, true), pessoa.getCodPessoa()); //Pula primeira posição da tabela 
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
    
    
    public List<Pessoa> listarTodos(){ //procurar todos nao tem parametro
			
        List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();

        //montando o sql
        ConnectionFactory.setSql("SELECT * FROM Pessoa");

        try{
            //preparando PreparedStatment com o SQL
            //quem prepara eh o connection
            PreparedStatement prepara = con.prepareStatement(sql);
            //executando ---CONSULTA--- no banco de dados o comando sql
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet

            while(resultado.next()){ //buscando valor das colunas, registro por registro
                Pessoa pessoa  = new Pessoa();
    
                pessoa.setCodPessoa(codPessoa);         
                pessoa.setNome("nome");
                pessoa.setCidade(View.digitaString("cidade"));
                pessoa.setCodTipoPessoa(View.digitaNumero("codTipoPessoa"));
                pessoa.setCodProfissao(View.digitaNumero("codProfisao"));
                pessoa.setCpf(View.digitaString("cpd"));
                pessoa.setEmail(View.digitaString("E-mail"));
                pessoa.setAtiva(View.digitaString("Ativa"));     
                
                listaDePessoas.add(pessoa);
            }
            prepara.close();

            System.out.println("Listando Todos os Registros");

            } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }

            return listaDePessoas;
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