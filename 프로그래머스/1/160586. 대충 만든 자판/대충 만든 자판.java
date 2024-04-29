class Solution {

    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        int[] keyCnt = new int[26];
        for(int i=0;i<26;i++) {
            int minIdx = 101;
            for(String str : keymap) {
                String a = String.valueOf((char)('A' + i));
                if(str.contains(a))
                    minIdx = Math.min(minIdx, str.indexOf(a));
            }
            keyCnt[i] = minIdx;
        }

        int i=0;
        for(String str : targets) {
            int cnt = 0;
            for(char c : str.toCharArray()) {
                int idx = c - 'A';
                if(keyCnt[idx] == 101) {
                    cnt = -1;
                    break;
                }                
                cnt += keyCnt[idx]+1;
            }
            answer[i++] = cnt;
        }

        return answer;
    }
}