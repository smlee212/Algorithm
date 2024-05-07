import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        int i=0;
        int size=progresses.length;
        while(true) {

            // i번째 진행상황 확인
            int cnt = 0;
            for(;i<size;i++) {
                if(progresses[i] >= 100) {
                    cnt++;
                }
                else{
                    break;
                }
            }

            if(cnt > 0) {
                answer.add(cnt);
            }

            if(i >= size) {
                break;
            }

            // 하루 진행
            for(int j=i;j<size;j++) {
                progresses[j] += speeds[j];
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}