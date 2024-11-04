import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        
        Arrays.sort(A);
        Arrays.sort(B);
        int len = A.length;
        int a = 0;
        
        int cnt = 0;
        for(int b=0;b<len;b++) {
            for(;a<len;a++) {         
                if(A[a] < B[b]) {
                    cnt++;      
                    a++;
                }                
                break;
            }
        }     
        
        return cnt;
    }
}