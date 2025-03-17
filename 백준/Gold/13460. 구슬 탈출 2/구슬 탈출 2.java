import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Bead redBead = null, blueBead = null;

        board = new char[N][M];
        for(int i=0;i<N;i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0;j<M;j++) {
                if(input[j] == 'R') {
                    redBead = new Bead(i,j);
                    board[i][j] = '.';
                }
                else if(input[j] == 'B') {
                    blueBead = new Bead(i,j);
                    board[i][j] = '.';
                }
                else {
                    board[i][j] = input[j];
                }
            }
        }

        bfs(redBead, blueBead);
    }

    /**
     *  4방 탐색을 해야한다.
     *  이때, 방문처리를 해야하나 싶었지만 10번 이하로 움직임의 제한이 있었다
     *      - 4^10=1,048,576 의 시간복잡도를 가지므로 방문처리를 할 필요가 없다
     *      - 또한, 똑같은 곳을 방문하지 말아야 하는 것이 아닌, 2개의 구슬이 동일한 상태가 되지 않도록 해야 하는 것이므로 방문처리 필요 없음
     *          (차라리 2개의 구슬의 상태를 기억하는 방식이 도움이 될 것이지만, 시간복잡도에서 문제가 없으므로 생략)
     *
     *  board는 그대로 놔두고, 2개의 구슬의 위치를 변화시켜가며 BFS를 진행
     *
     *  1시간째 풀이중... 예제 2번을 보고 문제를 잘못 이해했다는걸 깨달음...
     *      상하좌우로 판을 기울이면 1칸이 이동하는게 아니라 갈수있는 곳까지 계속 감...............
     */
    private static void bfs(Bead redBead, Bead blueBead) {
        int[] dy = {-1,0,1,0},
              dx = {0,1,0,-1};

        Deque<Bead[]> dq = new ArrayDeque<>(); // 0번째 빨간구슬, 1번째 파랑구슬
        dq.add(new Bead[]{redBead, blueBead});

        int cnt = 0;
        while(!dq.isEmpty()) {
            int size = dq.size();
            for(int s=0;s<size;s++) {
                Bead[] now = dq.poll();
                redBead = now[0];
                blueBead = now[1];

                if(redBead.isHole()) {
                    System.out.println(cnt);
                    return;
                }

                for(int i=0;i<4;i++) {
                    Bead nRedBead = new Bead(redBead);
                    Bead nBlueBead = new Bead(blueBead);

                    // 구슬 이동
                    nRedBead.move(dy[i], dx[i]);
                    nBlueBead.move(dy[i], dx[i]);

                    // 파란 구슬이 구멍에 들어간 경우
                    if(nBlueBead.isHole()) {
                        continue;
                    }
                    // 두 구슬의 위치가 같아질 경우
                    if(nRedBead.equals(nBlueBead)) {
                        // 이동하기 전 구슬이 한 행에 위치했었다면
                        if(dy[i]==0) {
                            if(redBead.x < blueBead.x) {
                                if(dx[i] < 0)
                                    nBlueBead.x += 1;
                                else
                                    nRedBead.x -= 1;
                            }
                            else {
                                if(dx[i] < 0)
                                    nRedBead.x += 1;
                                else
                                    nBlueBead.x -= 1;
                            }
                        }
                        // 이동하기 전 구슬이 한 열에 위치했었다면
                        else {
                            if(redBead.y < blueBead.y) {
                                if(dy[i] < 0)
                                    nBlueBead.y += 1;
                                else
                                    nRedBead.y -= 1;
                            }
                            else {
                                if(dy[i] < 0)
                                    nRedBead.y += 1;
                                else
                                    nBlueBead.y -= 1;
                            }
                        }
                    }
                    // 두 구슬의 최종 위치가 변하지 않았을 경우
                    if(nRedBead.equals(redBead) && nBlueBead.equals(blueBead)) {
                        continue;
                    }

                    dq.add(new Bead[]{nRedBead, nBlueBead});
                }
            }
            if(cnt >= 10) break;
            cnt++;
        }
        System.out.println(-1);
    }

    private static class Bead {
        public int y;
        public int x;
        public Bead(int y, int x) {
            this.y=y;
            this.x=x;
        }
        public Bead(Bead bead) {
            this.y=bead.y;
            this.x=bead.x;
        }
        public boolean isHole() { // 구멍에 있는지 확인
            return y>=0 && x>=0 && y<N && x<M && board[y][x]=='O';
        }
        public void move(int dy, int dx) {
            int ny = this.y + dy;
            int nx = this.x + dx;
            while(board[ny][nx]!='#') {
                this.y = ny;
                this.x = nx;
                if(board[ny][nx]=='O')
                    return;
                ny += dy;
                nx += dx;
            }
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Bead) {
                Bead bead = (Bead) obj;
                return this.y == bead.y && this.x == bead.x;
            }
            return false;
        }
    }
}
