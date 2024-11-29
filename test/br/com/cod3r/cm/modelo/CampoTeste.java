package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.exceção.ExplosaoException;

public class CampoTeste {
	
	private Campo campo;
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo (3,3);
	}
	
	@Test
	void testeVizinhoRealDistancia1Esquerda() {
		Campo vizinho = new Campo (3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
		
	}
	@Test
	void testeVizinhoRealDistancia1Direita() {
		Campo vizinho = new Campo (3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoRealDistancia1EmCima() {
		Campo vizinho = new Campo (2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoRealDistancia1Embaixo() {
		Campo vizinho = new Campo (4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoRealDistancia2() {
		Campo vizinho = new Campo (2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
	}
	@Test
	void testeNaoVizinhoDistancia() {
		Campo vizinho = new Campo (1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertFalse(resultado);
	}
	@Test
	void testeValorPadraoAtributoMarcado() {	
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAlternarMarcaçaoDuasChamadas() {	
		campo.alternarMarcaçao();
		campo.alternarMarcaçao();
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAlternarMarcaçao() {
		campo.alternarMarcaçao();
		assertTrue(campo.isMarcado());
	}
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {	
		assertTrue(campo.abrir());
	}
	@Test
	void testeAbrirNaoMinadoMarcado() {	
		campo.alternarMarcaçao();
		assertFalse(campo.abrir());
	}
	@Test
	 void AbrirMinadoMarcado() {	
		campo.alternarMarcaçao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	 void AbrirMinadoNaoMarcado() {	
		campo.minar();
		assertThrows(ExplosaoException.class,() ->{
			campo.abrir();
		});
		
	}
	 @Test
	 void testeAbrirComVizinhos() {
		 Campo campo11 = new Campo(1,1);
		 Campo campo22 = new Campo(2,2);
		campo.adicionarVizinho(campo11);
		 campo.adicionarVizinho(campo22);
		 campo.abrir();
		 
		 assertTrue(campo22.isAberto()&& campo11.isAberto());
	 }
	 @Test
	 void testeAbrirComVizinhos2() {
		 Campo campo11 = new Campo(1,1);
		 Campo campo12 =  new Campo(1,1);
		 campo12.minar();
		 
		 
		 Campo campo22 = new Campo(2,2);
		campo.adicionarVizinho(campo11);
		 campo.adicionarVizinho(campo22);
		 
		 campo.adicionarVizinho(campo22);
		 campo.abrir();
		 
		 assertTrue(campo22.isAberto()&& !campo11.isFechado());
	 }
}
