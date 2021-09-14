package leetcode;

import java.util.HashMap;
import java.util.Map;

public class HashTable {
}

class twoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target-num)){
                return new int[] {i, map.get(target-num)};
            } else {
                map.put(num, i);
            }
        }
        return null;
    }
}
