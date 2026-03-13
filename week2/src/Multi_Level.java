import java.util.*;

public class Multi_Level {

    static LinkedHashMap<String,String> L1 =
            new LinkedHashMap<>(5,0.75f,true);

    static HashMap<String,String> L2 = new HashMap<>();

    static HashMap<String,String> DB = new HashMap<>();

    static String getVideo(String id){

        if(L1.containsKey(id)){
            System.out.println("L1 HIT");
            return L1.get(id);
        }

        if(L2.containsKey(id)){
            System.out.println("L2 HIT");
            String v=L2.get(id);
            L1.put(id,v);
            return v;
        }

        System.out.println("Database HIT");
        String v=DB.get(id);
        L2.put(id,v);
        return v;
    }

    public static void main(String[] args){

        DB.put("video1","data1");

        getVideo("video1");
        getVideo("video1");
    }
}
