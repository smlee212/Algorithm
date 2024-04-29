class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        for(int i=0;i<photo.length;i++) {
            int score = 0;
            for (String str : photo[i]) {
                for (int j=0;j<name.length;j++) {
                    if(str.equals(name[j])) {
                        score += yearning[j];
                        break;
                    }
                }
            }
            answer[i] = score;
        }

        return answer;
    }
}