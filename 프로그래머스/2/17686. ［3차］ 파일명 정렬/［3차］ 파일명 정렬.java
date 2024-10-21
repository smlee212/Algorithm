class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        String[][] arr = init(files);
        bubbleSort(arr);
        for(int i=0;i<arr.length;i++) {
            answer[i] = arr[i][0];
        }
        
        return answer;
    }
    
    // [0] 원본, [1] HEAD, [2] NUMBER, [3] TAIL
    // files를 정렬할 시 편의를 위해 2차원 배열로 변환
    String[][] init(String[] files) {
        String[][] arr = new String[files.length][4];
        
        for(int i=0;i<files.length;i++) {
            arr[i][0] = files[i];
            
            StringBuilder sb = new StringBuilder();
            Boolean isNum = false;
            for(int j=0;j<files[i].length();j++) {
                char c = files[i].charAt(j);
                if(!isNum) {
                    if('0'<=c && c<='9') {
                        arr[i][1] = sb.toString().toLowerCase();
                        sb.setLength(0);
                        isNum = true;
                    }
                    sb.append(c);
                }
                else {
                    if('0'<=c && c<='9') {
                        sb.append(c);
                    }   
                    else {
                        arr[i][2] = sb.toString();
                        arr[i][3] = files[i].substring(j);
                        isNum = false;
                        break;
                    }                    
                }
            }
            if(isNum) {
                arr[i][2] = sb.toString();
                arr[i][3] = "";
            }
        }
        
        return arr;
    }
    
    // 안전 정렬 필요 (버블 / 삽입 / 머지)
    // n은 1000이므로 n^2의 알고리즘을 사용해도 괜찮음
    void bubbleSort(String[][] arr) {
        String[] temp;
        for(int i=0;i<arr.length;i++) {            
            for(int j=1;j<arr.length-i;j++) {
                // 연속한 두 원소끼리 비교
                if(compare(arr[j-1], arr[j])) {
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    boolean compare(String[] o1, String[] o2) {
        // HEAD 비교
        if(o1[1].equals(o2[1])) {
            // NUMBER 비교
            int num1 = Integer.parseInt(o1[2]);
            int num2 = Integer.parseInt(o2[2]);
            if(num1 == num2) {
                // TAIL 비교
                // 들어온 그대로 유지해야 하므로 교환하지 않는다
                return false;
            }
            else {
                // 왼쪽 원소가 클 경우 교환한다
                return num1 > num2;
            }
        }
        else {
            // 왼쪽 원소가 클 경우 교환한다
           return o1[1].compareTo(o2[1]) > 0;
        }
            
    }
}