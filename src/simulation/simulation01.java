package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class simulation01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int [][] move_data = {{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
        int size = Integer.parseInt(data[0]);
        int magic_cnt = Integer.parseInt(data[1]);
        int[][] basket = new int[size][size];
        int[][] magic = new int[magic_cnt][2];
        int[][] cloud = new int[size][size];
        List<Integer> list = new ArrayList<>();
        list.add(size-1);
        list.add(0);
        list.add(size-1);
        list.add(1);
        list.add(size-2);
        list.add(0);
        list.add(size-2);
        list.add(1);
        for(int x = 0; x < size; x++){
            data = br.readLine().split(" ");
            for(int y = 0; y <size; y++){
                basket[x][y] = Integer.parseInt(data[y]);
            }
        }
        for(int x = 0; x < magic_cnt; x++){
            data = br.readLine().split(" ");
            magic[x][0] = Integer.parseInt(data[0]);
            magic[x][1] = Integer.parseInt(data[1]);
        }
        int point_x;
        int point_y;
        int move_cnt;
        for(int i = 0; i < magic_cnt; i++){
            point_x = 0;
            point_y = 0;
            move_cnt = magic[i][1];
            int loop_cnt = list.size()/2;
            for(int x = 0; x < loop_cnt; x++){
                point_x = list.get(0)+move_data[magic[i][0]][0]*move_cnt;
                point_y = list.get(1)+move_data[magic[i][0]][1]*move_cnt;
                list.remove(0);
                list.remove(0);
                if(point_x >= size){
                    while(point_x>=size){
                        point_x -= size;
                    }
                }else if(point_x < 0){
                    while(point_x < 0){
                        point_x += size;
                    }
                }
                if(point_y >= size){
                    while(point_y >= size){
                        point_y -= size;
                    }
                }else if(point_y < 0){
                    while(point_y < 0){
                        point_y += size;
                    }
                }
                cloud[point_x][point_y] = 1;
            }
            int w_cnt;
            for(int x = 0; x < size; x++){
                for(int y = 0; y < size; y++){
                    basket[x][y] += cloud[x][y];
                }
            }
            for(int x = 0; x < size; x++){
                for(int y = 0; y <size; y++){
                    w_cnt = 0;
                    point_x = 0;
                    point_y = 0;
                    if(cloud[x][y]==1){
                        for(int z = 1; z <= 4; z++){
                            point_x = x+move_data[2*z][0];
                            point_y = y+move_data[2*z][1];
                            if(!(point_x < 0 || point_x >= size || point_y < 0 || point_y >= size)){
                                if(basket[point_x][point_y] > 0){
                                    w_cnt++;
                                }
                            }
                        }
                        basket[x][y] += w_cnt;
                    }
                }
            }
            list.clear();
            for(int x = 0; x < size; x++){
                for(int y = 0; y < size; y++){
                    if(cloud[x][y] == 0 && basket[x][y]>=2){
                        basket[x][y] -= 2;
                        list.add(x);
                        list.add(y);
                    }
                }
            }
            cloud = new int[size][size];
        }


        int total = 0;
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++) {
                total += basket[x][y];
            }
        }
        System.out.println(total);
    }
}
