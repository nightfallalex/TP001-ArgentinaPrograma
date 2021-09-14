package paquete;

import java.util.Arrays;

public class Taquilla {

	private Promocion[] promociones;
	private Usuario[] usuarios;
	private Atraccion[] atracciones;

	public Atraccion[] getAtracciones() {
		return atracciones;
	}

	public Promocion[] getPromociones() {
		return promociones;

	}

	public Usuario[] getUsuarios() {
		return usuarios;
	}

	public void agregarTodasLasAtracciones(Atraccion[] atracciones) {
		this.atracciones = atracciones;
	}

	public void agregarTodasLasPromociones(Promocion[] promociones) {
		this.promociones = promociones;
	}

	public void agregarTodosLosUsuarios(Usuario[] usuarios) {
		this.usuarios = usuarios;
	}

	public Atraccion obtenerAtraccionPorTipo(TipoAtraccion tipo) {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getTipo().equals(tipo)) {
				return atraccion;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Parque del Terror [atracciones=" + Arrays.toString(atracciones) + ", promociones=\n"
				+ Arrays.toString(promociones) + ", usuarios=\n" + Arrays.toString(usuarios) + "]";
	}

	public Atraccion obtenerAtraccionPorNombreAtraccion(String nombreAtraccion) {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(nombreAtraccion)) {
				return atraccion;
			}
		}
		return null;
	}

	public void ordenarLasPromociones() {
		Arrays.sort(this.promociones, new ComparadorDeOfertables());
	}

	public void ordenarLasAtracciones() {
		Arrays.sort(this.atracciones, new ComparadorDeOfertables());
	}
}
