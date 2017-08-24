package C.Automeveis;

import M.veiculo;
import V.View;

public class Cadastro {	
	
	
	static String v[][] = new String[3][3];
	static veiculo veiculo = new veiculo();
	
	public static void criaVetor(boolean criar, boolean imprimir){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {				
				if (criar == true && imprimir == false){
					v[i][j]= M.veiculo.getCodVeiculo();
					v[i][j]= M.veiculo.getCodVeiculo();
					v[i][j]= M.veiculo.getCodVeiculo();
									
				}else{
					if (criar == false && imprimir == true){
						View.msgcr(v[i][j]);
					}
				}
			}
		}
	}
	
	
	public static void imprimir( boolean criar, boolean imprimir){
		View.msgl();
		Cadastro.criaVetor(criar, imprimir);
	}
}
