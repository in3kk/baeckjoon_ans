package implementation;

// https://www.acmicpc.net/problem/21608
// 상어 초등학교에는 교실이 하나 있고, 교실은 N×N 크기의 격자로 나타낼 수 있다. 학교에 다니는 학생의 수는 N2명이다. 오늘은 모든 학생의 자리를 정하는 날이다.
// 학생은 1번부터 N2번까지 번호가 매겨져 있고, (r, c)는 r행 c열을 의미한다. 교실의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다.
// 선생님은 학생의 순서를 정했고, 각 학생이 좋아하는 학생 4명도 모두 조사했다. 이제 다음과 같은 규칙을 이용해 정해진 순서대로 학생의 자리를 정하려고 한다.
// 한 칸에는 학생 한 명의 자리만 있을 수 있고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접하다고 한다.
//
//  1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
//  2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
//  3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class implementation00 {//상어초등학교
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int [][] input_data  = new int[size*size][5];
        int [][] seat = new int[size][size];
        for(int x = 0; x < input_data.length; x++){
            for(int y = 0; y < 5; y++){
                input_data[x][y] = sc.nextInt();
            }
        }
        for(int i = 0; i < size*size; i++){
            int max_cnt_x = 0;
            int max_cnt_y = 0;
            int max_cnt = -1;
            int max_null_cnt = -1;

            List<Integer> list = new ArrayList();
            for(int z = 1; z < 5; z++){
                list.add(input_data[i][z]);
            }
            for(int x = 0; x < size; x++){
                for(int y = 0; y < size; y++){
                    if(seat[x][y] != 0){
                        continue;
                    }
                    int null_cnt = 0;
                    int cnt = 0;
                    if(x > 0){
                        if(seat[x-1][y] == 0){
                            null_cnt++;
                        }else if(seat[x-1][y] != 0 && list.contains(seat[x-1][y])){
                            cnt++;
                        }
                    }
                    if(x < size-1){
                        if(seat[x+1][y] == 0){
                            null_cnt++;
                        }else if(seat[x+1][y] != 0 && list.contains(seat[x+1][y])){
                            cnt++;
                        }
                    }
                    if(y > 0){
                        if(seat[x][y-1] == 0){
                            null_cnt++;
                        }else if(seat[x][y-1] != 0 && list.contains(seat[x][y-1])){
                            cnt++;
                        }
                    }
                    if(y < size-1){
                        if(seat[x][y+1] == 0){
                            null_cnt++;
                        }else if(seat[x][y+1] != 0 && list.contains(seat[x][y+1])){
                            cnt++;
                        }
                    }
                    if(max_null_cnt <= null_cnt && max_cnt <= cnt){
                        if((!(max_cnt == cnt && max_null_cnt == null_cnt) && seat[x][y] == 0)){
                            max_cnt_x = x;
                            max_cnt_y = y;
                            max_cnt = cnt;
                            max_null_cnt = null_cnt;
                        }
                    }
                }
            }
            seat[max_cnt_x][max_cnt_y] = input_data[i][0];
        }
        int result = 0;
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                int cnt = 0;
                List<Integer> list = new ArrayList();
                for(int o = 0; o < size*size; o++){
                    if(seat[x][y] == input_data[o][0]){
                        for(int i = 1; i < 5; i++){
                            list.add(input_data[o][i]);
                        }
                        break;
                    }
                }
                if(x > 0){
                    if(list.contains(seat[x-1][y])){
                        cnt++;
                    }
                }
                if(x < size-1){
                    if(list.contains(seat[x+1][y])){
                        cnt++;
                    }
                }
                if(y > 0){
                    if(list.contains(seat[x][y-1])){
                        cnt++;
                    }
                }
                if(y < size-1){
                    if(list.contains(seat[x][y+1])){
                        cnt++;
                    }
                }
                switch(cnt){
                    case 1:
                        result += 1;
                        break;
                    case 2:
                        result += 10;
                        break;
                    case 3:
                        result += 100;
                        break;
                    case 4:
                        result += 1000;
                        break;
                    default:
                        result += 0;
                }
            }
        }
        System.out.println(result);
    }
}
