import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		boolean p[]; // = new boolean[7];
		int posicao[] = new int[2];

		MundoWumpus mw = new MundoWumpus();
		BaseDeConhecimento bc = new BaseDeConhecimento();
		MaquinaDeInferencia mi = new MaquinaDeInferencia();
		mw.inicializaMundo();
		Agente a = new Agente(mw, 0, 0);
		int pL, pC;
		do {
			p = a.getPercep();

			mw.mostraMundo(p[5]);

			posicao = a.getPosicaoAtual();
			pL = posicao[0];
			pC = posicao[1];
			posicao = bc.ask(p, posicao[0], posicao[1]);
			a.mover(posicao[0], posicao[1], mw, p[5]);
			bc.tell(posicao[0], posicao[1]);

			System.out.println("Continua 1-SIM/2-NÃO?");
		} while (teclado.nextInt() == 1 && !(p[5] == true && pL == 0 && pC == 0));
		System.out.println("Missãoo executada com SUCESSOO!!!");
	}

}
