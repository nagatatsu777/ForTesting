//Practice of the graph theory
//Author: Tatsunori Nagashima
import java.util.*;
public class EulerianPath{
    //Edge class to describe edges in the graph
    static class Edge{
        private int from;
        private int to;
        public Edge(int from, int to){
           this.from = from;
           this.to = to;
        }
        int getFrom(){
            return this.from;
        }
        int getTo(){
            return this.to;
        }
        public String toString(){
            return "Edge, From: "+this.from+" To: "+this.to;
        }
        
    }
    //method to check if the graph is Eulerian path 
    static int isEulerian(Map<Integer,List<Edge>> m,int [][] track){
        int end = 0;
        int start = 0;
        int startNode = 1;
        m.forEach((k,v)->
        {
            Iterator<Edge> iterator = v.iterator();
            while(iterator.hasNext()){
                Edge temp = iterator.next();
                track[0][temp.getFrom()]++;
                track[1][temp.getTo()]++;
            }
        });
        for(int i = 0; i< track[0].length;i++){
            if(track[0][i]-track[1][i]==1){
                start++;
                startNode = i;
            }
            if(track[1][i]-track[0][i]==1){
                end++;
            }
        }
        if(start<=1&&end<=1){
            return startNode;
        }
        else{
            return -1;
        }

    }
        
        
    
     static void dfs(Map<Integer, List<Edge>> m, int [][]track, List<Integer> lists, int at) {
    while (track[0][at] != 0) {
      int next = m.get(at).get(--track[0][at]).getTo();
      dfs(m, track,lists, next);
    }
    lists.add(0,at);
  }

    static List<Integer> findEulerianPath(Map<Integer, List<Edge>> m, int [][]track, int start){
        LinkedList<Integer> lists = new LinkedList<Integer>();
        dfs(m, track, lists,start);
        return lists;
    }
    public static void main(String [] args){
        int [][] inOut = new int [2][6];
        int [][] inOut2 = new int[2][7];
        //graph setup
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i < 6; i++) graph.put(i, new LinkedList<Edge>());
        graph.get(1).add(new Edge(1,2));
        graph.get(2).add(new Edge(2,3));
        graph.get(3).add(new Edge(3,3));
        graph.get(2).add(new Edge(2,4));
        graph.get(3).add(new Edge(3,5));
        graph.get(5).add(new Edge(5,4));
        graph.get(4).add(new Edge(4,2));
        graph.forEach((k,v) ->
        {
            Iterator<Edge> iterator = v.iterator();
            while(iterator.hasNext()){
            System.out.println(iterator.next());
            }
        });
        int startNode = isEulerian(graph,inOut);
        if(startNode==-1){
            System.out.println("No Eulerian Path");

        
        }
        else{
            System.out.println("Eulerian starting node: "+startNode);
        }
        //second test case
        Map<Integer, List<Edge>> graph2 = new HashMap<>();
        for (int i = 1; i < 7; i++) graph2.put(i, new LinkedList<Edge>());
        graph2.get(1).add(new Edge(1,3));
        graph2.get(1).add(new Edge(1,2));
        graph2.get(2).add(new Edge(2,2));
        graph2.get(2).add(new Edge(2,4));
        graph2.get(2).add(new Edge(2,4));
        graph2.get(3).add(new Edge(3,2));
        graph2.get(3).add(new Edge(3,1));
        graph2.get(3).add(new Edge(3,5));
        graph2.get(4).add(new Edge(4,3));
        graph2.get(4).add(new Edge(4,6));
        graph2.get(5).add(new Edge(5,6));
        graph2.get(6).add(new Edge(6,3));
        int startNode2 = isEulerian(graph2,inOut2);
        if(startNode2==-1){
            System.out.println("No Eulerian Path");


        }
        else{
            System.out.println("Eulerian startingi node: "+startNode2);
        }
       /* for(int i = 0; i< inOut.length;i++){
            for(int j = 0; j< inOut[i].length;j++){
                System.out.println(inOut[i][j]);
            }
        }*/
        //Show results
        System.out.println("Test case 1:");
        List<Integer> eulerianPath = findEulerianPath(graph, inOut, startNode);
        Iterator<Integer> itr = eulerianPath.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next()+" -> ");
        }
        System.out.println("End\nTest case 2:");
        List<Integer> eulerianPath2 = findEulerianPath(graph2, inOut2, startNode2);
        Iterator<Integer> itr2 = eulerianPath2.iterator();
        while(itr2.hasNext()){
            System.out.print(itr2.next()+" -> ");
        }
        System.out.println("End");
        /*Iterator<Edge> iterator = graph.iterator();
        while(iterator.hasNext()){
            
            System.out.println(iterator.next());
        }*/
    }
}
