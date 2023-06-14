
public class BaseDeConhecimento {
	MaquinaDeInferencia mi = new MaquinaDeInferencia();
	MundoWumpus mw = new MundoWumpus();
	Agente a;
	int mundo[][]; // mundo Wumpus conhecido (0: desconhecido; 1: seguro; -1: não seguro);
	boolean caminho[][];
	int guarda[][];
	int linha = 0;
	int coluna = 0;
	int cont = 0, k = 1;
    boolean carimbar[][][];
    
 
	public BaseDeConhecimento() {
		int i, j;
		mundo = new int[4][4];
		caminho = new boolean[4][4];
		guarda = new int[8][2];
		carimbar= new boolean [8][3][1];
		// inicialização do mundo inicial conhecido
		for (i = 0; i < 4; i++)
			for (j = 0; j < 4; j++) {
				mundo[i][j] = 0;
				caminho[i][j] = false;

			}
		caminho[0][0] = true;
		guarda[0][0] = 0;
		guarda[0][1] = 0;
	
		mundo[0][0] = 1;

	}

	public int[] ask(boolean percep[], int i, int j) {
		int linha,l=0,c;
		int coluna;

		int posicao[] = new int[2];
		boolean brisa = percep[0];
		boolean fedor = percep[1];
		boolean brilho = percep[2];
		boolean poco = percep[3];
		boolean Wumpus = percep[4];
		boolean tesouro = percep[5];
	
    
		if (caminho[i][j] != true) {
			guarda[k][0] = i;
			guarda[k][1] = j;
			 
			k++;
		}

		if ((i == 0) && (j == 0)) { // sala (0,0)
			mundo[0][0] = 1;
			if (!caminho[0][1]) {
				posicao[0] = i;
				posicao[1] = +1;
			} else {
				posicao[0] = +1;
				posicao[1] = j;
			}
		} else {
			if ((j - 1 >= 0) && (!brisa && fedor)
					|| (brisa && !fedor) && (caminho[i][j + 1] == false && caminho[i + 1][j] == false)
					|| (!brisa && !fedor) && (caminho[i - 1][j] == false)) {
				mundo[i][j] = -1;
				caminho[i][j] = true;
				posicao[0] = i;
				posicao[1] = j - 1;
			} else {
				if (((!brisa && fedor)
						|| (brisa && !fedor) && (caminho[i][j + 1] == false) && caminho[i + 1][j] == false)
						&& (j + 1 < 4)) {
					posicao[0] = i;
					posicao[1] = j + 1;
					caminho[i][j] = true;
					mundo[i][j] = -1;
				} else {
					if ((!brisa && fedor) || (brisa && !fedor)
							|| (!brisa && !fedor) && (caminho[i + 1][j] == false) && (caminho[i][j + 1] == false)
									&& ((caminho[i - 1][j] == true || caminho[i][j - 1] == true))) {
						posicao[0] = i;
						posicao[1] = j + 1;
						caminho[i][j] = true;
						mundo[i][j] = -1;
					} else {
						if (((!brisa && fedor) || (brisa && !fedor) || (!brisa && !fedor) && caminho[i - 1][j] == true)
								&& (caminho[i][j + 1] == true) && (caminho[i][j - 1] == true)
								&& (caminho[i][j] == true)) {
							posicao[0] = i + 1;
							posicao[1] = j;
							caminho[i][j] = true;
							mundo[i][j] = -1;
						}
					}
				}
			}
			if (tesouro==true) {
				caminho[i][j] = true;
				mundo[i][j] = 1;
				 System.out.println("Achei o tesouro!!Voltando ");
				 linha = guarda[5-cont][0];
				 coluna = guarda[5-cont][1];
					
					
					System.out.println(" " + i + ", " + j);
					System.out.println(" " + linha + ", " + coluna);
		        
				this.mostrarCaminho();
                posicao[0] = linha;
				posicao[1] = coluna;
				cont++;
			}
			
		}
		

		return posicao;
	}

	public void tell(int i, int j) {
		mi.chamar(i, j);
	}


	public void mostrarCaminho() {
		int l;
		System.out.println("Caminho:");
		for (l = 0; l < k; l++) {

			System.out.println(" " + guarda[l][0] + ", " + guarda[l][1]);

		}
	}

}






/*public boolean retornar(boolean percep[], int i, int j) {
		int linha,l,c;
		int coluna;

//System.out.println(""+percep[5]);
		if(percep[5]==true && cont<=5) {
			linha = guarda[5-cont][0];
			coluna = guarda[5-cont][1];
			a = new Agente(mw, i, j);
			System.out.println(" " + linha + ", " + coluna);
        
			
			
			
			
	
		a.mover(linha, coluna, mw, percep[5]);
		
		
//					
//			linha = guarda[5-cont][0];
//			coluna = guarda[5-cont][1];
//			mw.atualizarAgente(i, j,linha, coluna, true);
//			mw.atualizaMundoTela(true);
		//	mw.mostraMundo(percep[5]);
		
		this.mostrarCaminho();
		cont++;
	}
		
return percep[5];
	}*/
