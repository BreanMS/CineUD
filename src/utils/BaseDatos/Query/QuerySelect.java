package utils.BaseDatos.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;


import utils.BaseDatos.RHException;
import utils.BaseDatos.ServiceLocator;

public class QuerySelect {

    public QuerySelect() {
    }

    public ArrayList<Cliente> SelectCliente() throws RHException {

        ArrayList<Cliente> arrayCliente = new ArrayList<Cliente>();

        try {
            String strSQL = "SELECT * FROM cliente";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                while (rs.next()) {
                    Cliente obj = new Cliente(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getLong(4), rs.getString(5),
                            rs.getInt(6), rs.getString(7));
                    arrayCliente.add(obj);
                }
                prepStmt.close();
                return arrayCliente;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Contrato> SelectContrato() throws RHException {
        ArrayList<Contrato> arrayContrato = new ArrayList<Contrato>();

        try {
            String strSQL = "SELECT * FROM contrato";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                while (rs.next()) {
                    Contrato obj = new Contrato(rs.getInt(1), String.valueOf(rs.getDate(2)),
                            rs.getString(3), rs.getInt(4), rs.getInt(5),
                            rs.getInt(6));
                    arrayContrato.add(obj);
                }
                prepStmt.close();
                return arrayContrato;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Empleado> SelectEmpleado() throws RHException {
        ArrayList<Empleado> arrayEmpleado = new ArrayList<Empleado>();

        try {
            String strSQL = "SELECT * FROM empleado";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                while (rs.next()) {
                    Empleado obj = new Empleado(rs.getString(1), rs.getInt(2),
                            rs.getString(3), rs.getLong(4), rs.getString(5),
                            rs.getString(6));
                    arrayEmpleado.add(obj);
                }
                prepStmt.close();
                return arrayEmpleado;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Factura> SelectFactura() throws RHException {
        ArrayList<Factura> arrayFactura = new ArrayList<Factura>();

        try {
            String strSQL = "SELECT * FROM factura";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                while (rs.next()) {
                    Factura obj = new Factura(rs.getInt(1), String.valueOf(rs.getDate(2)),
                            rs.getInt(3), rs.getString(4));
                    arrayFactura.add(obj);
                }
                prepStmt.close();
                return arrayFactura;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Multiplex> SelectMultiplex() throws RHException {
        ArrayList<Multiplex> arrayMultiplex = new ArrayList<Multiplex>();

        try {
            String strSQL = "SELECT * FROM multiplex";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                //while (rs.next()) {
                    // Pide arrays...
                    //Multiplex obj = new Multiplex(rs.getString(1), rs.getString(2),
                    //        rs.getString(3), rs.getLong(4), rs.getString(5),
                    //        rs.getInt(6), rs.getString(7));
                    //arrayMultiplex.add(obj);
                //}
                prepStmt.close();
                return arrayMultiplex;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Pelicula> SelectPelicula() throws RHException {
        ArrayList<Pelicula> arrayPelicula = new ArrayList<Pelicula>();

        try {
            String strSQL = "SELECT * FROM pelicula";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                //while (rs.next()) {
                    //Pide un String[]
                //    Pelicula obj = new Pelicula(rs.getString(1), rs.getString(2),
                //            rs.getString(3), rs.getInt(4), rs.getString(5));
                //    arrayPelicula.add(obj);
                //}
                prepStmt.close();
                return arrayPelicula;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Sala> SelectSala() throws RHException {
        ArrayList<Sala> arraySala = new ArrayList<Sala>();

        try {
            String strSQL = "SELECT * FROM sala";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                // Pide objeto Pelicula
                //while (rs.next()) {
                //    Sala obj = new Sala(rs.getString(1), rs.getString(2));
                //    arraySala.add(obj);
                //}
                prepStmt.close();
                return arraySala;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Silla> SelectSilla() throws RHException {
        ArrayList<Silla> arraySilla = new ArrayList<Silla>();

        try {
            String strSQL = "SELECT * FROM silla";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                // Este simplemente me puede
                //while (rs.next()) {
                //    Silla obj = new Silla(rs.getString(1));
                //    arraySilla.add(obj);
                //}
                prepStmt.close();
                return arraySilla;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Snack> SelectSnack() throws RHException {
        ArrayList<Snack> arraySnack = new ArrayList<Snack>();

        try {
            String strSQL = "SELECT * FROM snack";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            if (rs == null){
                prepStmt.close();
                return null;
            }
            else {
                while (rs.next()) {
                    Snack obj = new Snack(String.valueOf(rs.getInt(1)), rs.getString(2),
                            rs.getInt(3), rs.getString(4), rs.getString(5));
                    arraySnack.add(obj);
                }
                prepStmt.close();
                return arraySnack;
            }
        } catch (SQLException e) {
            throw new RHException("QuerySelect", e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
}
