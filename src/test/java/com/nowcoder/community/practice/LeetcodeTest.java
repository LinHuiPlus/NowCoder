package com.nowcoder.community.practice;/*
 *  @author 张林辉
 *  @version 1.0
 */

import org.junit.jupiter.api.Test;

import java.util.*;

public class LeetcodeTest {
    @Test
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int l = 2, r = n;
        boolean f = true;
        /*for (int i = 0; i < n; i++) {
			int tt = nums[i];
			System.out.print(tt+":");
			while(tt != 0){
				System.out.print(tt&1);
				tt = tt>>1;
			}
			System.out.println();
		}
        */
        while(l <= r){
            int m = l + ((r-l) >> 1);
            Map<Integer, Integer> map = new HashMap<>();
            f = true;
            for (int i = 0; i < m; i++) {
                int t = nums[i], count = 0;
                while(t != 0){
                    if((t & 1) == 1){
                        map.put(count, map.getOrDefault(count, 0)+1);
                        if(map.get(count) >= 2){
                            f = false;
                            //break;
                        }
                    }
                    count++;
                    t = t >> 1;
                }
                for(Map.Entry<Integer, Integer> mm: map.entrySet()){
                    if(mm.getValue() >= 2){
                        f = false;
                        break;
                    }
                }
            }
            if(f){
                l = m + 1;
                //System.out.println(m);
                continue;
            }

            for (int i = m; i < n; i++) {
                int a = nums[i-m], b = nums[i], count = 0;
                f = true;
                while(a != 0){
                    if((a & 1) == 1){
                        map.put(count, map.getOrDefault(count, 0)-1);
                    }
                    count++;
                    a = a >> 1;
                }
                count = 0;
                while(b != 0){
                    if((b & 1) == 1){
                        map.put(count, map.getOrDefault(count, 0)+1);
                        if(map.get(count) >= 2){
                            f = false;
                        }
                    }
                    count++;
                    b = b >> 1;
                }
                for(Map.Entry<Integer, Integer> mm: map.entrySet()){
                    if(mm.getValue() >= 2){
                        f = false;
                        break;
                    }
                }
                if(f){
                    //System.out.println(m + "," +i);
                    break;
                }
            }
            if(f){
                l = m + 1;
                //System.out.println("this:"+m);
            }
            else{
                r = m - 1;
            }
        }
        if(l == 2 && f){
            return 1;
        }
        return r;
    }


    @Test
    public int numberOfWays(int startPos, int endPos, int k) {
        for (int i = 0; i < k; i++) {

        }
        return 0;
    }


    @Test
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        int l = 1, r = n;
        while(l <= r){
            int mid = l + ((r-l) >> 1);

            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            Queue<Integer> queue1 = new LinkedList<>();
            long bu = (long) 1e15, sum = 0;
            for (int i = 0; i < mid; i++) {
                sum += runningCosts[i];
                queue.add(chargeTimes[i]);
                queue1.add(chargeTimes[i]);
                bu = Math.min(bu, sum+queue.peek());
            }

            for (int i = mid; i < n; i++) {
                sum += runningCosts[i];
                sum -= runningCosts[i-mid];
            }
        }
        return 0;
    }
}
