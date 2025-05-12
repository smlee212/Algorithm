import java.util.*;

// 2:05

class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        
        // [0] : 요청시간, [1] : 소요시간, [2] : 작업번호 
        // 소요시간, 요청시간, 작업번호 순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> {
            if(o1[1]==o2[1]) {
                if(o1[0]==o2[0]) {
                    return o1[2]-o2[2];
                }
                return o1[0]-o2[0];
            }
            return o1[1]-o2[1];
        });
        
        // pq에 쓰일 작업의 정보를 따로 저장
        // 요청 시간, 소요시간, 작업번호 순
        PriorityQueue<int[]> jobInfo = new PriorityQueue<>((o1,o2) -> {
           if(o1[0]==o2[0]) {
               if(o1[1]==o2[1]) {
                   return o1[2]-o2[2];
               }
               return o1[1]-o2[1];
           } 
            return o1[0]-o2[0];
        });
        
        for(int i=0;i<n;i++) {
            int[] job = jobs[i];
            jobInfo.add(new int[]{job[0], job[1], i});
        }
        
        // 시간 흐름에 따라 작업 처리
        int returnTime = 0;
        int cnt = 0;
        int time = 0;
        while(!jobInfo.isEmpty() || !pq.isEmpty()) {
            if(!pq.isEmpty()) {
                int[] nowJob = pq.poll();
                time += nowJob[1];
                returnTime += time - nowJob[0];
                cnt++;
            }        
            
            if(jobInfo.isEmpty()) continue;
            if(pq.isEmpty() && time < jobInfo.peek()[0]) {
                time = jobInfo.peek()[0];
            }
            else {
                while(!jobInfo.isEmpty() && time >= jobInfo.peek()[0]) {
                    pq.add(jobInfo.poll());
                }
            }
        }
        
        
        return returnTime / cnt;
    }
}