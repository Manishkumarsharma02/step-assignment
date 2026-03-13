import java.util.*;

public class Autocomplete {

    static HashMap<String,Integer> map = new HashMap<>();

    static void addQuery(String q){
        map.put(q, map.getOrDefault(q,0)+1);
    }

    static void search(String prefix){

        List<String> list = new ArrayList<>();

        for(String q : map.keySet()){
            if(q.startsWith(prefix))
                list.add(q);
        }

        list.sort((a,b)->map.get(b)-map.get(a));

        for(int i=0;i<Math.min(3,list.size());i++)
            System.out.println(list.get(i)+" "+map.get(list.get(i)));
    }

    public static void main(String[] args){

        addQuery("java tutorial");
        addQuery("java tutorial");
        addQuery("java download");
        addQuery("javascript guide");

        search("jav");
    }
}