package C;
import Dao.Jdbc.ConnectionFactory;
import Dao.Jdbc.BdUtil;
import Dao.ProdutosDAO;
import Dao.VendasItensDAO;
import M.Menu;
import M.Negocio.Globais;
import M.Produtos;
import M.VendaIntens;
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
public class VendaItensControle implements Serializable{
    VendaIntens vendaIntens = new VendaIntens();
    MenuView menuView = new MenuView();
    Produtos produtos = new Produtos();
    
    private Connection con = ConnectionFactory.getConnection();
    
    public  void totalizaPedido(String idProduto) throws SQLException{
        ConnectionFactory.setSql("SELECT sum(qtVenda)as qt, sum(totalValorBruto)AS vBruto, sum(valorTotal)as vTotal, sum(totalValorLiquido) as vLiq FROM `vendaintens` WHERE codVendaPedido in("+idProduto+")");
        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet
            while(resultado.next()){ //buscando valor das colunas, registro por registro
                
                vendaIntens.setQtVenda(resultado.getInt("qt"));
                vendaIntens.setValorBruto(resultado.getFloat("vTotal"));
                vendaIntens.setValorTotal(resultado.getFloat("vTotal"));
                vendaIntens.setValorLiquido(resultado.getFloat("vLiq"));                    
            }
            ConnectionFactory.fechaConexao(con, prepara, true );
        
        } catch(SQLException e){ 
            e.printStackTrace();
        }
        
        View.msgcr("Tolta geral da compra\n"
               +"\nTotal de itens: "+vendaIntens.getQtVenda()
               +"\nSubtotal bruto: "+vendaIntens.getValorBruto()
               +"\nSubtotal: "+vendaIntens.getValorTotal()
               +"\nSubtotal liquído: "+vendaIntens.getValorLiquido()
        );
            
    }
    
    
    public  void buscaProduto(String idProduto) throws SQLException{
        ConnectionFactory.setSql("CALL cProdutos("+idProduto+");");

        try{            
            PreparedStatement prepara = con.prepareStatement(ConnectionFactory.getSql());
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet

            while(resultado.next()){ //buscando valor das colunas, registro por registro
          
                    produtos.setCodProduto(resultado.getInt("codProduto"));
                    produtos.setDescProruto(resultado.getString("descProduto"));
                    produtos.setCodMarca(resultado.getInt("codMarca"));
                    produtos.setNomeMarca(resultado.getString("marca"));                        
                    produtos.setValorNotacao(resultado.getFloat("valorNotacao"));
                    produtos.setNotacao(resultado.getString("notacao"));
                    produtos.setPreco(resultado.getFloat("preco"));
                   }
            ConnectionFactory.fechaConexao(con, prepara, true );
        
        } catch(SQLException e){ 
                    //se comando sql nao estiver correto ira imprimir o erro gerado
                    e.printStackTrace();
            }

    }
    
    
    public void parametrizaProduto(boolean ativaCampo)throws Exception {
        
        if (ativaCampo == true){
            vendaIntens.setCodVendaPedido(View.digitaNumero("Id"));
        }else{
            vendaIntens.setCodVendaPedido(0);
        }
        vendaIntens.setCodVendaPedido(BdUtil.getPk());
        vendaIntens.setCodProduto(View.digitaNumero("Cod Produto"));        
        buscaProduto(String.valueOf(vendaIntens.getCodProduto()));
        View.msg("Descrição:"+produtos.getDescProruto()); 
        View.msg("\nMarca:"+produtos.getNomeMarca()); 
        View.msg("\nPreço:"+produtos.getPreco());
        View.msg("\nNotação:"+produtos.getValorNotacao()+" "+produtos.getNotacao()); 
        vendaIntens.setPreco(produtos.getPreco());        
        vendaIntens.setDesconto(View.digitaFloat("Desconto"));
        vendaIntens.setAcrescimo(View.digitaFloat("Acréscimo"));
        vendaIntens.setQtVenda(View.digitaNumero("Quantidade"));
        vendaIntens.calculaVenda();
        
        
        View.msgc("Valor total da compra\n"
            +"\nTotal bruto: " +vendaIntens.getValorBruto()
            +"\nTotal: "+vendaIntens.getValorTotal()
            +"\nTotal Liquido: "+vendaIntens.getValorLiquido());

    }
    
        
    public void recarregaMenu(boolean exibeMenuPrincipal) throws Exception{               
        View.msg("Adicionando itens de venda ao carrinho\n");
        menuView.setTipo(false);
        menuView.menuVendas(menuView.getTipo());
         new Controlador().selecionaMenu(exibeMenuPrincipal);
        
        if (exibeMenuPrincipal == false){
            executaVendaIntens();
        }
    }
    
   
    public void executaVendaIntens() throws Exception{       
       
        switch (Menu.getCod()) {
            case 1:
               
                parametrizaProduto(false);
                new VendasItensDAO().inserir(vendaIntens);
                recarregaMenu(false);                
                break;
            
            case 2:                
                parametrizaProduto(true);
                new VendasItensDAO().atualiza(vendaIntens);
                recarregaMenu(false);
                break;
 
            case 3:
                vendaIntens.setCodVendaPedido(View.digitaNumero("Id"));
                new VendasItensDAO().exclui(vendaIntens);
                recarregaMenu(false);
                break;
            
            case 4: 
                totalizaPedido(String.valueOf(BdUtil.getPk()));
                new VendaPedidoControle().executaVendaProduto();
                break;
            
            case 5:
                recarregaMenu(true);
                
                break;             
            case 6:
                recarregaMenu(true);
                
                break;
            
            case 7:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();       
                recarregaMenu(false);
                break;
            } 
    }
}