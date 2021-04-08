package ro.tuc.tp.tema2.DataModels;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada implements Runnable {
    private BlockingQueue<Client> clienti;
    private AtomicInteger timpAsteptare;
    private int id;

    public Coada(int id) {
        clienti = new LinkedBlockingQueue<>();
        timpAsteptare = new AtomicInteger(0);
        this.id=id;
    }

    public int getTimpAsteptare() {
        return timpAsteptare.get();
    }

    public void addClient(Client newClient) {
        clienti.add(newClient);

        timpAsteptare.addAndGet(newClient.getTimpServire());

    }

    public String toString()
    {
        String s="";
        s=s+"Coada "+id;
        for (Client c: clienti) {
            s=s+c.toString()+"; ";
        }

        return s;
    }

    public void run() {
        while (true) {
            if (clienti.size() > 0) {
                Client newClient = clienti.peek();
                int timp=newClient.getTimpServire();
                for (int i = 1; i <= timp; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timpAsteptare.decrementAndGet();
                    newClient.setTimpServire(newClient.getTimpServire()-1);

                }
                try {
                    clienti.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public ArrayList<Client> getClient() {
        ArrayList<Client> clienti2 = new ArrayList<>();
        for (Client c : clienti) {
            clienti2.add(c);

        }
        return clienti2;
    }
}
