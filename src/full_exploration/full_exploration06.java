package full_exploration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class full_exploration06 {
    public int factorial(int num){
        int a = 1;
        if(num > 1){
            a = factorial(num-1)*num;
        }
        return a;
    }
    public int getTotal(int num){
        int num2 = (factorial(3)*factorial(num-3));
        num2 = num2 > 1 ? num2 : 1;
        int result = factorial(num)/num2;
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        full_exploration06 full = new full_exploration06();
        String data = br.readLine();
        int num = Integer.parseInt(data.split(" ")[0]);
        int size = Integer.parseInt(data.split(" ")[1]);
        if(num >= 3){
            int[][] nset = new int[size][2];
            for(int x = 0; x < size; x++){
                data = br.readLine();
                nset[x][0] = Integer.parseInt(data.split(" ")[0]);
                nset[x][1] = Integer.parseInt(data.split(" ")[1]);
            }
            int total = full.getTotal(num);
            int dup_chk = 0;
            for(int x = 0; x < num; x++){
                for(int y = x+1; y < num; y++){
                    if((nset[x][0] == nset[y][0] && !(nset[x][1] == nset[y][1])) || (nset[x][0] == nset[y][1] && !(nset[x][1] == nset[y][0]))||
                            (nset[x][1] == nset[y][0] && !(nset[x][0] == nset[y][1])) || (nset[x][1] == nset[y][1] && !(nset[x][0] == nset[y][0]))){
                        total++;
                    }
                    if((nset[x][0] == nset[y][0] && (nset[x][1] == nset[y][1])) || (nset[x][0] == nset[y][1] && (nset[x][1] == nset[y][0]))||
                            (nset[x][1] == nset[y][0] && (nset[x][0] == nset[y][1])) || (nset[x][1] == nset[y][1] && (nset[x][0] == nset[y][0]))){
                        dup_chk++;
                    }
                }
            }
            total = total - size*(num-2) - dup_chk/2;
            System.out.println(total);
        }else{
            System.out.println(0);
        }

    }
}
