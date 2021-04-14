package ro.tuc.tp.tema2.Logic;

import ro.tuc.tp.tema2.DataModels.Client;
import ro.tuc.tp.tema2.DataModels.Coada;
import ro.tuc.tp.tema2.DataModels.Scheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.*;

public class SimulationManager implements Runnable{
    public int timeLimit=20;
    public int maxProcessingTime=5;
    public int minProcessingTime=3;
    public int numarDeCozi=2;
    public int numarDeClienti=10;
    public int maxSos=3;
    public int minSos=1;
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
        File fileOutput=new File("iesire.txt");
        FileWriter write= null;
        try {
            write = new FileWriter(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw=new PrintWriter(write);
        while(currentTime<timeLimit)
        {
            pw.println("-----Time: "+currentTime+"-----");
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
            pw.println("Clienti: ");
            System.out.print("Clienti: ");
            for (Client c: clientiGenerati)
            {
                pw.println(c.toString());
                System.out.print(c.toString());
            }
            pw.println();
            System.out.println();
            for (Coada c: scheduler.getCozi())
            {
                pw.println(c.toString());
                System.out.println(c.toString());
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pw.close();

    }

    public static void main(String[] args){
        SimulationManager gen=new SimulationManager();
        Thread t=new Thread(gen);
        t.start();

    }
}
