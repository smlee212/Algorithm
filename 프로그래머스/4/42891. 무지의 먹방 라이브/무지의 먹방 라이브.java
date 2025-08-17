import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[1]-o2[1]);
        long sumTime = 0;
        for(int i=0;i<n;i++) {
            pq.add(new int[]{i+1,food_times[i]});
            sumTime += food_times[i];
        }
        
        if(sumTime <= k) return -1;
        
        boolean[] visited = new boolean[n+1];        
        long prevFoodTime = 0;
        
        while(!pq.isEmpty()) {
            int foodCnt = pq.size();
            int[] smallFood = pq.poll();
            
            long totalEatTime = (long)(smallFood[1] - prevFoodTime) * foodCnt;
            
            if(totalEatTime <= k) {   
                visited[smallFood[0]] = true;
                while(!pq.isEmpty() && pq.peek()[1] == smallFood[1]) {
                    visited[pq.poll()[0]] = true;
                }                
                k -= totalEatTime;
                prevFoodTime = smallFood[1];
            }
            else {
                long targetIdx = k % (long)foodCnt;
                int idx = 0;
                for(int i=1;i<=n;i++) {
                    if(visited[i]) continue;
                    if(idx++ == targetIdx) {
                        return i;
                    }
                }
            }
        }        
        
        return -1;
    }
}