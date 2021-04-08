package ro.tuc.tp.tema2.DataModels;

import java.util.List;

public class StrategieCoada implements Strategy {

    @Override
    public void addClient(List<Coada> cozi, Client c) {
        Coada cod = cozi.get(0);
        for (Coada cod2 : cozi) {
            if (cod2.getClient().size() < cod.getClient().size())
                cod = cod2;
        }
        cod.addClient(c);
    }
}
