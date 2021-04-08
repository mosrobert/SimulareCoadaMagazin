package ro.tuc.tp.tema2.DataModels;

import java.util.List;

public class StrategieTimp implements Strategy {

    @Override
    public void addClient(List<Coada> cozi, Client c) {
        Coada cod = cozi.get(0);
        for (Coada cod2 : cozi) {
            if (cod2.getTimpAsteptare() < cod.getTimpAsteptare())
                cod = cod2;
        }
        cod.addClient(c);
    }
}
