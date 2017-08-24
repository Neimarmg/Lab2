package App;

import C.Automeveis.Cadastro;
import C.Automeveis.CustoEstacionamento;
import C.Automeveis.TrianguloCalculos;
import C.Automeveis.crud;
import V.View;


public class start {

	public static void main(String[] args) {
		
		//View.msgcr("\nCadastros de veiculos\n");
		//crud.inserir(true);
		//Cadastro.imprimir(false, true);
		
		//View.msgr("\n\n\nCadastros de triangulo\n");
		//TrianguloCalculos.imprimir(true);
		
		
		View.msgr("\n\n\nEstacionemento\n");
		CustoEstacionamento.imprimir(true);
		
	}

}
