import java.util.*;

public class Two_Sum {

    static void findTwoSum(int[] arr,int target){

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<arr.length;i++){

            int comp = target-arr[i];

            if(map.containsKey(comp)){
                System.out.println(comp+" + "+arr[i]);
                return;
            }

            map.put(arr[i],i);
        }
    }

    public static void main(String[] args){

        int arr[]={500,300,200};

        findTwoSum(arr,500);
    }
}