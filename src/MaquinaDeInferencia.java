
public class MaquinaDeInferencia {
    boolean caminho[][];

    public MaquinaDeInferencia() {
        int i, j;
        caminho = new boolean[4][4];
        // inicialização do caminho percorrido
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                caminho[i][j] = false;
            }
        }
        caminho[0][0] = true;
    }

    public void chamar(int i, int j) {
        caminho[i][j] = true; // marca a sala como caminho percorrido
    }
    
    public boolean getCaminho(int i, int j) {	
    	return caminho[i][j];
    }

}
