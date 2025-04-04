import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] map = new int[N+k-1];
        for(int i=0;i<N;i++) {
            map[i] = Integer.parseInt(br.readLine());
        }
        for(int i=0;i<k-1;i++) {
            map[i+N] = map[i];
        }

        int[] cntSushi = new int[d+1];
        int numberOfSushi = 0;
        int maxNumberOfSushi = 0;

        // 초기 세팅
        for(int i=0;i<k;i++) {
            int sushi = map[i];
            // 현재 스시 추가
            if(cntSushi[sushi] == 0) {
                numberOfSushi++;
            }
            cntSushi[sushi]++;
        }
        // 계산
        maxNumberOfSushi = cntSushi[c] == 0 ? numberOfSushi + 1 : numberOfSushi;

        // 남은 회전초밥에 대해 진행
        for(int i=k;i<map.length;i++) {
            int lastSushi = map[i];
            int firstSushi = map[i-k];
            // 현재 스시 추가
            if(cntSushi[lastSushi] == 0) {
                numberOfSushi++;
            }
            cntSushi[lastSushi]++;

            // 처음 스시 제거
            cntSushi[firstSushi]--;
            if(cntSushi[firstSushi] == 0) {
                numberOfSushi--;
            }

            // 계산
            maxNumberOfSushi = Math.max(maxNumberOfSushi, cntSushi[c] == 0 ? numberOfSushi + 1 : numberOfSushi);
        }

        System.out.println(maxNumberOfSushi);
    }
}
