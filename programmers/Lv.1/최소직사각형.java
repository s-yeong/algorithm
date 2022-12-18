class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        // 0 큰값, 작은값
        for(int i=0; i<sizes.length; i++) {
            if(sizes[i][0] < sizes[i][1]) {
                int tmp = sizes[i][0] ;
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
        }
        
        int w=0,h=0;
        for(int i=0; i<sizes.length; i++) {
            w = Math.max(w,sizes[i][0]);
            h = Math.max(h,sizes[i][1]);
        }
        
        answer = w*h;
        return answer;
    }
}