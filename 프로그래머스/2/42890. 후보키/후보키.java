import java.util.*;

class Solution {
    
    List<Integer> uniqueness;
    boolean[] visited;
    
    public int solution(String[][] relation) {        
        uniqueness = new ArrayList<>();
        visited = new boolean[relation[0].length];
        
        // 컬럼 조합을 선택
        columnCombi(relation, 0);
        
        return uniqueness.size();
    }
    
    public void columnCombi(String[][] relation, int nowCol) {        
        // 후보 키 검사
        if(nowCol == relation[0].length) {
            checkCandidateKey(relation);
            return;
        }
        
        visited[nowCol] = true;
        columnCombi(relation, nowCol + 1);
        visited[nowCol] = false;                     
        columnCombi(relation, nowCol + 1);
    }
    
    public void checkCandidateKey(String[][] relation) {
        // 유일성 확인
        Set<String> key = new HashSet<>();
        for(int i=0;i<relation.length;i++) {
            // 컬럼별로 값을 나열하여 하나의 문자열을 만듦
            StringBuilder temp = new StringBuilder();
            for(int j=0;j<relation[i].length;j++) {
                if(visited[j]) temp.append(relation[i][j]);
            }
            
            // 그 문자열이 유일한지 확인
            if(!temp.equals("") && key.contains(temp.toString())) 
                return;
            else
                key.add(temp.toString());
        }
        
        // 최소성 확인
        int bit = 0;
        for(int i=0;i<visited.length;i++) {
            if(visited[i])
                bit += 1 << i;
        }
        
        for(int i=0;i<uniqueness.size();i++) {
            int uniqueBit = uniqueness.get(i);
            // bit가 더 최소성을 띌 경우
            if((bit & uniqueBit) == bit) {
                uniqueness.remove(i--);
            }
            // bit보다 더 최소성을 띄는 것이 있다면
            else if((bit & uniqueBit) == uniqueBit) {
                return;   
            }
        }
        uniqueness.add(bit);
    }
}