package C;
import Dao.Jdbc.ConnectionFactory;
import Dao.VendasPedidoDAO;
import M.Menu;
import M.Pessoa;
import M.VendaPedido;
import V.Utilitarios.MenuView;
import V.View;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




/**
 *
 * @author neimarmoises
 */
public class VendaPedidoControle implements Serializable{
    private Connection con = ConnectionFactory.getConnection();
    VendaPedido vendaPedido = new VendaPedido();
    MenuView menuView = new MenuView();
    Pessoa pessoa = new Pessoa();

     
     
    public  void buscaCliente(String idPessoa) throws SQLException{
        ConnectionFactory.setSql("CALL cPessoa("+idPessoa+");");

        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet
            while(resultado.next()){ //buscando valor das colunas, registro por registro
                    pessoa.setNome(resultado.getString("nome"));
                    pessoa.setCidade(resultado.getString("cidade"));                   
                    pessoa.setTipoPessoa(resultado.getString("tipoPessoa"));
                    pessoa.setProfissoa(resultado.getString("Profissao"));
                    pessoa.setCpf(resultado.getString("cpf"));
                   }
            ConnectionFactory.fechaConexao(con, prepara, true );
        
        } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }
        
          View.msgcr(
               "\nNome: "+pessoa.getNome()
               +"\nCpf: "+pessoa.getCpf()
               +"\nTipo: "+pessoa.getTipoPessoa()
               +"\nProfissão: "+pessoa.getProfissoa()
               +"\nCidade: "+pessoa.getCidade()
        );

    }
    
    
    
    public void parametrizaProduto(boolean ativaCampo)throws Exception {
        
        if (ativaCampo == true){
            vendaPedido.setCodVendaPedido(View.digitaNumero("Id"));
        }else{
            vendaPedido.setCodVendaPedido(0);
        }
        vendaPedido.setCodCliente(View.digitaNumero("Cod Cliente"));
        buscaCliente(vendaPedido.getNomeCliente());
        vendaPedido.setDataVenda(View.digitaString("Data venda"));
       

    }
    
        
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{               
        View.msgr("Criar um pedido de venda");
        menuView.setTipo(true);
        menuView.menuVendas(menuView.getTipo());
        new Controlador().selecionaMenu(exibeMenuPrincipal);
        
        if (exibeMenuPrincipal == false){
            executaVendaProduto();
        }
    }
    
   
    public void executaVendaProduto() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:               
                parametrizaProduto(false);
                new VendasPedidoDAO().inserir(vendaPedido);
                new VendaItensControle().executaVendaIntens();
                recarregaMenu(false);
                break;
            
            case 2:                
                parametrizaProduto(true);
                new VendasPedidoDAO().atualiza(vendaPedido);
                recarregaMenu(false);
                break;
 
            case 3:
                vendaPedido.setCodVendaPedido(View.digitaNumero("Id"));
                new VendasPedidoDAO().exclui(vendaPedido);
                recarregaMenu(false);
                break;
            
            case 4:                
                //new VendasPedidoDAO().imprime();  
                recarregaMenu(false); 
                
                break;
                
            case 5:
                recarregaMenu(true);
                break;
            
            case 6:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                recarregaMenu(false);
                break;
            } 
    }
}
