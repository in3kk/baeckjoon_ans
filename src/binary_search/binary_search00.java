package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class binary_search00 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());
        int cnt = 0;

        for(long i = 1; i > 0; i++){
            cnt++;
            if(num - i <= i){
                break;
            }
            num -= i;
        }
        System.out.println(cnt);
    }
}
