package application;import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PalindromeQueue {

    // Method to check if the queue is a palindrome
    public static boolean isPalindrome(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return true;  // An empty queue is considered a palindrome
        }

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> tempQueue = new LinkedList<>();  // Temporary queue to store original data

        // Transfer elements from the original queue to the stack and tempQueue
        while (!queue.isEmpty()) {
            int current = queue.poll();
            stack.push(current);
            tempQueue.add(current);
        }

        // Restore the original queue from tempQueue and prepare for palindrome check
        boolean isPalindrome = true;
        while (!tempQueue.isEmpty()) {
            int original = tempQueue.poll();
            int reversed = stack.pop();
            queue.add(original);  // Restore the original queue

            if (original != reversed) {
                isPalindrome = false;
                break;
            }
        }

        return isPalindrome;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(3);
        queue.add(17);
        queue.add(9);
        queue.add(17);
        queue.add(31);

        System.out.println("Is the queue a palindrome? " + isPalindrome(queue));  // Should return true
        System.out.println("Original queue restored: " + queue);  // Check if the queue is restored
    }
}
