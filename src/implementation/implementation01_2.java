package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class implementation01_2 {
    static int cnt1 = 0;
    static int cnt2 = 0;
    static int cnt3 = 0;
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
        list.add(0);
        list.add(0);
        list.add(0);
        return list;
    }
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

    public List<Integer> org(List<Integer> list,int size){//0제거 -> size = array_size-1
        int cnt = 0;
        for(int x = 0; x < size; x++){
            if(list.get(x) != 0){
                cnt++;
            }
        }
        for(int x = size; x >= 0; x--){
            if(list.get(x)==0){
                for(int y = x; y > 0; y--){
                    list.set(y,list.get(y-1));
                }
                x++;
                list.set(0,0);
                if(x <= size-cnt )
                    break;
            }
        }
        return list;
    }

    public List<Integer> pang(List<Integer> list, int size){//같은 수가 4번 이상 반복되면 제거 -> size = array_size
        int cnt=1;
        boolean pang_chk;
        do{
            pang_chk = false;
            int one = list.get(size);
            int two = list.get(size+1);
            int thr = list.get(size+2);
            for(int x = 1; x < size; x++){
                if(list.get(x-1) != 0){
                    if(list.get(x-1) == list.get(x)){
                        cnt++;
                    }else if(cnt>=4){
                        for(int y = x-1; y >= x-cnt; y-- ){
                            switch (list.get(y)){
                                case 1:
                                    one++;
                                    break;
                                case 2:
                                    two++;
                                    break;
                                case 3:
                                    thr++;
                                    break;
                            }
                            list.set(y, 0);
                            pang_chk = true;
                        }
                        cnt = 1;
                    }else{
                        cnt=1;
                    }
                }
            }
            if(list.get(0)!=0 && cnt >= 4){
                for(int y = cnt; y >= 0; y--){
                    list.set(y,0);
                }
                cnt=1;
            }
            list.set(size, one);
            list.set(size+1, two);
            list.set(size+2, thr);
            list = org(list,size-1);
        }while(pang_chk);

        return list;
    }
//    public List<Integer> group(List<Integer> list, int size){//그룹화 & 변환
//        List<Integer> list2 = list;
//        int cnt = 1;
//        int list2_idx = 1;
//        for(int x = size*size-3; x >= 0; x--){
//            if(list.get(x+1) != 0){
//                if(list.get(x) == list.get(x+1)){
//                    cnt++;
//                }else if(list.get(x) != list.get(x+1)){
//                    if(size*size-1-list2_idx >= 0)
//                        list2.set(size*size-1-list2_idx,cnt);
//                    if(size*size-2-list2_idx>=0)
//                        list2.set(size*size-2-list2_idx,list.get(x+1));
//                    cnt=1;
//                    list2_idx += 2;
//                }
//            }
//        }
//        for(int x = size*size-1-list2_idx; x >= 0; x--){
//            list2.set(x,0);
//        }
//        return list2;
//    }
    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        implementation01_2 imp = new implementation01_2();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String f_line = br.readLine();
        int size = Integer.parseInt(f_line.split(" ")[0]);
        int magic_cnt = Integer.parseInt(f_line.split(" ")[1]);
        int[][] magic_data = new int[magic_cnt][2];
        int[][] begin_data = new int[size][size];
        for (int x = 0; x < size; x++) {//초기 구슬 정보 입력
            String data_line = br.readLine();
            for (int y = 0; y < size; y++) {
                begin_data[x][y] = Integer.parseInt(data_line.split(" ")[y]);
            }
        }
        for(int x = 0; x < magic_cnt; x++){//마법 정보 입력
            String magic_line = br.readLine();
            magic_data[x][0] = Integer.parseInt(magic_line.split(" ")[0]);
            magic_data[x][1] = Integer.parseInt(magic_line.split(" ")[1]);
        }
        br.close();

        int cent_x = (size-1)/2;
        int cent_y = (size-1)/2;
        int array_size = size*size-1;
        for(int x = 0; x < magic_cnt; x++){
            //1. 마법 사용
            if(magic_data[x][0] == 1){// ↑, ↓, ←, →가 있고, 정수 1, 2, 3, 4
                for(int y = 1; y <= magic_data[x][1]; y++){
                    switch (begin_data[cent_x-y][cent_y]){
                        case 1:
                            imp.cnt1++;
                            break;
                        case 2:
                            imp.cnt2++;
                            break;
                        case 3:
                            imp.cnt3++;
                            break;
                    }
                    begin_data[cent_x-y][cent_y] = 0;
                }
            }else if(magic_data[x][0]==2){
                for(int y = 1; y <= magic_data[x][1]; y++){
                    switch (begin_data[cent_x+y][cent_y]){
                        case 1:
                            imp.cnt1++;
                            break;
                        case 2:
                            imp.cnt2++;
                            break;
                        case 3:
                            imp.cnt3++;
                            break;
                    }
                    begin_data[cent_x+y][cent_y] = 0;
                }
            }else if(magic_data[x][0]==3){
                for(int y = 1; y <= magic_data[x][1]; y++){
                    switch (begin_data[cent_x][cent_y-y]){
                        case 1:
                            imp.cnt1++;
                            break;
                        case 2:
                            imp.cnt2++;
                            break;
                        case 3:
                            imp.cnt3++;
                            break;
                    }
                    begin_data[cent_x][cent_y-y] = 0;
                }
            }else if(magic_data[x][0]==4){
                for(int y = 1; y <= magic_data[x][1]; y++){
                    switch (begin_data[cent_x][cent_y+y]){
                        case 1:
                            imp.cnt1++;
                            break;
                        case 2:
                            imp.cnt2++;
                            break;
                        case 3:
                            imp.cnt3++;
                            break;
                    }
                    begin_data[cent_x][cent_y+y] = 0;
                }
            }
            list = imp.ArrayToList(begin_data);
            list = imp.org(list,array_size-1);
            list = imp.pang(list,array_size);
//            list = imp.group(list,size);
            imp.cnt1 += list.get(array_size);
            imp.cnt2 += list.get(array_size+1);
            imp.cnt3 += list.get(array_size+2);
            begin_data = imp.ListToArray(list,size);
        }

        System.out.println(list.get(array_size)+list.get(array_size+1)*2+list.get(array_size+2)*3);
    }
}
