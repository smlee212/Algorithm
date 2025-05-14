import java.io.*;
import java.util.*;

public class Main {

    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][2*N-1];
        for(int i=0;i<N;i++) {
            Arrays.fill(map[i], ' ');
        }

        // 삼각형의 맨 윗 꼭지점에서 시작
        star(0, N-1, N);

        // 출력하기
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            for(int j=0;j<2*N-1;j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    // n은 삼각형의 높이로 생각하면 됨
    private static void star(int y, int x, int n) {
        if(n == 3) {
            map[y][x] = '*';
            map[y+1][x+1] = map[y+1][x-1] = '*';
            map[y+2][x-2] = map[y+2][x-1] = map[y+2][x] = map[y+2][x+1] = map[y+2][x+2] = '*';
        }
        else {
            int nextN = n / 2;
            star(y, x, nextN); // 윗 삼각형
            star(y+nextN, x-nextN, nextN); // 왼쪽 아래 삼각형
            star(y+nextN, x+nextN, nextN); // 오른쪽 아래 삼각형
        }
    }
}
