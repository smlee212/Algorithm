import java.io.*;
import java.util.*;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t=0;t<3;t++) {
            N = Integer.parseInt(br.readLine());

            List<int[]> coinList = new ArrayList<>();
            int totalCoin = 0;
            for(int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());

                coinList.add(new int[]{coin, cnt});
                totalCoin += coin * cnt; // 전체 금액 갱신
            }

            // 절반이 소수점이라면 나눌 수 없음
            if(totalCoin%2!=0) {
                sb.append("0\n");
                continue;
            }

            // 전체 절반의 금액
            int halfCoin = totalCoin/2;

            // i번째 코인을 사용해서 j의 금액을 만들 수 있는지 여부
            boolean[][] dp = new boolean[N+1][halfCoin+1];

            dp[0][0] = true;
            for(int i=1;i<=N;i++) {
                int[] nowCoin = coinList.get(i-1); // 현재 선택한 코인
                for(int j=0;j<=halfCoin;j++) {
                    // j 금액을 i-1개의 코인으로 만들 수 있었다면
                    if(dp[i-1][j]) {
                        // 현재 코인을 사용하여 금액 만들기
                        for(int k=0;k<=nowCoin[1];k++) { // 코인 개수만큼 반복
                            int nextAmount = j + nowCoin[0] * k;
                            if(nextAmount <= halfCoin) {
                                dp[i][nextAmount] = true;
                            }
                        }
                    }
                }
            }

            // 모든 코인을 써서 절반의 금액을 만들 수 있는지 확인
            if(dp[coinList.size()][halfCoin]) {
                sb.append("1\n");
            }
            else {
                sb.append("0\n");
            }
        }

        System.out.println(sb);
    }
}