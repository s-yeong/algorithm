import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] arr) {
        int answer = 0;
        
        Map<String, Integer> mapCnt = new HashMap<>();
        
        // 의상의 이름, 의상의 종류
        for(int i=0; i<arr.length; i++) {
            mapCnt.put(arr[i][1], mapCnt.getOrDefault(arr[i][1], 0) + 1);
        }
        
        // 경우의수
        // key 기준, (value + 안입는 경우 포함) 곱하기
        
        answer = 1;
        for(String type : mapCnt.keySet()) {
            answer *= mapCnt.get(type) + 1;
        }
        
        // 모두 안입는 케이스 뺴기
        return answer-1;
    }
}