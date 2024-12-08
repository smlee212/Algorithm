import java.util.*;

class Solution {
    public int solution(int storey) {
        int cnt = 0;
        while(storey > 0) {
            int now = storey % 10;
            if((now >= 5 && (storey/10)%10 >= 5) || now >= 6) {
                storey += 10; 
                cnt += 10 - now;
            }
            else {
                cnt += now;
            }
            storey /= 10;
        }
        return cnt;
    }
}