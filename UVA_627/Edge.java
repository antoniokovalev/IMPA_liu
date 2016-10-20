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
