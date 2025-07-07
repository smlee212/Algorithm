import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] timeTable;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        Arrays.sort(timeTable, (Comparator.comparingInt(o -> o[0])));

        int[] cntComputer = new int[N+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0])); // o[0] : 끝나는 시간, o[1] : 컴퓨터 번호
        PriorityQueue<Integer> computer = new PriorityQueue<>(Comparator.comparingInt(o -> o)); // 빈 컴퓨터

        int maxNumber = 0;
        int index = 0;

        while(index<N) {
            int[] nowUser = timeTable[index];
            // 현재 사용자가 시작할 시간을 기준으로 컴퓨터 사용이 끝난 사람들을 제거
            while(!pq.isEmpty() && pq.peek()[0] < nowUser[0]) {
                int[] finishedUser = pq.poll();
                computer.add(finishedUser[1]); // 컴퓨터 반환
                cntComputer[finishedUser[1]]++; // 사용한 컴퓨터 체크하기
            }

            int nowComputerNumber;
            // 자리가 없다면 새로운 컴퓨터 증설
            if(computer.isEmpty()) {
                nowComputerNumber = ++maxNumber;
            }
            // 자리가 있다면 가장 작은 컴퓨터 배정
            else {
                nowComputerNumber = computer.poll();
            }
            pq.add(new int[]{timeTable[index][1], nowComputerNumber});

            index++;
        }

        // 사용중인 컴퓨터 모두 반환
        while(!pq.isEmpty()) {
            cntComputer[pq.poll()[1]]++; // 사용한 컴퓨터 체크하기
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(maxNumber).append('\n');
        for(int i=1;i<=maxNumber;i++) {
            sb.append(cntComputer[i]).append(' ');
        }
        System.out.println(sb);
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        timeTable = new int[N][2];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            timeTable[i][0] = Integer.parseInt(st.nextToken());
            timeTable[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}