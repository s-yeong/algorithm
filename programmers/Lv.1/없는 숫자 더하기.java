class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] ch = new boolean[10];
        for(int x : numbers) ch[x] = true;
        
        for(int i=0; i<10; i++) {
            if(!ch[i]) answer += i;
        }
        
        return answer;
    }
}