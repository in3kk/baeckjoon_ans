package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class implementation01 {//상어 마법사
    static int cnt1 = 0;
    static int cnt2 = 0;
    static int cnt3 = 0;
    public List<Integer> org2(List<Integer> list){
        for(int i = list.size()-1; i >= 0 ; i--){
            if(list.get(i) == 0){
                boolean chk = false;
                for(int o = i; o >= 0; o--){
                    if(list.get(o) != 0){
                        chk = true;
                        break;
                    }
                }
                if(chk){
                    for(int o = list.size()-i; o > 0; o--){
                        list.set(o,list.get(o-1));
                    }
                    list.set(0,0);
                }
            }
        }
        return list;
    }
    public List<Integer> dup_remove(List<Integer> list){
        int dup_cnt=1;
        for(int x = list.size()-2; x >= 0; x--){
            if(list.get(x) != 0 && list.get(x+1) == list.get(x)){
                dup_cnt++;
            }else if(dup_cnt >= 4){
                switch (list.get(x+1)){
                    case 1:
                        this.cnt1 += dup_cnt;
                        break;
                    case 2:
                        cnt2 += dup_cnt;
                        break;
                    case 3:
                        cnt3 += dup_cnt;
                        break;
                }
                for(int y = x+1; y <= x+dup_cnt; y++ ){
                    list.set(y,0);
                }
                dup_cnt = 1;
            }else{
                dup_cnt = 1;
            }
            System.out.println(cnt1+" "+cnt2+" "+cnt3);
        }
        list = org2(list);
        return list;
    }
    public List<Integer> group(List<Integer> list){
        List<Integer> list2 = list;
        int dup_cnt=1;
        int group_cnt=1;
        for(int x = list.size()-2; x >= 0; x--){
            if(list.get(x) != 0 && list.get(x+1)==list.get(x)){
                dup_cnt++;
            }else if(list.get(x) != 0){
                if(list.size()-group_cnt >= 0){
                    list2.set(list.size()-group_cnt, dup_cnt);
                }else {
                    break;
                }
                if(list.size()-group_cnt-1 >= 0){
                    list2.set(list.size()-group_cnt-1, list.get(x+1));
                }else {
                    break;
                }
                dup_cnt=1;
                group_cnt += 2;
            }
        }
        for(int x = list.size()-group_cnt; x >= 0; x--){
            list2.set(x,0);
        }
        return list2;
    }
    public int[][] org(int[][] data){
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
        list = org2(list);
        list = dup_remove(list);
        list = group(list);
        int cnt = 0;
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
    public static void main(String[] args) {
        implementation01 imp = new implementation01();
        Scanner sc = new Scanner(System.in);
        String f_line = sc.nextLine();
        int size = Integer.parseInt(f_line.split(" ")[0]);
        int magic_cnt = Integer.parseInt(f_line.split(" ")[1]);
        int[][] magic_data = new int[magic_cnt][2];
        int[][] begin_data = new int[size][size];
        for (int x = 0; x < size; x++) {//초기 구슬 정보 입력
            String data_line = sc.nextLine();
            for (int y = 0; y < size; y++) {
                begin_data[x][y] = Integer.parseInt(data_line.split(" ")[y]);
            }
        }
        for(int x = 0; x < magic_cnt; x++){//마법 정보 입력
            String magic_line = sc.nextLine();
            magic_data[x][0] = Integer.parseInt(magic_line.split(" ")[0]);
            magic_data[x][1] = Integer.parseInt(magic_line.split(" ")[1]);
            System.out.println(magic_line.split(" ")[0]+" "+magic_line.split(" ")[1]);
            System.out.println(magic_line);
        }
        sc.close();

        int cent_x = (size-1)/2;
        int cent_y = (size-1)/2;

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

            begin_data = imp.org(begin_data);
        }
        for(int x = 0; x < size; x++){
            for(int y = 0; y< size; y++){
                System.out.print(begin_data[x][y]+" ");
            }
            System.out.println();
        }
        System.out.println(imp.cnt1+" "+imp.cnt2+" "+imp.cnt3);
    }
}
