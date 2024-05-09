import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();

        for(int[] command : commands) {
            // 배열 분리
            int[] splitArray = Arrays.copyOfRange(array, command[0]-1, command[1]);
            
            // 정렬
            Arrays.sort(splitArray);
            
            answer.add(splitArray[command[2]-1]);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}