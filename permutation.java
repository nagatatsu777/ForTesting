import java.util.*;
public class permutation{
    static int maxSize = 0;
    public static List<List<Integer>> perm(List<Integer> set){
        maxSize = set.size();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<Integer> combination = new LinkedList<Integer>();
        recursivePerm(res,set,combination);
        return res;
    }
    public static void  recursivePerm(List<List<Integer>> res, List<Integer>set,LinkedList<Integer> combination){
        if(maxSize==combination.size()){
            List<Integer> temp = new LinkedList<Integer>();
            for(Integer i:combination){
                temp.add(i);
            }
            res.add(temp);
            return;
        }
        for(int i = 0; i < set.size(); i++){
            Integer s = set.remove(0);
            combination.add(s);
            recursivePerm(res,set,combination);
            set.add(s);
            combination.pollLast();
        }
        return;
    }
    public static void printSet(List<List<Integer>> res){
        for(List<Integer> lis: res){
            
            for(Integer i: lis){
                System.out.print(i);
            }
            System.out.println("");
        }
    }
    public static void main(String [] args){
        List<Integer> test1 = new ArrayList<Integer>();
        test1.add(1);
        test1.add(2);
        test1.add(3);
        test1.add(4);
        test1.add(5);
        List<List<Integer>> res = perm(test1);
        printSet(res);
        System.out.println(res.size());

    }
}
