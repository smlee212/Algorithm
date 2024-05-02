import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();
        for(String comp : completion) {
            if(map.containsKey(comp)) {
                map.put(comp, map.get(comp) + 1);
            }
            else {
                map.put(comp, 1);
            }
        }

        for(String part : participant) {
            if(!map.containsKey(part) || map.get(part) == 0){
                answer = part;
                break;
            }
            else {
                map.put(part, map.get(part) - 1);
            }
        }

        return answer;
    }
}