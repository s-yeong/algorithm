import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        int max_health = health;
        int con_succ_time = 0;
        int time = 0;
        int aIdx = 0;
        while(true) {
            
            //atack
            if(time == attacks[aIdx][0]) {
                int damage = attacks[aIdx][1];
                health -= damage;
                con_succ_time = 0;
                aIdx++;
                if(aIdx == attacks.length || health < 0) break;
            }
            else {
                con_succ_time++;
                if(con_succ_time == t) {
                    health += y;
                    con_succ_time = 0;
                }
                health += x;
                health = Math.min(health, max_health);
            }
            
            time++;
        }
        
        
        
        
        return health <= 0 ? -1 : health;
    }
}