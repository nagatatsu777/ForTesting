import java.util.HashMap;
import java.util.Map;
public class IndexPQueue{
    public static class IPQ{
        int size;
        int [][] ipq;//0:value, 1: position map, 2: inverse position map
        BiMap<String,Integer> map;
        int max;
        public IPQ(int size,BiMap<String,Integer> map){
            ipq = new int[3][];
            for(int i = 0; i<3;i++){
                ipq[i]= new int[size];
                for(int j= 0; j<size;j++){
                    ipq[i][j] = -1;
                }
            }
            this.map = map;
        }
        void insert(String key, int value ){
            map.put(key,map.size());
            ipq[0][map.size()-1]=value;
            ipq[1][map.size()-1]=map.size()-1;
            ipq[2][map.size()-1]=map.size()-1;
            ipq = recurse(map.size()-1,ipq);
            
        }
        void remove(String key){
           int temim =  ipq[2][map.size()-1];
           int tempm = ipq[1][map.get(key)];
           ipq[1][map.get(key)]=ipq[1][temim];
           ipq[1][temim]=-1;
           ipq[2][map.size()-1]=-1;
           ipq[2][tempm]=temim;
           ipq[0][map.get(key)]=-1;
           map.remove(key);
           this.toStr();
           recurse(tempm,ipq);

         }
        void  toStr(){
            for(int i = 0; i<map.size();i++){
                System.out.println("Position: "+i+" Name: "+map.getKey(ipq[2][i])+" Value: "+ipq[0][ipq[2][i]]);
            }
            for(int i = 0; i< 3; i++){
                for(int j = 0; j<map.size();j++){
                    System.out.print(ipq[i][j]+" ");
                }
                System.out.println("");
            }
        }

    }
    public static class BiMap<K,V> extends HashMap<K,V>{
        public Map<V,K> inversedMap = new HashMap<V,K>();
        int size = 0;
        public K getKey(V value){
            return inversedMap.get(value);
        }
        @Override 
        public int size(){
            return this.size;
        }
        @Override
        public boolean isEmpty(){
            return this.size()>0;
        }
        @Override
        public V remove(Object key){
            V val = super.remove(key);
            inversedMap.remove(val);
            size--;
            return val;
        }
        @Override
        public V get(Object key){
            return super.get(key);
        }
        @Override
        public V put(K key, V value){
            inversedMap.put(value,key);
            size++;
            return super.put(key,value);


        }
    }
    public static int[][] recurse(int node,int [][] oldd){
        if(node<=0){     
            return oldd;
        }        
       if(oldd[0][oldd[2][(node-1)/2]]>oldd[0][node]){
     
        oldd[2][node]=oldd[2][(node-1)/2];
        oldd[2][(node-1)/2]=node;
        oldd[1][node]=oldd[1][oldd[2][node]];
        oldd[1][oldd[2][node]]=node; 
        recurse((node-1)/2,oldd);
       }
       //For deletion
       //left
       if(oldd[0].length-1>=(node*2+1)&&oldd[2][node*2+1]!=-1&&oldd[0][oldd[2][node*2+1]]<oldd[0][node]){
        oldd[2][node]=oldd[2][node*2+1];
        oldd[2][node*2+1]=node;
        oldd[1][node]=oldd[1][oldd[2][node]];
        oldd[1][oldd[2][node*2+1]]=node;
        recurse(node*2+1,oldd);

       }
       else if(oldd[0].length-1>=(node*2+2)&&oldd[2][node*2+2]!=-1&&oldd[0][oldd[2][node*2+2]]<oldd[0][node]){
        oldd[2][node]=oldd[2][node*2+2];
        oldd[2][node*2+2]=node;
        oldd[1][node]=oldd[1][oldd[2][node]];
        oldd[1][oldd[2][node*2+2]]=node;
        recurse(node*2+2,oldd);
       }

       return oldd;

    }
    public static void  main (String [] args){
        final int EX_SIZE = 10;
        BiMap<String,Integer> map = new BiMap<>();
        IPQ exipq = new IPQ(EX_SIZE,map);
        exipq.insert("Ann",3);
        exipq.insert("Bella",4);
        exipq.insert("Kate",2);
        exipq.insert("George",7);
        exipq.insert("John",3);
        exipq.remove("John");
        exipq.toStr();
    }
}
