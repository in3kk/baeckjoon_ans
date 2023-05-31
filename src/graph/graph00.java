package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class graph00 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int com_cnt = Integer.parseInt(br.readLine());
        int pair = Integer.parseInt(br.readLine());
        int[][] pair_data = new int[pair][2];
        String input_data;
        for(int x = 0; x< pair; x++){
            input_data = br.readLine();
            pair_data[x][0] = Integer.parseInt(input_data.split(" ")[0]);
            pair_data[x][1] = Integer.parseInt(input_data.split(" ")[1]);
        }
        boolean [] virus =  new boolean[com_cnt];
        virus[0] = true;
        int chng;
        int cnt = 0;
        while(true){
            chng = 0;
            for(int x = 0; x < com_cnt; x++){
                if(virus[x]){
                    for(int[] pair_data2 : pair_data){
                        if(pair_data2[0]==x+1 && !virus[pair_data2[1]-1]){
                            virus[pair_data2[1]-1] = true;
                            chng++;
                            cnt++;
                        }else if(pair_data2[1]==x+1 && !virus[pair_data2[0]-1]){
                            virus[pair_data2[0]-1] = true;
                            chng++;
                            cnt++;
                        }
                    }
                }
            }
            if(chng == 0)
                break;
        }
        System.out.println(cnt);
//        for(boolean x : virus){
//            System.out.println(x+" ");
//        }
    }
}