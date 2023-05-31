package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class implementation01_3 {
    public int[][] ListToArray(List<Integer> list, int size){//리스트 -> 배열
        int cnt = 0;
        int[][] data = new int[size][size];
        for(int x = 0; x < (size-1)/2; x++){
            for(int y = x; y < size-x; y++){
                data[x][y] = list.get(cnt++);
            }
            for(int y = x+1; y < size-x; y++){
                data[y][size-x-1] = list.get(cnt++);
            }
            for(int y = size-x-2; y >= x; y--){
                data[size-x-1][y] = list.get(cnt++);
            }
            for(int y = size-x-2; y >= x+1; y--){
                data[y][x] = list.get(cnt++);
            }
        }
        return data;
    }
    public List<Integer> ArrayToList(int[][] data){//배열 -> 리스트
        int size = data.length;
        List<Integer> list = new ArrayList<>();
        for(int x = 0; x < (size-1)/2; x++){
            for(int y = x; y < size-x; y++){
                list.add(data[x][y]);
            }
            for(int y = x+1; y < size-x; y++){
                list.add(data[y][size-x-1]);
            }
            for(int y = size-x-2; y >= x; y--){
                list.add(data[size-x-1][y]);
            }
            for(int y = size-x-2; y >= x+1; y--){
                list.add(data[y][x]);
            }
        }
        return list;
    }
    public List<Integer> org(List<Integer> list){//0제거 -> size = array_size-1
        int idx = -1;
        for(int x = 0; x < list.size(); x++){
            if(idx == -1 && list.get(x) == 0){
                idx = x;
            }else if(idx != -1 && list.get(x) != 0){
                for(int y = idx; y <= x-1; y++){
                    list.remove(y);
                    list.add(0);
                }
                idx = -1;
            }
        }
        return list;
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        implementation01_3 imp = new implementation01_3();
        String f_line = br.readLine();
        int size = Integer.parseInt(f_line.split(" ")[0]);
        int magic_cnt = Integer.parseInt(f_line.split(" ")[1]);
        int [][] input_data = new int[size][size];
        int [][] magic_data = new int[magic_cnt][2];
        String data;
        for(int x = 0; x < size; x++){
            data = br.readLine();
            for(int y = 0; y < size; y++) {
                input_data[x][y] = Integer.parseInt(data.split(" ")[y]);
            }
        }
        for(int x = 0; x < magic_cnt; x++){
            data = br.readLine();
            magic_data[x][0] = Integer.parseInt(data.split(" ")[0]);
            magic_data[x][1] = Integer.parseInt(data.split(" ")[1]);
        }//입력 끝
        int cent_x = (size-1)/2;
        int cent_y = (size-1)/2;
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for(int i = 0; i < magic_cnt; i++){
            switch (magic_data[i][0]){// ↑, ↓, ←, →가 있고, 정수 1, 2, 3, 4
                case 1:
                    for(int o = 1; o <= magic_data[i][1]; o++){
                        input_data[cent_x-o][cent_y] = 0;
                    }
                    break;
                case 2:
                    for(int o = 1; o <= magic_data[i][1]; o++){
                        input_data[cent_x+o][cent_y] = 0;
                    }
                    break;
                case 3:
                    for(int o = 1; o <= magic_data[i][1]; o++){
                        input_data[cent_x][cent_y-o] = 0;
                    }
                    break;
                case 4:
                    for(int o = 1; o <= magic_data[i][1]; o++){
                        input_data[cent_x][cent_y+o] = 0;
                    }
                    break;
            }
            list = imp.ArrayToList(input_data);
            Collections.reverse(list);
            list = imp.org(list);
            boolean pang_chk;
            int dup_cnt;
            do{
                pang_chk = false;
                dup_cnt = 1;
                for(int x = 1; x < list.size(); x++){
                    if(list.get(x-1) != 0){
                        if(list.get(x-1) == list.get(x)){
                            dup_cnt++;
                        }else if(dup_cnt >= 4){
                            switch (list.get(x-1)){
                                case 1:
                                    cnt1+=dup_cnt;
                                    break;
                                case 2:
                                    cnt2+=dup_cnt;
                                    break;
                                case 3:
                                    cnt3+=dup_cnt;
                                    break;
                            }
                            pang_chk = true;
                            for(int y = 0; y < dup_cnt; y++){
                                list.remove(x-dup_cnt);
                                list.add(0);
                            }
                            x-=dup_cnt;
                            dup_cnt=1;
                        }else{
                            dup_cnt=1;
                        }
                    }
                }
            }while(pang_chk);
            int list2_idx = 0;
            dup_cnt = 1;
            for(int x = 1; x < list.size(); x++){
                if(list.get(x-1) != 0){
                    if(list.get(x-1) == list.get(x)){
                        dup_cnt++;
                    }else if(list2_idx < list.size()){
                        list2.add(dup_cnt);
                        list2.add(list.get(x-1));
                        list2_idx += 2;
                        dup_cnt = 1;
                    }
                    if(list2_idx >= list.size()){
                        break;
                    }
                }
            }
            if(list2_idx < list.size()){
                for(int x = list2_idx; x < list.size(); x++){
                    list2.add(0);
                }
            }
            Collections.reverse(list2);
            input_data = imp.ListToArray(list2,size);
        }
        System.out.println(1*cnt1+2*cnt2+3*cnt3);
    }
}
