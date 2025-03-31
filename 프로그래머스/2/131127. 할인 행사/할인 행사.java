import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        final int DAY_TEN = 10;
        
        int n = want.length;        
        int[] cart = new int[n]; // 카트에 담긴 물건 개수
        int result = 0;
        
        for(int i=0;i<DAY_TEN;i++) {
            for(int j=0;j<n;j++) {
                if(want[j].equals(discount[i])) {
                    cart[j]++;
                    break;
                }
            }
        }
        
        if(isValid(n,cart,number)) {
            result++;
        }
        
        for(int i=DAY_TEN;i<discount.length;i++) {
            // 물건 추가
            for(int j=0;j<n;j++) {
                if(want[j].equals(discount[i])) {
                    cart[j]++;
                    break;
                }
            }
            
            // 물건 제거
            for(int j=0;j<n;j++) {
                if(want[j].equals(discount[i-DAY_TEN])) {
                    cart[j]--;
                    break;
                }
            }
        
            if(isValid(n,cart,number)) {
                result++;
            }
        }
        
        return result;
    }
    
    boolean isValid(int n, int[] cart, int[] number) {
        for(int i=0;i<n;i++) {
            if(cart[i] < number[i]) {
                return false;
            }
        }
        return true;
    }
}