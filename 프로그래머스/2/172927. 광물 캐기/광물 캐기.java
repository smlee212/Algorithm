import java.util.*;
class Solution {
    
    int[][] info = {{1,1,1},{5,1,1},{25,5,1}};
    int minFatigue = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        
        for(int nowPick=0;nowPick<3;nowPick++) {
            if(picks[nowPick] > 0) {       
                picks[nowPick]--;
                dfs(picks, minerals, nowPick, 0, 0);    
                picks[nowPick]++;
            }
        }
        
        return minFatigue;
    }
    
    public boolean isEmptyMinerals(int length, int nowMineral) {
        return nowMineral >= length;
    }
    
    public boolean isEmptyPicks(int[] picks) {
        int cnt = 0;
        for(int pick : picks) {
            cnt += pick;
        }
        return cnt == 0;
    }
    
    public void dfs(int[] picks, String[] minerals, int nowPick, int nowMineral, int sumFatigue) {
        // 현재 캐야할 광물이 있는지 확인
        if(isEmptyMinerals(minerals.length, nowMineral)) {
            minFatigue = Math.min(minFatigue, sumFatigue);
            return;
        }
        
        // 5개 광물 캐기
        for(int i=nowMineral;i<nowMineral+5 && i<minerals.length;i++) {
            if(minerals[i].equals("diamond")) {
                sumFatigue += info[nowPick][0];
            }
            else if(minerals[i].equals("iron")) {
                sumFatigue += info[nowPick][1];
            }
            else if(minerals[i].equals("stone")) {
                sumFatigue += info[nowPick][2];
            }
        }
        
        // 현재 남은 곡괭이가 있는지 확인
        if(isEmptyPicks(picks)) {
            minFatigue = Math.min(minFatigue, sumFatigue);
            return;
        }
        
        // 곡괭이 선택
        for(int nextPick=0;nextPick<3;nextPick++) {
            if(picks[nextPick] > 0) {       
                picks[nextPick]--;
                dfs(picks, minerals, nextPick, nowMineral+5, sumFatigue);    
                picks[nextPick]++;
            }
        }
    }
}