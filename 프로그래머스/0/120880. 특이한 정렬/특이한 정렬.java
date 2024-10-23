import java.util.*;

class Solution {
    public int[] solution(int[] numlist, int n) {
        int[][] arr = new int[numlist.length][2];
        for(int i=0;i<numlist.length;i++) {
            arr[i][0] = numlist[i];
            arr[i][1] = numlist[i] - n;
        }
        Arrays.sort(arr, (o1,o2) -> {            
           if(Math.abs(o1[1])==Math.abs(o2[1])) {
               return o2[0] - o1[0];
           } 
           else {
                return Math.abs(o1[1]) - Math.abs(o2[1]);
           }
        });
        
        for(int i=0;i<numlist.length;i++) {
            numlist[i] = arr[i][0];
        }
        
        return numlist;
    }
}