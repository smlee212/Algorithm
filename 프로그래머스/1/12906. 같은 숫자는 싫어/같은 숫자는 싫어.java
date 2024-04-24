import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        int i=1;
        int leftVal = arr[0];
        answer.add(leftVal);
        for(;i<arr.length;i++) {
            int currVal = arr[i];
            if(leftVal == currVal) continue;
            leftVal = currVal;
            answer.add(leftVal);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}