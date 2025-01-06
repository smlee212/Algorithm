import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int[] dy = {-1, 0, 0, 1}, dx = {0, -1, 1, 0};
    static int sizeShark = 2, countEaten = 0, time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int[] shark = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            int[] result = BFS(shark);
            if (result == null) break;

            shark[0] = result[0];
            shark[1] = result[1];
            time += result[2];

            countEaten++;
            if (countEaten == sizeShark) {
                sizeShark++;
                countEaten = 0;
            }
        }

        System.out.println(time);
    }

    private static int[] BFS(int[] shark) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[]{shark[0], shark[1], 0});
        visited[shark[0]][shark[1]] = true;

        int[] target = null;
        int minDistance = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0], x = now[1], dist = now[2];

            if (dist > minDistance) break;

            if (map[y][x] > 0 && map[y][x] < sizeShark) {
                if (target == null || 
                    y < target[0] || 
                    (y == target[0] && x < target[1])) {
                    target = new int[]{y, x, dist};
                    minDistance = dist;
                }
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) continue;
                if (map[ny][nx] > sizeShark) continue;

                visited[ny][nx] = true;
                queue.add(new int[]{ny, nx, dist + 1});
            }
        }

        if (target != null) {
            map[target[0]][target[1]] = 0;
        }

        return target;
    }
}
