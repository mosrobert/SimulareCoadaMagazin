package ro.tuc.tp.tema2.Logic;

import ro.tuc.tp.tema2.DataModels.Client;
import ro.tuc.tp.tema2.DataModels.Coada;
import ro.tuc.tp.tema2.Logic.Strategy;

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
