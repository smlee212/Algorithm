import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 보석 종류 세기
        Set<String> set = new HashSet<>();
        for(String gem : gems) 
            set.add(gem);
        int n = set.size();
        
        int[] answer = new int[2];
        
        // 투 포인터
        int left = 0; // 범위 왼쪽 포인터
        int minLen = Integer.MAX_VALUE; // 범위의 최소 길이
        Map<String, Integer> map = new HashMap<>(); // 현재 범위에서의 보석의 종류와 개수
        
        // 범위 오른쪽 포인터를 늘려나감
        for(int right=0;right<gems.length;right++) {
            // 제일 오른쪽 보석을 map에 추가
            map.put(gems[right], map.getOrDefault(gems[right],0) + 1);    
            
            // 범위 왼쪽 포인터를 늘려나감
            // 제일 왼쪽 보석이 map에 여러개 있다면 하나 제거
            while(map.get(gems[left]) > 1) {
                map.put(gems[left], map.get(gems[left]) - 1);
                left++;
            }
            
            // map의 사이즈는 곧 보석의 종류이므로 n과 같아졌을때 범위 길이 갱신
            if(map.size() == n && minLen > right - left) {
                minLen = right - left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
        }        
       
        return answer;
    }
}