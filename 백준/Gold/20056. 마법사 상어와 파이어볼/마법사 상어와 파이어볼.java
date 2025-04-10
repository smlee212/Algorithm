import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N,M,K;
    private static List<FireBall> fireBallList;
    private static int[] dy = {-1,-1,0,1,1,1,0,-1},
                         dx = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireBallList = new ArrayList<>(); // 파이어볼 모음
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBallList.add(new FireBall(y,x,m,s,d));
        }

        for(int i=0;i<K;i++) {
            func();
        }

        int sum = 0;
        for(FireBall fireBall : fireBallList) {
            sum += fireBall.m;
        }
        System.out.println(sum);
    }

    private static void func() {
        List<FireBall> newFireBallList = new ArrayList<>();
        List<FireBall>[][] map = new ArrayList[N+1][N+1];

        // 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동한다
        for(FireBall fireBall : fireBallList) {
            // 방향 d로 s칸 만큼 이동
            fireBall.move();
            // 이동한 좌표에 파이어볼 위치
            if(map[fireBall.y][fireBall.x] == null)
                map[fireBall.y][fireBall.x] = new ArrayList<>();
            map[fireBall.y][fireBall.x].add(fireBall);
        }

        // 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(map[i][j] != null) {
                    // 2개 이상일 경우
                    if(map[i][j].size() >= 2) {
                        // flag 의 절대값이 파이어볼 개수와 같은지 확인
                        int flag = 0;
                        // 2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
                        FireBall fireBall = new FireBall(i, j, 0, 0, 0);
                        for (FireBall fb : map[i][j]) {
                            fireBall.sum(fb);
                            flag += fb.d % 2 == 0 ? 1 : -1; // 짝수면 +1, 홀수면 -1
                        }

                        if (fireBall.m < 5) continue; // 3-4. 질량이 0인 파이어볼은 소멸되어 없어진다.
                        // 2-2. 파이어볼은 4개의 파이어볼로 나누어진다.
                        List<FireBall> list = fireBall.div(map[i][j].size(), Math.abs(flag) == map[i][j].size());
                        newFireBallList.addAll(list);
                    }
                    // 1개만 있을 경우
                    else {
                        newFireBallList.add(map[i][j].get(0));
                    }
                }
            }
        }

        fireBallList = new ArrayList<>(newFireBallList);
        newFireBallList.clear();
    }

    private static class FireBall {
        int y, x, m, s, d;
        public FireBall(int y, int x, int m, int s, int d) {
            this.y=y; this.x=x; this.m=m; this.s=s; this.d=d;
        }
        public void move() {
            int ny = y;
            int nx = x;
            for(int i=0;i<s;i++) {
                ny = (ny+dy[d])%N; ny = ny==0 ? N : ny;
                nx = (nx+dx[d])%N; nx = nx==0 ? N : nx;
            }
            y=ny;
            x=nx;
        }
        public void sum(FireBall fireBall) {
            this.m += fireBall.m;
            this.s += fireBall.s;
            this.d += fireBall.d;
        }
        // 3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
        public List<FireBall> div(int cnt, boolean flag) {
            List<FireBall> list = new ArrayList<>();
            for(int i=0;i<4;i++) {
                // 3-1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
                // 3-2. 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
                // 3-3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그지 않으면 1, 3, 5, 7이 된다.
                list.add(new FireBall(y, x, m/5, s/cnt, 2*i + (flag?0:1)));
            }
            return list;
        }
    }
}