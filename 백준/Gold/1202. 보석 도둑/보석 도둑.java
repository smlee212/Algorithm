import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 보석 정보 저장
        List<int[]> gemList = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            gemList.add(new int[]{M,V});
        }

        // 가방 정보 저장
        List<Integer> bagList = new ArrayList<>();
        for(int i=0;i<K;i++) {
            int c = Integer.parseInt(br.readLine());
            bagList.add(c);
        }

        // 보석 무게 순으로 정렬
        gemList.sort(Comparator.comparingInt(o -> o[0]));
        // 가방 무게 순으로 정렬
        bagList.sort(Comparator.comparingInt(o -> o));

        // 가치 순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        long result = 0;
        int idx = 0;
        for(int bag : bagList) {
            // 현재 가방에 보석을 담을 수 있다면 pq에 저장
            while(idx < N && gemList.get(idx)[0] <= bag) {
                pq.add(gemList.get(idx));
                idx++;
            }

            // 가방에 담을 수 있는 보석이 있다면 가장 가치가 큰 보석을 선택함
            if(!pq.isEmpty()) {
                result += pq.poll()[1];
            }
        }

        System.out.println(result);
    }
}