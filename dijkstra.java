//Practice for Computer Graph Theory
//Author: Tatsunori Nagashima
import java.util.*;
public class dijkstra{
    static int [] track;
    public static class Edge{
        private int from;
        private int weight;
        private int to;
        public Edge(int from, int weight, int to){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        int  getFrom(){
            return this.from;
        }
        int getTo(){
            return this.to;
        }
        int getWeight(){
            return this.weight;
        }
        public String toString(){
            return "Edge, From: "+this.from+" To: "+this.to+" Weight: "+this.weight;
        }
    }
    public static List<Integer> dijkstraPath(Map<Integer, List<Edge>> m,int start){
        LinkedList<Integer> lists = new LinkedList<Integer>();
        IndexPQueue ipq = new IndexPQueue();
        int prev;
        ipq.setUp(30);
        ipq.push(start,0);
        track[start]=0;
        ipq.toStr();
        while(ipq.getSize()!=0){
            int []arr = ipq.poll();//get key and value with minimum value
            prev = arr[0];
            Iterator<Edge> iterator = m.get(prev).iterator();
            while(iterator.hasNext()){
                Edge next = iterator.next();
                if(track[next.getTo()]>track[prev]+next.getWeight()){
                    track[next.getTo()]=track[prev]+next.getWeight();
                    ipq.push(next.getTo(),track[next.getTo()]);
                    ipq.toStr();
                }}
                ipq.toStr();
        }
        return lists;
        
    }
     public static void main(String [] args){
        Map<Integer, List<Edge>> graph = new HashMap<>();
        String [] airports = {"Haneda","San Francisco","Hong Kong","Rio de janeiro","Osaka","Nairobi","Washington D.C"};
        track = new int[airports.length];
        for (int i = 0; i < airports.length; i++) graph.put(i, new LinkedList<Edge>());
        for(int i = 0; i<airports.length;i++)track[i]=Integer.MAX_VALUE;
        graph.get(0).add(new Edge(0,5,1));
        graph.get(0).add(new Edge(0,3,5));
        graph.get(0).add(new Edge(0,4,2));
        graph.get(0).add(new Edge(0,6,4));
        graph.get(1).add(new Edge(1,3,2));
        graph.get(1).add(new Edge(1,4,6));
        graph.get(2).add(new Edge(2,1,3));
        graph.get(3).add(new Edge(3,3,4));
        graph.get(4).add(new Edge(4,1,5));
        graph.get(5).add(new Edge(5,3,6));
        graph.get(6).add(new Edge(6,2,5));
        graph.forEach((k,v) ->
        {
            Iterator<Edge> iterator = v.iterator();
            while(iterator.hasNext()){
            System.out.println(iterator.next());
            }
        });
        dijkstraPath(graph,0);
        for(int i = 0; i<track.length;i++){
            System.out.println(track[i]);
        }
    }
}
