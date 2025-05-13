import java.util.*;

// 4:35

class Solution {
    
    /*
        구간의 길이는 5000, 브루트포스는 당연히 안됨
        해당 위치의 값은 약수 중 자기 자신을 제외한 값중에서 가장 큰 값
        소수인 것은 무조건 1이 된다.
        
        에라토스테네스의 체를 이용하면 1부터 N까지의 숫자들의 소수 여부를 판별할 수 있다.
        하지만 NlogN이므로 불가
        
        해당 숫자가 소수인지 판별하는 것은 logN = 최대 9이다.
        구간이 길이는 최대 5000이므로 5000 * logN 은 충분하다.
    */
    public int[] solution(long begin, long end) {
        final int MAX_NUM = 10000000;
        int[] answer = new int[(int)(end-begin)+1];
        
        int index = 0;
        for(long num=begin;num<=end;num++) {
            answer[index] = num == 1 ? 0 : 1;
            for(long i=2;i<=Math.sqrt(num);i++) {
                if(num%i==0) {
                    long result = num / i;
                    if(result <= MAX_NUM) {
                        answer[index] = (int) result;
                        break;
                    }
                    answer[index] = (int) i;
                }
            }
            index++;
        }
        
        return answer;
    }
}