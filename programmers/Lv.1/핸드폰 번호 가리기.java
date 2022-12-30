class Solution {
    public String solution(String pnum) {
        String answer = "";
        char[] array = pnum.toCharArray();
        for(int i=0; i<pnum.length()-4; i++) array[i] = '*';
        answer = String.valueOf(array);
        
        return answer;
    }
}