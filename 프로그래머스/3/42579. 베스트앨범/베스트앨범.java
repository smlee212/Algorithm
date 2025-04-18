import java.util.*;
class Solution {
    // 12:40
    
    public List<Integer> solution(String[] genres, int[] plays) {
        // 장르별 재생횟수 구하기
        Map<String, Integer> map1 = new HashMap<>();
        for(int i=0;i<genres.length;i++) {
            String genre = genres[i];
            map1.put(genre, map1.getOrDefault(genre, 0) + plays[i]);
        }
        
        // 재생 횟수로 장르 오름차순 정렬
        List<String> keySet1 = new ArrayList<>(map1.keySet());
        keySet1.sort((o1,o2) -> map1.get(o2) - map1.get(o1)); 
        
        // 장르별 노래의 재생횟수 정리하기
        Map<String, List<int[]>> map2 = new HashMap<>(); // int[0] : 인덱스, int[1] : 재생횟수
        for(int i=0;i<genres.length;i++) {
            String genre = genres[i];
            List<int[]> value = map2.getOrDefault(genre, new ArrayList<>());
            value.add(new int[]{i, plays[i]});
            map2.put(genre, value);
        }
        
        // 베스트 앨범
        List<Integer> bestAlbum = new ArrayList<>();
        
        // 1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
        for(String key : keySet1) {
            List<int[]> value = map2.get(key);
            value.sort((o1,o2) -> {
                // 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                // 2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
                return o2[1] - o1[1];
            });
            
            // 2개 곡 뽑기
            for(int i=0;i<2 && i<value.size();i++) {
                bestAlbum.add(value.get(i)[0]);
            }
        }
        
        return bestAlbum;
    }
}