import java.util.*;

class Solution {
    // 8:47
    
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];
        
        Map<Long, Long> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            long room = room_number[i];
            long nextRoom = room + 1;
            
            // 현재 방이 비어있다면
            if(!map.containsKey(room)) {
                answer[i] = room;
                map.put(room, nextRoom); 
            }
            // 이미 방이 배정되어 있다면
            else {
                nextRoom = map.get(room);
                while(map.containsKey(nextRoom)) {
                    long temp = map.get(nextRoom);
                    map.put(room, temp);
                    room = nextRoom;
                    nextRoom = temp;
                }
                answer[i] = nextRoom;
                map.put(nextRoom, nextRoom+1);
            }
        }
        
        return answer;
    }
}