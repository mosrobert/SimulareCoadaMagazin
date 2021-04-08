package ro.tuc.tp.tema2.DataModels;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.*;

public class SimulationManager implements Runnable{
    public int timeLimit=60;
    public int maxProcessingTime=30;
    public int minProcessingTime=2;
    public int numarDeCozi=2;
    public int numarDeClienti=4;
    public int maxSos=30;
    public int minSos=2;
    public SelectionPolicy selectionPolicy=SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private List<Client> clientiGenerati=new ArrayList<>();

    public SimulationManager()
    {
        Scheduler sc=new Scheduler(numarDeCozi, numarDeClienti);
        sc.changeStrategy(selectionPolicy);
        generateNRandomClienti();
        this.scheduler=sc;

    }

    private void generateNRandomClienti()
    {
        Random rand= new Random();
        for(int i=0;i<numarDeClienti;i++)
        {
            int timpSos=(int) (Math.random()*(maxSos-minSos+1)+minSos);
            int procT=(int) (Math.random()*(maxProcessingTime-minProcessingTime+1)+minProcessingTime);
            Client c=new Client(i+1,timpSos,procT);
            clientiGenerati.add(c);
        }
        Collections.sort(clientiGenerati);
    }
    public void run()
    {
        int currentTime=0;
        while(currentTime<timeLimit)
        {
            System.out.println("-----Time: "+currentTime+"-----");
            ArrayList<Client> clientiStersi=new ArrayList<Client>();
            for (Client c: clientiGenerati)
            {
                if(c.getTimpSosire()==currentTime)
                {
                    scheduler.dispatchClient(c);
                    clientiStersi.add(c);
                }
            }
            for (Client c: clientiStersi){
                clientiGenerati.remove(c);
            }
            System.out.print("Clienti: ");
            for (Client c: clientiGenerati)
            {
                System.out.print(c.toString());
            }
            System.out.println();
            for (Coada c: scheduler.getCozi())
            {
                System.out.println(c.toString());
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){
        SimulationManager gen=new SimulationManager();
        Thread t=new Thread(gen);
        t.start();

    }
}
