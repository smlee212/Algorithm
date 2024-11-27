import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // Strint -> int
        for(int i=0;i<timetable.length;i++) {
            String[] temp = timetable[i].split(":");
            timetable[i] = temp[0] + temp[1];
        }        
        int[] intTimetable = new int[timetable.length];
        for(int i=0;i<timetable.length;i++) {
            intTimetable[i] = Integer.parseInt(timetable[i]);
        }
        
        // 정렬
        Arrays.sort(intTimetable);
        
        // 출발 시각에 누가 탑승하는지 구하기
        // startTimeAndPeople.get(i) => 09:00 부터 i번째 출발시각에 탑승한 인원
        List<List<Integer>> startTimeAndPeople = new ArrayList<>();
        int nowPeople = 0;
        int startTime = 900;
        for(int i=0;i<n;i++) {
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<m;j++) {
                if(nowPeople < timetable.length && intTimetable[nowPeople] <= startTime) {
                    list.add(intTimetable[nowPeople]);
                    nowPeople++;
                }
                else
                    break;
            }
            startTimeAndPeople.add(list);
            startTime = sumTime(startTime, t);
        }
        
        // 마지막 출발 인원 목록
        List<Integer> lastStartPeople = startTimeAndPeople.get(startTimeAndPeople.size()-1);
        
        int result = 0;
        int lastTime = 900;
        for(int i=1;i<n;i++) {
            lastTime = sumTime(lastTime, t);
        }         
        
        // 1. 인원이 남는 경우
        if(lastStartPeople.size() < m){
            // 제일 늦은 시각으로 설정
            result = lastTime;
        }
        // 2. 인원이 꽉 차있을 경우
        else {
            int lastPeople = lastStartPeople.get(lastStartPeople.size() - 1);
            int hour = lastPeople / 100;
            int minute = lastPeople % 100;
            if(minute == 0) {
                hour -= 1;
                minute = 59;
                lastPeople = hour * 100 + minute;
            }
            else
                lastPeople--;
            result = lastPeople;
        }
        
        // int -> String
        int hour = result / 100;
        int minute = result % 100;
        String answer = "";
        if(hour < 10)
            answer = "0";
        answer += hour + ":";
        if(minute < 10)
            answer += "0";
        answer += minute;
        
        return answer;
    }
    
    int sumTime(int a, int b) {
        int a_h = a / 100;
        int a_m = a % 100;
        int b_h = b / 100;
        int b_m = b % 100;
        
        int sum = a_m + b_m;
        if(sum >= 60) sum = 100 + (sum-60);
        
        return sum + (a_h + b_h) * 100;
    }
}