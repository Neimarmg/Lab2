package C.Automeveis;

import M.veiculo;
import V.View;

public class crud extends veiculo{
	
	public static void inserir(boolean criarVetor){
		

		setCodVeiculo(View.digitaString("Cod"));
		setModelo(View.digitaString("Modelo"));
		setPlaca(View.digitaString("Placa"));		
	   
		if (criarVetor = true){
			Cadastro.criaVetor(true,false);
		}			
	}
	
	
	public void buscar(){
		
	}
	
	public void excluir(){
		
	}
}


