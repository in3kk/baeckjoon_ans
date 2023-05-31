package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class graph01 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] data = br.readLine().split(" ");
        int point = Integer.parseInt(data[0]);
        int line = Integer.parseInt(data[1]);
        int root = Integer.parseInt(data[2]);
        int [][] input_data = new int[line][2];
        for(int x = 0; x < line; x++){
            data = br.readLine().split(" ");
            input_data[x][0] = Integer.parseInt(data[0]);
            input_data[x][1] = Integer.parseInt(data[1]);
        }
        List<Integer> dfs_list = new ArrayList<Integer>();
        List<Integer> bfs_list = new ArrayList<Integer>();
        dfs_list.add(root);
        int crt = root;
        int cand;
        boolean chng_chk;
        //dfs
        while(true){
            chng_chk = false;
            cand = 1001;
            for(int[] input_data2 : input_data){
                if(crt == input_data2[0] && !dfs_list.contains(input_data2[1]) && input_data2[1]<cand){
                    cand = input_data2[1];
                    chng_chk = true;
                }else if(crt == input_data2[1] && !dfs_list.contains(input_data2[0]) && input_data2[0]<cand){
                    cand = input_data2[0];
                    chng_chk = true;
                }
            }
            if(!chng_chk && dfs_list.indexOf(crt) > 1){
                crt = dfs_list.get(dfs_list.indexOf(crt)-1);
            }else if(!chng_chk && dfs_list.indexOf(crt) <= 1 ){
                break;
            }else if(chng_chk){
                crt = cand;
                dfs_list.add(cand);
            }
        }
        for(Integer i : dfs_list){
            System.out.print(i+" ");
        }System.out.println();

        //bfs
        bfs_list.add(root);
        crt = root;
        while(true){
            cand = 1001;
            chng_chk = false;
            for(int[] input_data2 : input_data){
                if(crt == input_data2[0] && !bfs_list.contains(input_data2[1]) && input_data2[1] < cand){
                    cand = input_data2[1];
                    chng_chk = true;
                }else if(crt == input_data2[1] && !bfs_list.contains(input_data2[0]) && input_data2[0] <cand){
                    cand = input_data2[0];
                    chng_chk = true;
                }
            }
            if(chng_chk){
                bfs_list.add(cand);
            }else if(!chng_chk && bfs_list.indexOf(crt) < bfs_list.size()-1){
                crt = bfs_list.get(bfs_list.indexOf(crt)+1);
            }else{
                break;
            }
        }
        for(Integer i : bfs_list){
            System.out.print(i+" ");
        }System.out.println();
    }
}
