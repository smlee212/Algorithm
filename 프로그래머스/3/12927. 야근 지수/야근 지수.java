import java.util.*;

class Solution {
    
    public long solution(int n, int[] works) {        
        long[] number = new long[50001];
        for(int work : works) {
            number[work]++;
        }
        
        for(int i=50000;i>0;i--) {
            if(number[i] == 0) continue;
            
            if(number[i] < n) {
                n -= number[i];
                number[i-1] += number[i];
                number[i] = 0;
            }
            else {
                number[i-1] += n;
                number[i] -= n;
                break;
            }
        }
        
        long answer = 0;
        for(int i=1;i<=50000;i++) {
            if(number[i] > 0)
                answer += i * i * number[i];   
        }
        
        return answer;
    }
}