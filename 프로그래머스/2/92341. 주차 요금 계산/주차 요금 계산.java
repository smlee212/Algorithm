import java.util.*;

class Solution {
    
    // 11:15
    
    public int[] solution(int[] fees, String[] records) {
        
        Map<Integer, Integer> recordMap = new HashMap<>();
        Map<Integer, Integer> timeByNumber = new HashMap<>();
        
        for(String record : records) {
            String[] recordSplit = record.split(" ");
            
            String[] timeSplit = recordSplit[0].split(":");
            int time = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
            
            int number = Integer.parseInt(recordSplit[1]);
            
            if(recordSplit[2].equals("IN")) {
                recordMap.put(number, time);
            }
            else {
                int inTime = recordMap.get(number);
                int totalTime = time - inTime;
                if(!timeByNumber.containsKey(number)) {
                    timeByNumber.put(number, totalTime);
                }
                else {
                    timeByNumber.put(number, totalTime + timeByNumber.get(number));
                }
                recordMap.remove(number);
            }
        }
        
        int outTime = 23 * 60 + 59;
        for(int number : recordMap.keySet()) {
            int inTime = recordMap.get(number);
            int totalTime = outTime - inTime;
            if(!timeByNumber.containsKey(number)) {
                timeByNumber.put(number, totalTime);
            }
            else {
                timeByNumber.put(number, totalTime + timeByNumber.get(number));
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for(int item : timeByNumber.keySet()) {
            list.add(item);
        }
        list.sort((o1,o2) -> o1-o2);
        
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            answer[i] = calcFee(fees, timeByNumber.get(list.get(i)));
        }
        
        return answer;
    }
    
    private int calcFee(int[] fees, int totalTime) {        
        if(totalTime <= fees[0]) {
            return fees[1];
        }
        
        int fee = fees[1];
        totalTime -= fees[0];
        
        if(totalTime % fees[2] == 0) {
            fee += (totalTime / fees[2]) * fees[3];
        }
        else {
            fee += ((totalTime / fees[2]) + 1 )  * fees[3];
        }
        return fee;
    }
}