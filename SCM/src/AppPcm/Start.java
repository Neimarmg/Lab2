/*Pesquisar 15ap, hanna, vora*/
package AppPcm;


import C.Controlador;
import V.Utilitarios.Dia;
import V.Utilitarios.View;

public class Start {
    static Controlador menu = new Controlador();
    public static void main(String[] args) {

            View.msgcr("\n\nPrograma de consultório médico\n\n" );
            Dia.defineSaudacao();
            View.msgl();
            menu.carregaApp();

    }
}
