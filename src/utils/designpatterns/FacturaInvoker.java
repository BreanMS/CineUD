package utils.designpatterns;

import java.util.ArrayList;

public class FacturaInvoker {

    private ArrayList<FacturaCommand> facturaCommands = new ArrayList<>();

    public void recibirPedidos (FacturaCommand facturaCommand){
        this.facturaCommands.add(facturaCommand);
    }

    public void calcularPedidos () {
        facturaCommands.forEach(p -> p.execute());
        facturaCommands.clear();
    }

}

