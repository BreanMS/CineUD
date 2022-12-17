package utils.BaseDatos;

import model.Cliente;
import utils.BaseDatos.Query.QueryDelete;
import utils.BaseDatos.Query.QuerySelect;

import java.util.ArrayList;

public class testBaseDatos {

    public static void main(String[] args) throws RHException {

        ArrayList<Cliente> c = new ArrayList<Cliente>();

        QueryDelete qd = new QueryDelete();
        QuerySelect qs = new QuerySelect();
        c = qs.SelectCliente();
        System.out.println(c.get(0).getCedula());

    }

}
