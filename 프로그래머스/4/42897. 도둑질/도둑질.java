class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int n = money.length;
        // 첫 집을 터냐 안터냐
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        // n-1까지 고려해서 집 턴 최대값
        //O
        dp1[0] = money[0];
        dp1[1] = dp1[0];
        for(int i = 2; i<n-1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }
        dp1[n-1] = dp1[n-2];
        
        //X
        dp2[0] = 0;
        dp2[1] = money[1];
        for(int i = 2; i<n; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }
        
        return Math.max(dp1[n-1], dp2[n-1]);
    }
}