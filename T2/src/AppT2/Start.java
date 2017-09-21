/*Pesquisar 15ap, hanna, vora*/
package AppT2;



import C.Controlador;
import V.Utilitarios.Dia;
import V.View;

public class Start {
   
    public static void main(String[] args) throws Exception {

           View.msgcr("\n\nPrograma de vendas\n\n" );
           Dia.defineSaudacao();
           View.msgl();
           new Controlador().selecionaMenu(true);
           new Controlador().carregaApp();
    }
}
