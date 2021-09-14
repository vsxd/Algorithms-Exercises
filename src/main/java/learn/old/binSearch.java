package learn.old;

import java.util.HashMap;
import java.util.Map;

public class binSearch {
    public static void main(String[] args) {

    }
}

class Solution704 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target){
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }
}


class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int left = binSearch(nums, target, true);
        int right = binSearch(nums, target, false);
        return new int[]{left, right};
    }

    public int binSearch(int[] nums, int target, boolean lowerBound) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                if (lowerBound) right = mid - 1;
                else left = mid + 1;
            }
        }
        if (lowerBound) {
            if (left >= nums.length || nums[left] != target)
                return -1;
            return left;
        } else {
            if (right < 0 || nums[right] != target)
                return -1;
            return right;
        }
    }
}

