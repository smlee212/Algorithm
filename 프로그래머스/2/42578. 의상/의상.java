import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for(String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        List<Integer> cntList = map.values().stream().collect(Collectors.toList());
        int answer = 1;
        for(int cnt : cntList) {
            answer *= (cnt + 1);
        }

        return answer - 1;
    }
}