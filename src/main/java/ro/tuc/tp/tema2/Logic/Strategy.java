package ro.tuc.tp.tema2.Logic;

import ro.tuc.tp.tema2.DataModels.Client;
import ro.tuc.tp.tema2.DataModels.Coada;

import java.util.List;

public interface Strategy {
    public void addClient(List<Coada> cozi, Client c);
}
