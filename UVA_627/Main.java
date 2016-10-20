import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 2016-04-24
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main (String args[]) throws Exception{
        int n;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String a;
        while((a = in.readLine()) != null){
            n = Integer.parseInt(a);
            ArrayList<Node> nodes = new ArrayList<Node>();
            for (int i = 0; i < n; i++) {
                String line;
                String[] parts = in.readLine().split("[-]");
                if (parts.length == 2) {
                    line = parts[1];
                    String[] neighs = line.split("[,]");
                    Node node = new Node(Integer.parseInt(parts[0]) - 1);
                    nodes.add(node);
                    for (int j = 0; j < neighs.length; j++) {
                        int w = Integer.parseInt(neighs[j]) - 1;
                        node.addAdjacentNode(w);
                    }
                }
                else{
                   Node node = new Node(Integer.parseInt(parts[0]) - 1);
                    nodes.add(node);
                }
            }
            //Just to print
          /*  for (int i = 0; i < nodes.size(); i++) {
                System.out.println("Node ID: " + nodes.get(i).getNodeID());
                System.out.println("Neighbours: ");
                nodes.get(i).printAdjacentNodes();
            }*/
            String b = in.readLine();
            int m = Integer.parseInt(b);
            System.out.println("-----");
            for (int i =0;i<m;i++) {
                String[] c = in.readLine().split("[ ]");
                int source = Integer.parseInt(c[0]) - 1;
                int end = Integer.parseInt(c[1]) - 1;
                int[] parents = bfs(source, end, nodes);
                boolean path = true;
                int current_parent = end;
                if (parents[current_parent] < 0) {
                    System.out.println("connection impossible");
                } else if (parents[source] == source) {
                    System.out.println(source+1);
                } else {
                    int[] answer = new int[n];
                    int counter = 0;
                    while (path) {
                        answer[counter] = current_parent +1;
                        counter++;
                        if (parents[current_parent] >= 0) {
                            current_parent = parents[current_parent];
                        } else {
                            path = false;
                        }
                    }
                    for (int j = answer.length-1; j >= 0; j--) {
                        if (answer[j] > 0 && j != 0) {
                            System.out.print(answer[j]+ " ");
                        }
                        else if (answer[j] > 0 || j==0) {
                            System.out.print(answer[j]);
                        }
                    }
                    System.out.println();
                }
            }
        }
        System.exit(0);
    }

    public static int[] bfs (int start, int end, ArrayList<Node> nodes ){
        int [] distance_arr = new int[nodes.size()];
        Arrays.fill(distance_arr, Integer.MAX_VALUE);
        int [] parent_arr = new int[nodes.size()];
        Arrays.fill(parent_arr,-5);
        nodes.get(start).setDistance(0);    //Dubbelkolla! Inte säker att t.ex. nod 2 har id 2, borde dock vara så eftersom de läggs till i ordning i detta problem...
        distance_arr[start] = 0;
        if (start == end)
        {
            parent_arr[start] = start;
            return parent_arr;
        }

        else if (nodes.get(start).numberOfEdges()==0) {
            parent_arr[start] = -3;
            return parent_arr;
        }
        // BFS algorithm
        Queue<Node> Q = new LinkedList<Node>();
        Q.offer(nodes.get(start));
        while(!Q.isEmpty()){
            Node current = Q.poll();
            for (int i=0; i < current.numberOfEdges();i++){
                if (distance_arr[current. getAdjacentNode(i)]==Integer.MAX_VALUE) {
                    distance_arr[current.getAdjacentNode(i)] += 1;
                    parent_arr[current.getAdjacentNode(i)] = current.getNodeID();
                    Q.offer(nodes.get(current.getAdjacentNode(i)));
                }
        }
        }
        return parent_arr;
    }

    /**
     * Created with IntelliJ IDEA.
     * User: Anton
     * Date: 2016-04-24
     * Time: 13:11
     * To change this template use File | Settings | File Templates.
     */
    public static class Node {
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
            this.adjacentNodes = new ArrayList<Integer>(); // Kan be switched to incidentEdges...
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




    /**
     * Created with IntelliJ IDEA.
     * User: Anton
     * Date: 2016-04-24
     * Time: 13:11
     * To change this template use File | Settings | File Templates.
     */
    public class Edge<T> {
        private T vertex1;
        private T vertex2;
        private boolean explored;
        // Optional: add cross edge property and discovery edge property.

        public boolean isExplored (){
            return this.explored;
        }

        public void setExplored(){this.explored = true;} //Double check if it actually gets set.

        public T getVertex1(){return this.vertex1;}
        public T getVertex2(){return this.vertex2;}

        public void setVertex1(T vertex){this.vertex1 = vertex;}
        public void setVertex2(T vertex){this.vertex2 = vertex;}

        public T getOppositeVertex(T vertex){
            if (vertex == vertex1){
                return vertex2;
            }
            else if (vertex == vertex2){
                return vertex1;
            }
            else{
                System.out.println("This edge is not adjacent to this vertex!");
                return vertex;
            }
        }
    }

}
