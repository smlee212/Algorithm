class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        // 10:15
        int n = cookie.length;
        
        // m을 정해놓고 l과 r을 확장해나감
        for(int m1=0;m1<n-1;m1++) {
            int l=m1, r=m1+1;
            int sum1=cookie[l], sum2=cookie[r];
            while(l>=0 && r<n) {
                if(sum1<sum2) {
                    if(l==0) break;
                    l--;
                    sum1 += cookie[l];
                }
                else if(sum1>sum2) {
                    if(r==n-1) break;
                    r++;
                    sum2 += cookie[r];
                }
                else {           
                    answer = Math.max(answer, sum1);
                    if(l==0 || r==n-1) break;
                    l--; r++;
                    sum1 += cookie[l];
                    sum2 += cookie[r];
                }
            }
        }
        
        
        return answer;
    }
}