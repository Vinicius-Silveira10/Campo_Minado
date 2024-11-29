package br.com.cod3r.cm.visao;

import br.com.cod3r.cm.exceção.ExplosaoException;
import br.com.cod3r.cm.exceção.SairExeception;
import br.com.cod3r.cm.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;


public class TabuleiroConsole {
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				 cicloDoJogo();
				
				System.out.println("Outra partida?(S/n)");
				String resposta = entrada.nextLine();
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
					tabuleiro.reiniciar();
				}
			}
			
		}catch (SairExeception e) {
			System.out.println("Tchau!!!");
		}finally{
			entrada.close();
		}
	}
	private void cicloDoJogo() {
		try {
			
			while (!tabuleiro.objetivoAlcançado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y):");
				
				 Iterator<Integer> xy= Arrays.stream(digitado.split(","))
				.map(e -> Integer.parseInt(e.trim())).iterator();
				
				 digitado = capturarValorDigitado("1 - abrir ou 2 -(Des)Marcar:");
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(),xy.next());
				}else if ("2".equals(digitado)){
					tabuleiro.alternarMarcaçao(xy.next(),xy.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Você Ganhou!");
		}catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado =  entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairExeception();		
	}
		return digitado;
}
}
