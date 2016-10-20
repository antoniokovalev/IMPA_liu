import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 2016-04-24
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class Node {
    private ArrayList <Edge> incidentEdges;
    private ArrayList <Integer> adjacentNodes;
    //private T t;
    private int ID;
    private boolean nodeVisited;
    private Node parent;
    private int parentID;
    private int distance;


    //public void set(T t){this.t = t;}
    //public T get(){return this.t;}
    public void addAdjacentNode(int ID){
        this.adjacentNodes.add(ID);
       }
    public int numberOfEdges () {return this.adjacentNodes.size();}
    public int getAdjacentNode(int index) {return adjacentNodes.get(index);}
    public void printAdjacentNodes () {
        for (int i = 0; i < adjacentNodes.size(); i++) {
            System.out.print(adjacentNodes.get(i) + " ");
        }
        System.out.println("");
    }


    public void setDistance (int dist) {this.distance = dist;}
    public int getDistance () {return this.distance;}
    public void setParent (Node parent){
        this.parent=parent;
    }
    public void setParentID (int ID){this.parentID = ID;}
    public int getParentID () {return this.parentID;}
    public ArrayList<Edge> getIncidentEdges(){return this.incidentEdges;}
    public Node (int ID){ this.ID = ID;
        this.nodeVisited=false;
        this.distance = Integer.MAX_VALUE;
        this.adjacentNodes = new ArrayList<Integer>();
        this.parentID = -1;
    }

    public  int getNodeID(){return this.ID;}
    public void setVisited(){this.nodeVisited = true;}
    public boolean IsVisited() {return this.nodeVisited;}
    public void addIncidentEdge(Edge e){
        this.incidentEdges.add(e);
    }
    public boolean EdgesEmpty(){
        if (this.incidentEdges.size() == 0){
            return true;
        }
        else {return false;}
    }

    public boolean isIncident(Edge e){
        return incidentEdges.contains(e); // check if it really works.
    }
}
