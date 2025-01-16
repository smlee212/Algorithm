import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine()); // 주문한 피자 크기

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // A 피자 조각 개수
        int n = Integer.parseInt(st.nextToken()); // B 피자 조각 개수

        int[] A = new int[m]; // A 피자 조각들
        int[] B = new int[n]; // B 피자 조각들

        int totalA = 0; // A 피자 한판의 크기
        int totalB = 0; // B 피자 한판의 크기
        for(int i=0;i<m;i++) {
            A[i] = Integer.parseInt(br.readLine());
            totalA += A[i];
        }
        for(int i=0;i<n;i++) {
            B[i] = Integer.parseInt(br.readLine());
            totalB += B[i];
        }

        // 한 피자에서 나올 수 있는 크기의 개수들
        Map<Integer, Integer> numberOfCaseA = new HashMap<>();
        Map<Integer, Integer> numberOfCaseB = new HashMap<>();

        // A 피자에서 나올 수 있는 경우의 수
        for(int i=0;i<m;i++) {
            int num = 0;
            for(int j=0;j<m-1;j++) { // 연속된 조각이 1개부터 m-1개까지 각각에 대한 경우
                num += A[(i+j >= m ? i+j-m : i+j)]; // 인덱스가 넘어간 경우 처리
                if(num > size) break;
                numberOfCaseA.put(num, numberOfCaseA.getOrDefault(num,0)+1);
            }
        }
        // 피자 한판에 대한 경우
        if(totalA <= size) numberOfCaseA.put(totalA, 1);

        // B 피자에서 나올 수 있는 경우의 수
        for(int i=0;i<n;i++) {
            int num = 0;
            for(int j=0;j<n-1;j++) { // 연속된 조각이 1개부터 n-1개까지 각각에 대한 경우
                num += B[(i+j >= n ? i+j-n : i+j)]; // 인덱스가 넘어간 경우 처리
                if(num > size) break;
                numberOfCaseB.put(num, numberOfCaseB.getOrDefault(num,0)+1);
            }
        }
        // 피자 한판에 대한 경우
        if(totalB <= size) numberOfCaseB.put(totalB, 1);


        // 모든 경우의 수에 대해 나올 수 있는 방법 조합
        int resultCase = 0;

        // 1. A 에서만 판매하는 경우
        resultCase += numberOfCaseA.getOrDefault(size,0);

        // 2. B 에서만 판매하는 경우
        resultCase += numberOfCaseB.getOrDefault(size,0);

        // 3. A, B 둘 다에서 판매하는 경우
        if(numberOfCaseA.size() < numberOfCaseB.size()) {
            for (int keyA : numberOfCaseA.keySet()) {
                int keyB = size - keyA;
                int valueA = numberOfCaseA.get(keyA);
                int valueB = numberOfCaseB.getOrDefault(keyB, 0);
                resultCase += valueA * valueB;
            }
        }
        else {
            for (int keyB : numberOfCaseB.keySet()) {
                int keyA = size - keyB;
                int valueB = numberOfCaseB.get(keyB);
                int valueA = numberOfCaseA.getOrDefault(keyA, 0);
                resultCase += valueA * valueB;
            }
        }

        System.out.println(resultCase);
    }
}
