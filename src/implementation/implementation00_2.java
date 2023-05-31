package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class implementation00_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] seat = new int[size][size];
        int[][] input_data =new int[size*size][5];
        for(int x = 0; x < size*size; x++){
            String data = br.readLine();
            for(int y = 0; y < 5; y++){
                input_data[x][y] = Integer.parseInt(data.split(" ")[y]);
            }
        }
        int fr_cnt;//좋아하는 학생수 카운트
        int emp_cnt;//인접한 비어있는 자리수 카운트
        int sel_x;
        int sel_y;
        List<Integer> list = new ArrayList<>();
        for(int x = 0; x < input_data.length; x++){
            fr_cnt = 0;
            emp_cnt = 0;
            sel_x = -1;
            sel_y = -1;
            list.clear();
            for(int i = 1; i < 5; i++){
                list.add(input_data[x][i]);
            }
            for(int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    int tmp_fr_cnt = 0;
                    int tmp_emp_cnt = 0;
                    if (seat[y][z] == 0) {
                        if (y < size - 1) {
                            if (seat[y + 1][z] == 0) {
                                tmp_emp_cnt++;
                            } else if (list.contains(seat[y + 1][z])) {
                                tmp_fr_cnt++;
                            }
                        }
                        if (y > 0) {
                            if (seat[y - 1][z] == 0) {
                                tmp_emp_cnt++;
                            } else if (list.contains(seat[y - 1][z])) {
                                tmp_fr_cnt++;
                            }
                        }
                        if (z < size - 1) {
                            if (seat[y][z + 1] == 0) {
                                tmp_emp_cnt++;
                            } else if (list.contains(seat[y][z + 1])) {
                                tmp_fr_cnt++;
                            }
                        }
                        if (z > 0) {
                            if (seat[y][z - 1] == 0) {
                                tmp_emp_cnt++;
                            } else if (list.contains(seat[y][z - 1])) {
                                tmp_fr_cnt++;
                            }
                        }
                        if(sel_x == -1 && sel_y == -1){
                            sel_x = y;
                            sel_y = z;
                            fr_cnt = tmp_fr_cnt;
                            emp_cnt = tmp_emp_cnt;
                        } else if (tmp_fr_cnt > fr_cnt) {
                            sel_x = y;
                            sel_y = z;
                            fr_cnt = tmp_fr_cnt;
                            emp_cnt = tmp_emp_cnt;
                        } else if (tmp_fr_cnt == fr_cnt && tmp_emp_cnt > emp_cnt) {
                            sel_x = y;
                            sel_y = z;
                            fr_cnt = tmp_fr_cnt;
                            emp_cnt = tmp_emp_cnt;
                        }
                    }
                }
            }
            seat[sel_x][sel_y] = input_data[x][0];
        }
        int score = 0;
        int cnt;
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                cnt = 0;
                for(int z = 0; z < size * size; z++){
                    if(seat[x][y] == input_data[z][0]){
                        list.clear();
                        for(int i = 1; i < 5; i++){
                            list.add(input_data[z][i]);
                        }
                    }
                }
                if (x < size - 1) {
                    if (list.contains(seat[x + 1][y])) {
                        cnt++;
                    }
                }
                if (x > 0) {
                    if (list.contains(seat[x - 1][y])) {
                        cnt++;
                    }
                }
                if (y < size - 1) {
                    if (list.contains(seat[x][y + 1])) {
                        cnt++;
                    }
                }
                if (y > 0) {
                    if (list.contains(seat[x][y - 1])) {
                        cnt++;
                    }
                }
                switch (cnt){
                    case 0:
                        score += 0;
                        break;
                    case 1:
                        score += 1;
                        break;
                    case 2:
                        score += 10;
                        break;
                    case 3:
                        score += 100;
                        break;
                    case 4:
                        score += 1000;
                        break;
                }
            }
        }
        System.out.println(score);
    }
}
