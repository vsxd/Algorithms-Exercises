package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Array {
}

class findKthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> (b - a));
        for (int num : nums) {
            heap.add(num);
        }
        for (int i = 0; i < k - 1; i++) heap.poll();
        return heap.peek();
    }

    private void swim(){
    }

}


class threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            // 去重 eg. [0,0,0,0]
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 在[i+1, len-1]中寻找
            // 由于nums已经排序，所以必定是[negative..0..positive]
            int first = nums[i];
            int left = i+1;
            int right = nums.length-1;

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum + first == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重
                    while (left < right && nums[left] == nums[++left]){}
                    while (left < right && nums[right] == nums[--right]){}
                } else if (sum + first < 0) {
                    left++;
                } else if (sum + first > 0) {
                    right--;
                }
            }
        }
        return result;
    }
}