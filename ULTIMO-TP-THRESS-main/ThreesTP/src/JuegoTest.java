package ;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JuegoTest {

	@Test
	public void puntajeParaunaFicha() {
		// Dado: un tablero con una sola ficha de valor 48 (y el resto vacío)
		int[] tablero = { 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		Negocio negocio = new Negocio();
		int actual = negocio.puntaje(tablero);

		// Entonces: el puntaje debe ser 3^5 = 243
		int esperado = 243; // porque 48 = 3 × 2^4 → exponente = 4 + 1 = 5 → 3^5 = 243
		assertEquals("Puntaje incorrecto para ficha 48", esperado, actual);
	}

	@Test
	public void puntajeParaFichasMultiples() {
		// Dado: tablero con 3, 6 y 12 (valores válidos > 2)
		int[] tablero = { 3, 6, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		Negocio negocio = new Negocio();
		int actual = negocio.puntaje(tablero);

		// Esperado: 3^1 + 3^2 + 3^3 = 3 + 9 + 27 = 39
		int esperado = 39;
		assertEquals("Puntaje incorrecto para combinación 3+6+12", esperado, actual);
	}

	@Test
	public void puntajeRecordHistoricoDeGideon() {
		// Dado: Tablero final histórico de Gideon (@ThreesPorn), el primer jugador
		// en "completar" el juego al fusionar dos fichas de 6.144 para crear un 12.288.
		// Puntuación máxima registrada legítimamente: 1.594.458 puntos.
		// Fuentes: Vice, IGN.Representacion de puntaje antes del bloqueo del tablero:
		int[] tablero = { 12288, 24, 12, 12, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };		Negocio negocio = new Negocio();
		int actual = negocio.puntaje(tablero);

		// Esperado: 1.594.458
		// (12288=1.594.323 + 24=81 + 12=27  + 12=27     
		int esperado = 1594458;

		assertEquals("Puntaje incorrecto para el récord histórico de Gideon", esperado, actual);
	}

	@Test
	public void puntajeParaConfiguracionTeoricamenteImposible() {
		// Dado: Tablero con la configuración teóricamente máxima pero prácticamente
		// imposible
		// 98,304 (3×2^15) es el valor máximo teórico, pero es imposible de alcanzar en
		// la práctica
		// debido a las reglas de generación aleatoria de fichas y necesidad de espacio
		// para mover
		int[] tablero = { 98304, 49152, 24576, 12288, 6144, 3072, 1536, 768, 384, 192, 96, 48, 24, 12, 6, 3 };

		Negocio negocio = new Negocio();
		int actual = negocio.puntaje(tablero);

		// Esperado: Puntaje teórico máximo (64,570,080)
		// Pero en la práctica, este tablero es imposible de alcanzar
		int esperado = 64570080; // Suma de la serie geométrica 3^1 + 3^2 + ... + 3^16

		// Este test serviría para verificar que nuestro cálculo de puntaje funciona
		// para valores extremos, aunque en la práctica nunca se alcanzaría esta
		// configuración
		assertEquals("El cálculo de puntaje falla para la configuración teórica máxima", esperado, actual);
	}
}
