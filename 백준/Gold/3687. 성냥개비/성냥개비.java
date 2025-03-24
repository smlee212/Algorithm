import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            int n = Integer.parseInt(br.readLine());

            StringBuilder maxValue = new StringBuilder();
            // 가장 큰 값 구하기
            int x = n / 2;
            int y = n % 2;
            if(y==1) { // n이 홀수일 경우
                maxValue.append(7);
                for(int i=0;i<x-1;i++) {
                    maxValue.append(1);
                }
            }
            else { // n이 짝수일 경우
                for(int i=0;i<x;i++) {
                    maxValue.append(1);
                }
            }

            // 가장 작은 값 구하기
            int[] numberOfN = new int[]{0,
                    0,1,7,4,2,6,8,
                    10,18,22,20,28,68,88,
                    108,188,200,208,288,688,888
            };
            String minValue = "";
            StringBuilder temp = new StringBuilder();
            while(n>0) {
                if(n<=21) {
                    minValue = numberOfN[n] + temp.toString();
                    break;
                }
                else {
                    temp.append(8);
                    n -= 7;
                }
            }

            sb.append(minValue).append(" ").append(maxValue).append("\n");
        }

        System.out.println(sb);
    }
}
