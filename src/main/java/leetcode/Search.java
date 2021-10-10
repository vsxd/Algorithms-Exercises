package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Search {

}

class numIslands_dfs {
    private int size_x;
    private int size_y;

    private void dfs(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= size_x || y >= size_y) return;

        if (grid[x][y] == 1) {
            grid[x][y] = 0;
            dfs(grid, x - 1, y);
            dfs(grid, x + 1, y);
            dfs(grid, x, y - 1);
            dfs(grid, x, y + 1);
        }

    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;

        int res = 0;
        size_x = grid.length;
        size_y = grid[0].length;
        for (int x = 0; x < size_x; x++) {
            for (int y = 0; y < size_y; y++) {
                if (grid[x][y] == 1) {
                    res++;
                    dfs(grid, x, y);
                }
            }
        }
        return res;
    }
}

class numIslands_bfs {
    private int size_x;
    private int size_y;

    private void bfs(char[][] grid, int x, int y) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(x * size_y + y);
        while (!q.isEmpty()) {
            int item = q.poll();
            int ix = item / size_y;
            int iy = item % size_y;
            grid[ix][iy] = '0';
            if (ix - 1 >= 0 && grid[ix - 1][iy] == '1') q.offer((ix - 1) * size_y + iy);
            if (ix + 1 < size_x && grid[ix + 1][iy] == '1') q.offer((ix + 1) * size_y + iy);
            if (iy - 1 >= 0 && grid[ix][iy - 1] == '1') q.offer(ix * size_y + iy - 1);
            if (iy + 1 < size_y && grid[ix][iy + 1] == '1') q.offer(ix * size_y + iy + 1);
        }

    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;

        int res = 0;
        size_x = grid.length;
        size_y = grid[0].length;
        for (int x = 0; x < size_x; x++) {
            for (int y = 0; y < size_y; y++) {
                if (grid[x][y] == '1') {
                    res++;
                    bfs(grid, x, y);
                }
            }
        }
        return res;
    }
}

class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int prev = nums[0];
        int hIdx = -1;
        for (int i = 1; i < nums.length; i++) {
            if (prev > nums[i]) {
                hIdx = i;
                break;
            }
            prev = nums[i];
        }

        int res = -1;
        if (nums[0] < target) {
           res = binarySearch(nums, target, 0, hIdx-1);
        } else {
            res = binarySearch(nums, target, hIdx, nums.length-1);
        }
        return res;
    }

    private int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else if (nums[mid] > target) right = mid - 1;
            else return mid;
        }
        return -1;
    }
}
