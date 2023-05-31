package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class simulation00 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] data = br.readLine().split(" ");

        int size = Integer.parseInt(data[0]);
        int min = Integer.parseInt(data[1]);
        int max = Integer.parseInt(data[2]);
        int[][] input_data = new int[size][size];
        int[][] group ;
        for(int x = 0; x < size; x++){
            data = br.readLine().split(" ");
            for(int y = 0; y < size; y++){
                input_data[x][y] = Integer.parseInt(data[y]);
            }
        }
        int cnt = 0;
        int group_num;
        int tmp_num;
        boolean chng_chk;
        int c_cnt;
        int pop;
        List<Integer> list = new ArrayList<>();
        while(true){
            chng_chk = false;

            group_num = 1;
            group= new int[size][size];
            for(int x = 0; x < size; x++){
                for(int y = 0; y < size; y++){
                    list.clear();
                    if(x<size-1){
                        if(Math.abs(input_data[x][y] - input_data[x+1][y]) >= min && Math.abs(input_data[x][y] - input_data[x+1][y]) <= max){
                            list.add(x+1);
                            list.add(y);
                        }
                    }
                    if(x>0){
                        if(Math.abs(input_data[x][y] - input_data[x-1][y]) >= min && Math.abs(input_data[x][y] - input_data[x-1][y]) <= max){
                            list.add(x-1);
                            list.add(y);
                        }
                    }
                    if(y<size-1){
                        if(Math.abs(input_data[x][y] - input_data[x][y+1]) >= min && Math.abs(input_data[x][y] - input_data[x][y+1]) <= max){
                            list.add(x);
                            list.add(y+1);
                        }
                    }
                    if(y>0){
                        if(Math.abs(input_data[x][y] - input_data[x][y-1]) >= min && Math.abs(input_data[x][y] - input_data[x][y-1]) <= max){
                            list.add(x);
                            list.add(y-1);
                        }
                    }
                    if(!list.isEmpty()){
                        list.add(x);
                        list.add(y);
                        tmp_num = 0;
                        for(int i = 0; i < list.size()/2; i++){
                            if(group[list.get(i*2)][list.get(i*2+1)] != 0){
                                tmp_num = group[list.get(i*2)][list.get(i*2+1)];
                                break;
                            }
                        }
                        if(tmp_num == 0){
                            tmp_num = group_num;
                            group_num++;
                        }
                        for(int i = 0; i < list.size()/2; i++){
                            int tmp = group[list.get(i*2)][list.get(i*2+1)];
                            group[list.get(i*2)][list.get(i*2+1)]= tmp_num;
                            if(tmp != 0){
                                for(int z = 0; z < size; z++){
                                    for(int p = 0; p < size; p++){
                                        if(group[z][p] == tmp){
                                            group[z][p] = tmp_num;
                                        }
                                    }
                                }
                            }

                        }
                        chng_chk = true;
                    }
                }
            }
            if(chng_chk){
                for(int i = 1; i < group_num; i++){
                    c_cnt = 0;
                    pop = 0;
                    for(int x = 0; x < size; x++){
                        for(int y = 0; y < size; y++){
                            if(group[x][y] == i){
                                c_cnt++;
                                pop += input_data[x][y];
                            }
                        }
                    }
                    for(int x = 0; x < size; x++){
                        for(int y = 0; y < size; y++){
                            if(group[x][y] == i){
                                input_data[x][y] = pop / c_cnt;
                            }
                        }
                    }
                }
                cnt++;
            }
            else
                break;
        }
        System.out.println(cnt);
    }
}
