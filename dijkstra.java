//Practice for Computer Graph Theory
//Author: Tatsunori Nagashima
import java.util.*;
public class dijkstra{
    static int [] track;
    static String [] airports = {"Haneda","San Francisco","Hong Kong","Rio de janeiro","Osaka","Nairobi","Washington D.C"};
    static int [] [] listArr;
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
            return "Edge, From: "+airports[this.from]+" To "+airports[this.to]+" Weight: "+this.weight;
        }
    }
    public static List<Integer> dijkstraPath(Map<Integer, List<Edge>> m,int start){
        LinkedList<Integer> lists = new LinkedList<Integer>();
        IndexPQueue ipq = new IndexPQueue();
        int prev;
        ipq.setUp(30);//create index priority queue
        ipq.push(start,0);//push the starting point
        track[start]=0;//record the distance(weight)
        while(ipq.getSize()!=0){
            int []arr = ipq.poll();//get key and value with minimum value
            prev = arr[0];
            Iterator<Edge> iterator = m.get(prev).iterator();
            while(iterator.hasNext()){
                Edge next = iterator.next();
            if(track[next.getTo()]>(track[prev]+next.getWeight())){
                    track[next.getTo()]=track[prev]+next.getWeight();       
                    ipq.push(next.getTo(),track[next.getTo()]);
                    listArr[next.getTo()][listArr.length]=0;//reset the size
                    for(int i=0;i<listArr[prev][listArr.length];i++){
                        listArr[next.getTo()][i] = listArr[prev][i];
                        listArr[next.getTo()][listArr.length]++;
                    }
                    listArr[next.getTo()][listArr[next.getTo()][listArr.length]]= prev;
                    listArr[next.getTo()][listArr.length]++;
                }}
        }
        return lists;
        
    }
     public static void main(String [] args){
        Map<Integer, List<Edge>> graph = new HashMap<>();
        track = new int[airports.length];
        int start = 0;
        listArr = new int[airports.length][];//keep tracks of what node was used to reach the current node
        for (int i = 0; i < airports.length; i++) graph.put(i, new LinkedList<Edge>());
        for(int i = 0; i<airports.length;i++)track[i]=Integer.MAX_VALUE;
        for(int i=0; i<airports.length;i++){
            listArr[i] = new int [airports.length+1];//the last digit will tell how many numbers are stored
            for(int j=0;j<airports.length;j++){
                listArr[i][j]=-1;

            }
            listArr[i][airports.length]=0;
        }
        graph.get(0).add(new Edge(0,5,1));
        graph.get(0).add(new Edge(0,6,5));
        graph.get(0).add(new Edge(0,4,2));
        graph.get(0).add(new Edge(0,4,4));
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
        dijkstraPath(graph,start);
        System.out.println("Start Point: "+ airports[start]+"\nShortest Distances: \n");
        for(int i = 0; i<track.length;i++){
            System.out.println(airports[i]+": "+track[i]);
            if(listArr[i][listArr.length]>0){
                System.out.print("Route: ");
                for(int j = 0; j<listArr[i][listArr.length];j++){
                    System.out.print(" "+airports[listArr[i][j]]+" to");
                }
                System.out.println(" "+airports[i]);
            }
            
        }
    }
}
