package ro.tuc.tp.tema2.DataModels;

public class Client implements Comparable {
    private int timpSosire;
    private int id;
    private int timpServire;
    public Client(int id, int timpSosire, int timpServire)
    {
        this.id=id;
        this.timpSosire=timpSosire;
        this.timpServire=timpServire;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public void setTimpSosire(int timpSosire){
        this.timpSosire=timpSosire;
    }
    public int getTimpSosire()
    {
        return timpSosire;
    }
    public void setTimpServire(int timpServire){
        this.timpServire=timpServire;
    }
    public int getTimpServire()
    {
        return timpServire;
    }

    public String toString()
    {
        return "("+id+","+timpSosire+","+timpServire+")";
    }

    @Override
    public int compareTo(Object o) {
        return this.timpSosire-((Client)o).timpSosire;

    }
}
