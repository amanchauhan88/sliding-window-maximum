
// sliding window maximum

import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            // Remove elements from the deque that are out of the window
            if (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            
            // Remove elements from the back of the deque that are smaller than the current element
            // because they are not useful (they will never be the max for this window or any future window)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            // Add the current element's index to the deque
            deque.offer(i);
            
            // If we have processed at least 'k' elements, record the maximum (front of the deque)
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peek()];
            }
        }
        
        return result;
    }
}