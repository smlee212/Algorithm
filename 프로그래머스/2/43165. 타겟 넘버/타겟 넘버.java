
class Solution {

    int cnt = 0;

    public int solution(int[] numbers, int target) {
        permutation(numbers, 0, 0, target);

        return cnt;
    }

    void permutation(int[] number, int currIndex, int sum, int target){
        if(number.length == currIndex) {
            if(sum == target)
                cnt++;
            return;
        }

        permutation(number, currIndex+1,sum+number[currIndex], target);
        permutation(number, currIndex+1,sum-number[currIndex], target);
    }
}