package utils.BaseDatos.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.BaseDatos.RHException;
import utils.BaseDatos.ServiceLocator;

public class QueryDelete {

    public QueryDelete() throws RHException {

    }

    public void deleteSnack(int id) throws RHException {
        try {
            String strSQL = "DELETE FROM snack WHERE id_sna = ?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            ServiceLocator.getInstance().rollback();
            throw new RHException("QueryDelete", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void deleteEmpleado(int id) throws RHException {
        try {
            String strSQL = "DELETE FROM empleado WHERE cedula_emp = ?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            ServiceLocator.getInstance().rollback();
            throw new RHException("QueryDelete", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void deleteCliente(int id) throws RHException {
        try {
            String strSQL = "DELETE FROM cliente WHERE cedula_cli = ?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            ServiceLocator.getInstance().rollback();
            throw new RHException("QueryDelete", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
}
