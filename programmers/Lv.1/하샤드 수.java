class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int n = String.valueOf(x).chars().map(Character::getNumericValue).sum();
        if(x%n != 0) answer = false;
        return answer;
    }
}