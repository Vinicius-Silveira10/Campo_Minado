package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.cod3r.cm.exceção.ExplosaoException;

public class Tabuleiro {
	private int QLinhas;
	private int QColunas;
	private int QMinas;
	
	private final List <Campo> campos = new ArrayList<>();
	
	public Tabuleiro(int QLinhas, int QColunas, int QMinas) {
		this.QLinhas = QLinhas;
		this.QColunas = QColunas;
		this.QMinas = QMinas;
		
		 gerarCampos();		
		associarVizinhos();
		sortearMinas();
	}

	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream()
			.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(c -> c.abrir ());
			
		}catch(ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
		
	public void alternarMarcaçao(int linha, int coluna) {
			campos.parallelStream()
				.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
				.findFirst()
				.ifPresent(c -> c.alternarMarcaçao());
				
			}

			
	
	private void gerarCampos() {
		for (int l = 0; l <QLinhas; l++) {
			for (int c = 0; c <QColunas; c++) {
				campos.add(new Campo(l,c));
			}
		} 
	}
	
	private void associarVizinhos() {
		for(Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate <Campo> QMinado = c -> c.isMinado();
		
		do {
			int aleatorio = (int) (Math.random()*campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(QMinado).count();
		
		}while(minasArmadas < QMinas);	
	}
	public boolean objetivoAlcançado() {
		return campos.stream().allMatch(c -> c.objetivoAlcançado());
	}
	
	public void reiniciar () {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");

		for(int c = 0; c< QColunas; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\n");
	
		int i =0;
		for (int l = 0; l < QLinhas; l++) {
			sb.append(l);
			sb.append(" ");
			for (int c = 0; c < QColunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");

		}
			
		
		
		return sb.toString();
	}

	

			
	
}
