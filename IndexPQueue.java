import java.util.HashMap;
import java.util.Map;
public class IndexPQueue{
    public static IPQ exipq;
    public static class IPQ{
        int size;
        int [][] ipq;//0:value, 1: position map, 2: inverse position map
        BiMap<Integer,Integer> map;
        int max;
        public IPQ(int size,BiMap<Integer,Integer> map){
            ipq = new int[3][];
            for(int i = 0; i<3;i++){
                ipq[i]= new int[size];
                for(int j= 0; j<size;j++){
                    ipq[i][j] = -1;
                }
            }
            this.map = map;
        }
        void insert(Integer key, int value ){
            map.put(key,map.size());
            ipq[0][map.size()-1]=value;
            ipq[1][map.size()-1]=map.size()-1;
            if(ipq[2][0]==-1){
                ipq[2][0]= map.size()-1;
                ipq[1][map.size()-1]=0;
            }
            else{
                ipq[2][map.size()-1]=map.size()-1;
                ipq = recurse(map.size()-1,ipq);
            }
            size++;
        }
        void remove(Integer key){
           int temim =  ipq[2][map.size()-1];
           int tempm = ipq[1][map.get(key)];
           if(tempm ==  map.size()-1){
            ipq[1][map.get(key)]=-1;
            ipq[2][map.size()-1]=-1;
           }
           else{
            ipq[1][temim]=ipq[1][map.get(key)];
            ipq[1][map.get(key)]=-1;
            ipq[2][tempm]=temim;
            ipq[2][map.size()-1]=-1;
            recurse(tempm,ipq);
           }
           ipq[0][map.get(key)]=-1;
           map.remove(key);
           size--;

         }
         void change(Integer key, int value){
            ipq[0][map.get(key)]=value;
            recurse(ipq[1][map.get(key)],ipq);
         }
         int getMinVal(){
            return ipq[0][ipq[2][0]];
         }
         Integer  getMinKey(){
            return map.getKey(ipq[2][0]);
         }
         int get(Integer key){
            if(map.get(key)==null){
                return -1;
            }
            else{
                return ipq[0][map.get(key)];
            }
         }

        void toStr(){
            for(int i = 0; i<map.size();i++){
                if(ipq[2][i]!=-1){
                    System.out.println("Position: "+i+" Name: "+map.getKey(ipq[2][i])+" Value: "+ipq[0][ipq[2][i]]);
                }
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
        if(node<0){     
            return oldd;
        } 
//For deletion
       if(oldd[2][(node-1)/2]!=-1&&oldd[0][oldd[2][(node-1)/2]]>oldd[0][oldd[2][node]]){
        int temim = oldd[2][node];
        oldd[2][node]=oldd[2][(node-1)/2];
        oldd[2][(node-1)/2]=temim;
        oldd[1][oldd[2][(node-1)/2]]=oldd[1][oldd[2][node]];
        oldd[1][oldd[2][node]]=node;
        recurse((node-1)/2,oldd);
       }
       //left
       if(oldd[0].length-1>=(node*2+1)&&oldd[2][node*2+1]!=-1&&oldd[0][oldd[2][node*2+1]]<oldd[0][oldd[2][node]]){
        int temim = oldd[2][node];
        System.out.println("8");
        oldd[2][node]=oldd[2][node*2+1];
        oldd[2][node*2+1]=temim;
        oldd[1][oldd[2][node*2+1]]=oldd[1][oldd[2][node]];
        oldd[1][oldd[2][node]]= node;
        recurse(node*2+1,oldd);
       }
       //right
       if(oldd[0].length-1>=(node*2+2)&&oldd[2][node*2+2]!=-1&&oldd[0][oldd[2][node*2+2]]<oldd[0][oldd[2][node]]){
        int temim = oldd[2][node];
        System.out.println("a");
        oldd[2][node]=oldd[2][node*2+2];
        oldd[2][node*2+2]=temim;
        oldd[1][oldd[2][node*2+2]]=oldd[1][oldd[2][node]];
        oldd[1][oldd[2][node]]=node;
        recurse(node*2+2,oldd);
       }
       //check for the first node    
       return oldd;

    }
    static void setUp(int size){
        final int EX_SIZE = size;
        BiMap<Integer, Integer>map = new BiMap<>();
        exipq = new IPQ(EX_SIZE,map);
    }
    static IPQ getIPQ(){
        return exipq;
    }
    static void delete(Integer key){
        exipq.remove(key);
    } 
    static void push(Integer key, int val){
       
        if(exipq.get(key)!=-1){
            System.out.println("UA");
            exipq.change(key,val);
        }
        else{
            exipq.insert(key,val);
        }
    }
    static void update(Integer key, int val){
        exipq.change(key, val);
    }
    static void toStr(){
        exipq.toStr();
    }
    static int [] poll(){//returns 0:key, 1: value
        int [] arr =  new int[2];
        arr[0]=exipq.getMinKey();
        arr[1]=exipq.getMinVal();
        exipq.remove(arr[0]);
        return arr;
    }
    static int getSize(){
        return exipq.size;
    }
    public static void  main (String [] args){
        setUp(10);
        System.out.println(exipq.get(1));
        exipq.insert(0,0);
        exipq.remove(0);
        exipq.insert(1,5);
        exipq.insert(5,3);
        exipq.insert(2,4);
        exipq.insert(4,6);
        exipq.remove(5);
        exipq.toStr();
        exipq.insert(6,6);
        exipq.toStr();
        System.out.println("Minimum Key: "+exipq.getMinKey());
        System.out.println("Minimum Value: "+exipq.getMinVal());
  
    }
}
