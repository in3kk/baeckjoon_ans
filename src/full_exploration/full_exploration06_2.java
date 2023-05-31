package full_exploration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class full_exploration06_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();
        int num = Integer.parseInt(data.split(" ")[0]);
        int size = Integer.parseInt(data.split(" ")[1]);

        boolean[][] set = new boolean[num][num];
        int a = 0; int b = 0;
        for(int x = 0; x < size; x++){
            data = br.readLine();
            a = Integer.parseInt(data.split(" ")[0]);
            b = Integer.parseInt(data.split(" ")[1]);
            set[a-1][b-1] = true;
            set[b-1][a-1] = true;
        }
        int cnt = 0;
        for(int x = 1; x <= num; x++){
            for(int y = x+1; y <= num; y++){
                if(set[x-1][y-1]){
                    continue;
                }
                for(int z = y+1; z <= num; z++){
                    if(!set[x-1][z-1] && !set[y-1][z-1]){
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
   