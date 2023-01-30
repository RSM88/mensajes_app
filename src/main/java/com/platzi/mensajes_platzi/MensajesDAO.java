package com.platzi.mensajes_platzi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MensajesDAO {
	
	public static void CrearMensajeDB(Mensajes mensaje) {
		
		Conexion db_connect = new Conexion();
		
		try(Connection conexion = db_connect.get_connection()){
			
			PreparedStatement ps = null;
			try {
				String query="INSERT INTO mensajes (mensaje, auto_mensaje) VALUES (?,? )";
				ps=conexion.prepareStatement(query);
				ps.setString(1, mensaje.getMensaje());
				ps.setString(2, mensaje.getAutor_mensaje());
				ps.executeUpdate();
				System.out.println("mensaje creado");
				
			}catch(SQLException ex) {
				System.out.println(ex);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void leerMensajesDB() {
		
		Conexion db_connect = new Conexion();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		
		
		try(Connection conexion = db_connect.get_connection()){
			
			String query = "SELECT * FROM mensajes";
			ps = conexion.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt("id_mensaje"));
				System.out.println("Mensaje: " + rs.getString("mensaje"));
				System.out.println("Autor: " + rs.getString("auto_mensaje"));
				System.out.println("Fecha: " + rs.getString("fecha_mensaje"));
				System.out.println("");
			}
			
			
		}catch(SQLException ex) {
			System.out.println("No se pudieron recuperar los mensajes");
			System.out.println(ex);
		}
	}
	
	public static void borrarMensajeDB(int id_mensaje) {
		
		Conexion db_connect = new Conexion();
		
		try(Connection conexion = db_connect.get_connection()){
			
			PreparedStatement ps = null;
			
			try {
				String query = "DELETE FROM mensajes WHERE id_mensaje = ?";
				ps = conexion.prepareStatement(query);
				ps.setInt(1, id_mensaje);
				ps.executeUpdate();
				System.out.println("el mensaje ha sido borrado");
				
			}catch(SQLException e) {
				System.out.println(e);
				System.out.println("No se pudieron recuperar los mensajes");
			}

		}catch(SQLException ex) {
			System.out.println("No se pudieron recuperar los mensajes");
			System.out.println(ex);
		}
	}
	
	public static void actualizarMensajeDB(Mensajes mensajes) {
		Conexion db_connect = new Conexion();
		
		try(Connection conexion = db_connect.get_connection()){
			PreparedStatement ps = null;
			
			try{
				String query = "UPDATE mensajes SET mensaje = ? WHERE id_mensaje = ?";
				ps = conexion.prepareStatement(query);
				ps.setString(1, mensajes.getMensaje());
				ps.setInt(2, mensajes.getId_mensaje());
				ps.executeUpdate();
				System.out.println("EL mensaje se actualizo correctamente");
				
			}catch(SQLException ex) {
				System.out.println(ex);
				System.out.println("No se pudo actualizar el mensaje");
			}
			
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}

}
