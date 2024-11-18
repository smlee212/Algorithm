import java.util.*;
class Solution {
    int maxPoint = Integer.MIN_VALUE;
    int[] result, temp;
    
    public int[] solution(int n, int[] info) {
        result = new int[info.length];
        temp = new int[info.length];
                 
        // 0번째 과녁 이길 경우
        temp[0] = info[0]+1;
        dfs(info, n, 0, temp[0]);   
        // 0번째 과녁 질 경우
        temp[0] = 0;
        dfs(info, n, 0, 0);
        
        return maxPoint == Integer.MIN_VALUE ? new int[]{-1} : result;
    }
    
    void dfs(int[] info, int n, int now, int cnt) {
        // 화살을 정해진 개수 이상으로 사용했을 경우
        if(cnt > n) return;
        // 모든 과녁을 처리했을 경우 (0점 과녁은 제외)
        else if(now == info.length-2) {
            temp[info.length-1] = n - cnt;
            // 조건을 만족할 때 결과 배열 갱신
            int winPoint = calcWinPoint(info);
            if(winPoint > 0) {
                renewCase(info, winPoint);
            }      
            return;
        }
        
        int next = now + 1;
        // 다음 과녁 이길 경우
        temp[next] = info[next] + 1;
        dfs(info, n, next, cnt + temp[next]);
        // 다음 과녁 질 경우
        temp[next] = 0;
        dfs(info, n, next, cnt);
    }
    
    int calcWinPoint(int[] info) {
        int winPoint = 0;
        
        for(int i=0;i<info.length-1;i++) {
            if(info[i] == 0 && temp[i] == 0) continue;
            // 화살 개수가 많을 경우 득점
            else if(info[i] < temp[i]) {
                winPoint += 10 - i;
            }
            // 같거나 적을 경우 실점
            else {
                winPoint -= 10 - i;
            }   
        }
        
        return winPoint;
    }
    
    void renewCase(int[] info, int winPoint) {
        // 최대 점수가 같을 경우 가장 낮은 점수를 더 많이 맞힌 경우로 갱신
        if(maxPoint == winPoint) {
            boolean check = false;
            for(int i=info.length-1;i>=0;i--) {
                if(result[i] < temp[i]) {
                    check = true;
                    break;
                }
                else if(result[i] > temp[i]) {
                    break;
                }
            }
            
            if(check) {
                for(int i=0;i<info.length;i++) {
                    result[i] = temp[i];
                }
            }
        }
        // 최대 점수보다 클 경우 갱신
        else if(maxPoint < winPoint) {
            maxPoint = winPoint;
            for(int i=0;i<info.length;i++) {
                result[i] = temp[i];
            }
        }
    }
}