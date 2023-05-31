package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class binary_search01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int own_c_cnt = Integer.parseInt(br.readLine());
        List<Integer> own_c = new ArrayList<Integer>();
        String[] data = br.readLine().split(" ");
        for(int x = 0; x < own_c_cnt; x++){
            own_c.add(Integer.parseInt(data[x]));
        }
        Collections.sort(own_c);
        int sech_c_cnt = Integer.parseInt(br.readLine());
        data = br.readLine().split(" ");
        int num = 0;
        int min_idx;
        int max_idx;
        int mid_idx;
        for(int x = 0; x < sech_c_cnt; x++){
            min_idx = 0;
            max_idx = 0;
            mid_idx = 0;
            num = Integer.parseInt(data[x]);

        }
    }
}
