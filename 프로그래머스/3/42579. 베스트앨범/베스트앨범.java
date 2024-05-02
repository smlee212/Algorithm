import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {

        // 장르별 플레이 횟수
        HashMap<String, Integer> genres_cnt = new HashMap<>();
        // 장르별 고유값별로 플레이 횟수
        HashMap<String, ArrayList<int[]>> genres_playes = new HashMap<>(); // 0번째(고유값), 1번째(플레이 횟수)

        // 값 넣기
        int size = genres.length;
        for(int i=0;i<size;i++) {
            String genre = genres[i];
            int playCnt = plays[i];
            genres_cnt.put(genre, genres_cnt.getOrDefault(genre, 0) + playCnt);
            // 장르가 존재한다면
            if(genres_playes.containsKey(genre)) {
                genres_playes.get(genre).add(new int[]{i, playCnt});
            }
            // 없다면
            else {
                ArrayList<int[]> array = new ArrayList<>();
                array.add(new int[]{i, playCnt});
                genres_playes.put(genre, array);
            }
        }

        // 정렬
        List<String> keySet = new ArrayList<>(genres_cnt.keySet());
        keySet.sort((o1, o2) -> genres_cnt.get(o2).compareTo(genres_cnt.get(o1)));

        ArrayList<Integer> result = new ArrayList<>();
        for(String key : keySet) {
            ArrayList<int[]> genre_playes = genres_playes.get(key);
            genre_playes.sort((o1, o2) -> {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                else {
                    return o2[1] - o1[1];
                }
            });
            for(int i=0;i<genre_playes.size();i++) {
                if(i==2) break;
                result.add(genre_playes.get(i)[0]);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}