import java.util.*;

class Solution {
    
    // 2:20
    
    public int[] solution(String[] operations) {      
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minPq = new PriorityQueue<>();        
        
        for(String op : operations) {
            String[] temp = op.split(" ");
            int num = Integer.parseInt(temp[1]);
            if(temp[0].equals("I")) {
                maxPq.add(num);
                minPq.add(num);
            }
            else if(num > 0 && !maxPq.isEmpty()){
                int max = maxPq.poll();
                minPq.remove(max);
            }
            else if(num < 0 && !minPq.isEmpty()) {
                int min = minPq.poll();
                maxPq.remove(min);
            }
        }
        
        if(maxPq.isEmpty()) {
            return new int[]{0,0};
        }
        else {
            return new int[]{maxPq.poll(), minPq.poll()};
        }
    }
}