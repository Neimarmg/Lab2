package Dao;
import Dao.Jdbc.ConnectionFactory;
import M.Pessoa;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author neimarmoises
 */
public class PessoaDao {

    private Connection con = ConnectionFactory.getConnection();

    public void salvar (Pessoa pessoa){
        //montando o sql
        String sql = "INSERT INTO pessoa(nome, cidade, codTipoPessoa, codProfissao, cpf, email, Ativa) VALUES (?,?,?,?,?,?,?)";
        try{
                //preparando PreparedStatment com o SQL
                //quem prepara eh o connection
                PreparedStatement prepara = con.prepareStatement(sql);
                
                prepara.setString(1,pessoa.getNome()); //inserindo nome no primeiro '?' (nome) 
                prepara.setString(2,pessoa.getCidade()); //inserindo nome no segundo '?' (login) 
                prepara.setInt(3,pessoa.getCodTipoPessoa()); //inserindo nome no terceiro '?' (senha) 
                prepara.setInt(4,pessoa.getCodProfissao());
                prepara.setString(5,pessoa.getCpf());
                prepara.setString(6,pessoa.getEmail());
                prepara.setString(7,pessoa.getAtiva());
                
                
                //executando no banco de dados o comando sql
                prepara.execute();   //execute retorna um booleano
                prepara.close();

                System.out.println("Registro Usuario -salvo- com sucesso");

        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }
	/*	
		public void atualizar (Usuario usuario){
			//montando o sql
			String sql = "UPDATE usuario SET nome=?, login=?, senha=? WHERE id=?";
			
			//String sql = "UPDATE paciente SET rg=?, nome=?, datanascimento=? "+ "WHERE id=?";
			try{
				//preparando PreparedStatment com o SQL
				//quem prepara eh o connection
				PreparedStatement prepara = con.prepareStatement(sql);
				
				//substitindo os pontos de interrogacao com os dados
				
				String nome = usuario.getNome();
				String login = usuario.getLogin();
				String senha = usuario.getSenha();
				int id = usuario.getId();
				
				prepara.setString(1,nome); 
				prepara.setString(2,login); 
				prepara.setString(3,senha); 
				prepara.setInt(4,id); //atualizando pelo id que eh inteiro
				
				//executando no banco de dados o comando sql
				prepara.execute();
				prepara.close();

				System.out.println("Registro Usuario -alterado- com sucesso");

			} catch(SQLException e){ 
				//se comando sql nao estiver correto ira imprimir o erro gerado
				e.printStackTrace();
			}
		}
		
		public void deletar (Usuario usuario){
			//montando o sql
			String sql = "DELETE FROM usuario WHERE id=?";
			
			try{
				//preparando PreparedStatment com o SQL
				//quem prepara eh o connection
				PreparedStatement prepara = con.prepareStatement(sql);
				
				int id = usuario.getId();
				prepara.setInt(1,id); //deletando pelo id que eh inteiro
				
				//executando no banco de dados o comando sql
				prepara.execute();
				prepara.close();

				System.out.println("Registro Usuario -Deletado- com sucesso");

			} catch(SQLException e){ 
				//se comando sql nao estiver correto ira imprimir o erro gerado
				e.printStackTrace();
			}
		}
		
		public List<Usuario> listarTodos(){ //procurar todos nao tem parametro
			
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
		}
                */

}
