import java.io.;
import java.util.;

class Solution {
    static int[] ch;
    static TreeSetInteger set = new TreeSet();
    public boolean isPrime(int num) {
        if(num == 1  num == 0) return false;
        for(int i=2; inum; i++) {
            if(num%i == 0) return false;
        }
        return true;
    }
    
    public void DFS(int L, String num, char[] chars) {
     
        if(L == chars.length) {
            if(!num.equals()) set.add(Integer.parseInt(num));
        }
        else {
            for(int i=0; ichars.length; i++) {
                if(ch[i] == 0) {
                ch[i] = 1;
                DFS(L+1, num+chars[i], chars);
                DFS(L+1, num,chars);
                ch[i] = 0;
                }
            }
        }
        
    }
    
    public int solution(String numbers) {
        
        int answer=0;
        
        char[] chars = numbers.toCharArray();
        ch = new int[chars.length];
        DFS(0,,chars);
        
        for(int x  set) {
            if(isPrime(x)) answer++;
        }
        
        return answer;
    }
}