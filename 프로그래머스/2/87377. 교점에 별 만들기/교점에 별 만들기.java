import java.util.*;

class Solution {
    
    // 1:24
    
    public String[] solution(int[][] line) {
        
        // 교점 맵 : y 좌표에 해당하는 x 모음들
        Map<Long, Set<Long>> yAndSetX = new HashMap<>();
        
        long maxY = Long.MIN_VALUE, maxX = Long.MIN_VALUE, minY = Long.MAX_VALUE, minX = Long.MAX_VALUE;
        
        for(int i=0;i<line.length;i++) {
            for(int j=i+1;j<line.length;j++) {
                // 두 좌표의 교점
                long[] yx = func(line[i], line[j]);
                if(yx == null) continue;
                
                // y, x의 최대 최소 갱신
                long y = yx[0], x = yx[1];
                maxY = Math.max(maxY, y);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                minX = Math.min(minX, x);
                
                // y좌표가 동일한 교점이 있는지 확인
                if(!yAndSetX.containsKey(y)) {
                    yAndSetX.put(y, new HashSet<>());
                }
                yAndSetX.get(y).add(x);
            }
        }
                
        List<String> list = new ArrayList<>();
        
        for(long i=maxY;i>=minY;i--) {
            StringBuilder sb = new StringBuilder();
            
            Set<Long> setX = yAndSetX.getOrDefault(i, new HashSet<>());
            for(long j=minX;j<=maxX;j++) {
                if(setX.contains(j)) {
                    sb.append("*");
                }   
                else {
                    sb.append(".");
                }
            }
            
            list.add(sb.toString());
        }
        
        return list.stream().toArray(String[]::new);
    }
    
    long[] func(int[] line1, int[] line2) {
        long A = line1[0], B = line1[1], E = line1[2];
        long C = line2[0], D = line2[1], F = line2[2];
        
        long ADBC = A*D - B*C;
        // 평행일 경우
        if(ADBC == 0) {
            return null;
        }
        
        boolean isY = (E*C - A*F) % ADBC == 0;
        boolean isX = (B*F - E*D) % ADBC == 0;
        if(!isY || !isX) {
            return null;
        }
        
        long y = (E*C - A*F) / ADBC;
        long x = (B*F - E*D) / ADBC;
        
        return new long[]{y,x};
    }
}