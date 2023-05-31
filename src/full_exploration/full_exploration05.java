package full_exploration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class full_exploration05 {
    public int pro(int turn){
        return (8 + 2 * (turn-1));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        full_exploration05 full = new full_exploration05();
        int num = Integer.parseInt(br.readLine());
        int cnt = Integer.parseInt(br.readLine());
        int condition = Integer.parseInt(br.readLine());
        int total = 0;
        int idx = 0;
        for(int x = 1; x > 0; x++){
            if(total + full.pro(x) < cnt*2){
                total += full.pro(x);
                idx = x;
            }else{
                break;
            }
        }
        System.out.println(idx);
        System.out.println(total);
        int cnt_total = total > 0 ? cnt-total/2 : cnt;
        if(condition == 0){
            if(cnt_total <= 2){
                total = total+(2*cnt_total-1);
            }else if(cnt_total>2){
                if(cnt_total-2 <= idx+2){
                    total = total + 2 + cnt_total;
                }
            }
        }else if(condition == 1){
            if(cnt_total <= 2){
                total = total+2*cnt_total;
            }else if(cnt_total > 2){
                total = total + 4 + cnt_total+idx;
            }
        }
        System.out.println(total%num > 0 ? total%num-1:num-1);
    }
}
