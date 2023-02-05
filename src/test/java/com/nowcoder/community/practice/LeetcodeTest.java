package com.nowcoder.community.practice;/*
 *  @author 张林辉
 *  @version 1.0
 */

import org.junit.jupiter.api.Test;

import java.util.*;

public class LeetcodeTest {


    @Test
    public int[] frequencySort(int[] nums) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)  +1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o1[1]-o2[1];
            }
        });
        for(Map.Entry<Integer, Integer> m : map.entrySet()) {
            queue.add(new int[]{m.getKey(),m.getValue()});
        }
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 0; i < p[1]; i++) {
                list.add(p[0]);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }



}
