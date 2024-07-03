import java.io.*;
import java.util.*;

/**
파일명에 포함된 숫자를 반영한 정렬
**/
class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        
        List<File> list = new ArrayList<>();
        
        for(String str : files) {
            char[] chars = str.toCharArray();
            int nstartIdx = 0;
            int nendIdx = chars.length;
            for(int idx=0; idx<chars.length; idx++) {
                if(Character.isDigit(chars[idx])) {
                    // idx 기준 나누기
                    nstartIdx = idx;
                    for(int j=idx+1; j<chars.length; j++) {
                        // 아닌 시점
                        if(!Character.isDigit(chars[j])) {
                            nendIdx = j;
                            break;
                        }
                    }
                    
                    break;
                } // if end
            } // for chars end
            
            String head = str.substring(0, nstartIdx);
            String number = str.substring(nstartIdx, nendIdx);
            String tail = str.substring(nendIdx);
            list.add(new File(head, number, tail));
        }
        
        // 정렬
        Collections.sort(list);
        
        return list.stream()
            .map(f -> f.head+f.number+f.tail)
            .toArray(String[]::new);
    }
    
    static class File implements Comparable<File> {
        String head;
        String number;
        String tail;
        
        public File(String head, String number, String tail) {
            this.head=head;
            this.number=number;
            this.tail=tail;
        }
        
        public int compareTo(File obj) {
            
            String upperHThis = this.head.toUpperCase();
            String upperHObj = obj.head.toUpperCase();
            
            int headComparison = upperHThis.compareTo(upperHObj);
            if (headComparison != 0) {
                return headComparison;
            }
            
            int numberThis = Integer.parseInt(this.number);
            int numberObj = Integer.parseInt(obj.number);
            
            return Integer.compare(numberThis, numberObj);
        }
        
    }
}