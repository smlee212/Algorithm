// 7시 30분 시작
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for(int i=1;i<=r2;i++) {
            double y2 = Math.sqrt(Math.pow(r2,2) - Math.pow(i,2));
            double y1 = r1 > i ? Math.sqrt(Math.pow(r1,2) - Math.pow(i,2)) : 0;
            answer += Math.floor(y2) - Math.ceil(y1) + 1;
        }
        
        return answer * 4;
    }
}