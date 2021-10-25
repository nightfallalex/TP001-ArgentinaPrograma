package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;

public class AtraccionDAO {
	public int update(Atraccion atraccion) throws SQLException {
		String sql = "UPDATE atracciones SET cupoDisponible = ? WHERE id = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, (atraccion.getCupo() - 1));
		statement.setInt(2, atraccion.getId());
		int rows = statement.executeUpdate();

		return rows;
	}

	public List<Atraccion> findAll() throws SQLException {
		String sql = "SELECT * FROM atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();
		
		Atraccion atraccion = null;

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		
		while (resultados.next()) {
			atraccion = new Atraccion(resultados.getInt("id"),resultados.getString("nombre"),resultados.getInt("costo"),resultados.getInt("tiempo"),resultados.getInt("cupoDisponible"),resultados.getInt("tipoAtraccion"));
			atracciones.add(atraccion);
		}

		return atracciones;
	}
	
}
