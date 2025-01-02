import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static final int N = 4;
    static final int M = 8;
    static int K;
    static int[][] cogwheels;
    static int[] pivot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cogwheels = new int[N][M];
        pivot = new int[N];

        for(int i=0;i<N;i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0;j<M;j++) {
                cogwheels[i][j] = input[j] - '0';
            }
        }

        K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int cogwheel = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            func1(cogwheel, direction);
        }

        int result = func2();

        System.out.println(result);
    }

    private static int getIndex(int index) {
        if(index < 0) {
            return M - ((-index) % M);
        }
        else if(index >= M) {
            return index % M;
        }
        else {
            return index;
        }
    }

    private static void func1(int cogwheel, int direction) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        visited[cogwheel] = true;
        dq.add(new int[]{cogwheel, direction});

        while(!dq.isEmpty()) {
            int[] now = dq.poll();
            cogwheel = now[0];
            direction = now[1];

            if(cogwheel-1 >= 0 && !visited[cogwheel-1]) {
                int nowIndex = getIndex(pivot[cogwheel]-2);
                int leftIndex = getIndex(pivot[cogwheel-1]+2);
                if(cogwheels[cogwheel][nowIndex] != cogwheels[cogwheel-1][leftIndex]) {
                    visited[cogwheel-1] = true;
                    dq.add(new int[]{cogwheel-1, -direction});
                }
            }
            if(cogwheel+1 < N && !visited[cogwheel+1]) {
                int nowIndex = getIndex(pivot[cogwheel]+2); 
                int rightIndex = getIndex(pivot[cogwheel+1]-2);
                if(cogwheels[cogwheel][nowIndex] != cogwheels[cogwheel+1][rightIndex]) {
                    visited[cogwheel+1] = true;
                    dq.add(new int[]{cogwheel+1, -direction});
                }
            }

            pivot[cogwheel] = getIndex(pivot[cogwheel] - direction);
        }
    }

    private static int func2() {
        int sum = 0;
        for(int i=0,multiple=1;i<N;i++,multiple*=2) {
            sum += multiple * (cogwheels[i][pivot[i]] == 0 ? 0 : 1);
        }
        return sum;
    }
}