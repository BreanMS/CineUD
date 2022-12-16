package utils.BaseDatos.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;
import model.Contrato;
import model.Empleado;
import model.Factura;
import utils.BaseDatos.RHException;
import utils.BaseDatos.ServiceLocator;

public class QueryInsert {

    public QueryInsert() {

    }

    public void insertEmpleado(Empleado obj) throws RHException {
        try {
            String strSQL = "INSERT INTO empleado VALUES(?,?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, obj.getCedula());
            prepStmt.setInt(2, obj.getCodigo());
            prepStmt.setString(3, obj.getNombre());
            prepStmt.setLong(4, obj.getTelefono());
            prepStmt.setString(5, obj.getContrasena());
            prepStmt.setString(6, obj.getEmail() );

            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            ServiceLocator.getInstance().rollback();
            throw new RHException( "QueryInsert", "No pudo crear el empleado"+ e.getMessage());
        }  finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void insertCliente(Cliente obj) throws RHException {
        try {
            String strSQL = "INSERT INTO cliente VALUES(?,?,?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, obj.getCedula());
            prepStmt.setString(2, obj.getNombre());
            prepStmt.setString(3, obj.getApellido());
            prepStmt.setLong(4, obj.getTelefono());
            prepStmt.setString(5, obj.getContrasena());
            prepStmt.setInt(6, obj.getPuntos());
            prepStmt.setString(7, obj.getEmail() );

            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            ServiceLocator.getInstance().rollback();
            throw new RHException( "QueryInsert", "No pudo crear el cliente"+ e.getMessage());
        }  finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void insertContrato(Contrato obj) throws RHException {
        try {
            String strSQL = "INSERT INTO contratp VALUES(?,?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, obj.getId());
            prepStmt.setDate(2, java.sql.Date.valueOf(obj.getFechIn()));
            prepStmt.setString(3, obj.getCargo());
            prepStmt.setInt(4, obj.getSalario());
            prepStmt.setInt(5, obj.getMultiplex());
            prepStmt.setInt(6, obj.getCliente());

            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            ServiceLocator.getInstance().rollback();
            throw new RHException( "QueryInsert", "No pudo crear el contrato"+ e.getMessage());
        }  finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void insertFactura(Factura obj) throws RHException {
        try {
            String strSQL = "INSERT INTO factura VALUES(?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, obj.getId());
            prepStmt.setDate(2, java.sql.Date.valueOf(obj.getFecha()));
            prepStmt.setInt(3, obj.getMonto_final());
            prepStmt.setString(4, obj.getCliente());

            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            ServiceLocator.getInstance().rollback();
            throw new RHException( "QueryInsert", "No pudo crear la factura"+ e.getMessage());
        }  finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
}
