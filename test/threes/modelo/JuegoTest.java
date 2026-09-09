package threes.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JuegoTest {

	// Método auxiliar para inyectar el array de 16 posiciones (1D) en el tablero
	// (2D) de Juego
	private void configurarTableroParaTest(Juego juego, int[] valores) {
		Tablero tablero = juego.obtenerTablero();
		int indice = 0;

		for (int fila = 0; fila < Tablero.TAMANIO; fila++) {
			for (int columna = 0; columna < Tablero.TAMANIO; columna++) {
				// Sobrescribimos cualquier ficha aleatoria inicial con los valores de nuestro
				// test
				tablero.asignarValor(fila, columna, valores[indice]);
				indice++;
			}
		}
	}

	@Test
	public void puntajeParaunaFicha() {
		// Dado: un tablero con una sola ficha de valor 48 (y el resto vacío)
		int[] estadoTablero = { 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		Juego juego = new Juego();
		configurarTableroParaTest(juego, estadoTablero);

		int actual = juego.obtenerPuntaje();

		// Entonces: el puntaje debe ser 3^5 = 243
		int esperado = 243;
		assertEquals("Puntaje incorrecto para ficha 48", esperado, actual);
	}

	@Test
	public void puntajeParaFichasMultiples() {
		// Dado: tablero con 3, 6 y 12 (valores válidos > 2)
		int[] estadoTablero = { 3, 6, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		Juego juego = new Juego();
		configurarTableroParaTest(juego, estadoTablero);

		int actual = juego.obtenerPuntaje();

		// Esperado: 3^1 + 3^2 + 3^3 = 3 + 9 + 27 = 39
		int esperado = 39;
		assertEquals("Puntaje incorrecto para combinación 3+6+12", esperado, actual);
	}

	@Test
	public void puntajeRecordHistoricoDeGideon() {
		// Dado: Tablero final histórico de Gideon (@ThreesPorn)
		int[] estadoTablero = { 12288, 24, 12, 12, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		Juego juego = new Juego();
		configurarTableroParaTest(juego, estadoTablero);

		int actual = juego.obtenerPuntaje();

		// Esperado: 1.594.458
		int esperado = 1594458;
		assertEquals("Puntaje incorrecto para el récord histórico de Gideon", esperado, actual);
	}

	@Test
	public void puntajeParaConfiguracionTeoricamenteImposible() {
		// Dado: Tablero con la configuración teóricamente máxima
		int[] estadoTablero = { 98304, 49152, 24576, 12288, 6144, 3072, 1536, 768, 384, 192, 96, 48, 24, 12, 6, 3 };

		Juego juego = new Juego();
		configurarTableroParaTest(juego, estadoTablero);

		int actual = juego.obtenerPuntaje();

		// Esperado: Puntaje teórico máximo (64,570,080)
		int esperado = 64570080;
		assertEquals("El cálculo de puntaje falla para la configuración teórica máxima", esperado, actual);
	}
}