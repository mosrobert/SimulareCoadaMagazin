package ro.tuc.tp.tema2.DataModels;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Coada> cozi;
    private int maxCozi;
    private int maxClientiPeCoada;
    private Strategy strategy;

    public Scheduler(int maxCozi, int maxClientiPeCoada)
    {
        cozi=new ArrayList<Coada>();
        for(int i=0;i<maxCozi;i++)
        {
            Coada c=new Coada(i+1);
            cozi.add(c);
            Thread t=new Thread(c);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy)
    {
        if(policy==SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy =new StrategieCoada();
        }
        if(policy==SelectionPolicy.SHORTEST_TIME){
            strategy =new StrategieTimp();
        }
    }
    public void dispatchClient(Client newClient)
    {
        strategy.addClient(cozi, newClient);
    }
    public List<Coada> getCozi()
    {
        return cozi;
    }
}
