import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        // 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열
        // 최종 방을 개설한 사람이 보게 되는 메시지 출력
        
        String enter = "%s님이 들어왔습니다.";
        String leave = "%s님이 나갔습니다.";
        
        Map<String, String> userMap = new HashMap<>();
        List<String> ansMsg = new ArrayList<>();
        List<String> ansUser = new ArrayList<>();
        
        for(String str : record) {
            
            String[] splitStr = str.split(" ");
            
            // enter
            if("Enter".equals(splitStr[0])) {
                ansMsg.add(enter);
                ansUser.add(splitStr[1]);
                userMap.put(splitStr[1], splitStr[2]);
            }
            // leave
            else if("Leave".equals(splitStr[0])) {
                ansMsg.add(leave);
                ansUser.add(splitStr[1]);
            }
            else {
                userMap.put(splitStr[1], splitStr[2]);
            }
            
        }
        
        String[] answer = new String[ansMsg.size()];
        
        for(int idx=0; idx<ansMsg.size(); idx++) {
            String msg = ansMsg.get(idx);
            String uid = ansUser.get(idx);
            answer[idx] = String.format(msg, userMap.get(uid));
        }
        
        
        return answer;
    }
}