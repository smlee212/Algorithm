import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        List<long[]> carrotList = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            long w = Long.parseLong(st.nextToken());
            long p = Long.parseLong(st.nextToken());
            carrotList.add(new long[]{w,p});
        }

        // 영양제의 크기가 크다면 오래 묵히고 먹는 것이 이득
        // 영양제의 크기가 작다면 묵히지 말고 빨리 먹어치우는 것이 낫다

        // 영양제 오름차순
        carrotList.sort(Comparator.comparingLong(o -> o[1]));

        // p >= w 이기 때문에 심자마자 바로 먹는 경우는 고려하지 않아도 됨
        // 마지막 날로부터 N개를 차례대로 먹어야 함
        long sum = 0;
        for(int i=0;i<N;i++) {
            long[] carrot = carrotList.get(i);
            sum += carrot[1] * (T-N + i)  + carrot[0];
        }

        System.out.println(sum);
    }
}