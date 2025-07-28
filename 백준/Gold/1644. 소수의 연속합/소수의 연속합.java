import java.io.*;
import java.util.*;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        if(N==1) {
            System.out.println(0);
            return;
        }

        // 소수 리스트
        List<Integer> primeList = eratosthenes();

        int cnt=0; // 경우의 수
        int l=0, r=0, sum=primeList.get(0);
        while(l<=r) {
            // 합이 목표값보다 부족할 때
            if(sum<N) {
                if(++r>=primeList.size()) break;
                // r 포인터 증가
                sum += primeList.get(r);
            }
            // 합이 목표값이거나 넘칠 떄
            else{
                // 합이 목표값이면 경우의 수 증가
                if(sum==N) cnt++;
                // l, r 포인터가 같은 곳을 가리키고 있다면 포인터 둘 다 증가
                if(l==r) {
                    if(++r>=primeList.size()) break;
                    sum += primeList.get(r);
                }
                // l 포인터 증가
                sum -= primeList.get(l++);
            }
        }

        System.out.println(cnt);
    }

    private static List<Integer> eratosthenes() {
        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);

        // 목표값의 제곱근까지 반복
        for(int i=2;i<=Math.sqrt(N);i++) {
            // 해당 수가 소수라면
            if(isPrime[i]) {
                // 소수의 배수에 대해 false 처리하기
                // i*1, i*2, ..., i*(i-1) 까지는 모두 검사가 된 상태이므로 i*i부터 검사
                for(int j=i*i;j<isPrime.length;j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        // 소수만 리스트에 담아서 반환
        List<Integer> primeList = new ArrayList<>();
        for(int i=2;i<=N;i++) {
            if(isPrime[i]) primeList.add(i);
        }
        return primeList;
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
    }
}