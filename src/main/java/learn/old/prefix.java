package learn.old;

import java.util.HashMap;
import java.util.Map;

public class prefix {
}

class Solution724 {
    public int pivotIndex(int[] nums) {
        int presum = 0;
        //数组的和
        for (int x : nums) {
            presum += x;
        }
        int leftsum = 0;
        for (int i = 0; i < nums.length; ++i) {
            //发现相同情况
            if (leftsum == presum - nums[i] - leftsum) {
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
    }
}

class Solution560 {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int presum = 0, count = 0;
        m.put(0, 1);

        for (int x : nums) {
            presum += x;
            if (m.containsKey(presum - k))
                count += m.get(presum - k);
            m.put(presum, m.getOrDefault(presum, 0) + 1);
        }
        return count;
    }
}

class Solution1248 {
    public int numberOfSubarrays1(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);

        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] & 1;
            if (m.containsKey(count - k))
                res += m.get(count - k);
            m.put(count, m.getOrDefault(count, 0) + 1);
        }
        return res;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int[] m = new int[nums.length + 1];
        m[0] = 1;

        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) count++;
            if (count - k >= 0)
                res += m[count - k];
            m[count]++;
        }
        return res;
    }
}

class Solution974 {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        int sum = 0, res = 0;

        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            int key = (sum % K + K) % K; // 处理负数情况
            if (m.containsKey(key))
                res += m.get(key);
            m.put(key, m.getOrDefault(key, 0) + 1);
        }
        return res;
    }
}


// https://leetcode-cn.com/problems/continuous-subarray-sum/
class Solution523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1); // 当[0,1]满足时，key = 0, i=1, i-(-1) = 2 >= 2 才能满足
        int presum = 0;

        for (int i = 0; i < nums.length; i++) {
            presum += nums[i];
            int key = k == 0 ? presum : presum % k;//细节1，防止 k 为 0 的情况
            if (m.containsKey(key)) {
                if (i - m.get(key) >= 2) {
                    return true;
                }
                continue; //因为我们需要保存最小索引，当已经存在时则不用再次存入，不然将索引值更新变大;
            }
            m.put(key, i);
        }
        return false;
    }
}