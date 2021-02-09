/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

/*
Solution Approach 2: Using Binary Search

Algorithm

We can apply Binary Search to find the given number. We start with the mid number. We pass that number to the guessguessguess function. If it returns a -1, it implies that the guessed number is larger than the required one. Thus, we use Binary Search for numbers lower than itself. Similarly, if it returns a 1, we use Binary Search for numbers higher than itself.

Complexity Analysis

    Time complexity : O(log⁡2n)O\big(\log_2 n\big)O(log2​n). Binary Search is used.
    Space complexity : O(1)O(1)O(1). No extra space is used. 
*/

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0)
                return mid;
            else if (res < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }
}

/* Python attempt
The guess API is already defined for you.
@param num, your guess
@return -1 if my number is lower, 1 if my number is higher, otherwise return 0
# def guess(num):
# 1. Guess which number was picked
# 2. if the guess is wrong, then tell the user whether the numner is higher or lower
# 3. call int guess(int num) this returns 3 possibles results
#       1) -1 if The number I picked is lower than your guess 
#           (i.e. pick < num).
#       2) 1 if The number I picked is higher than your guess (i.e. pick > num).
#       0: 0 The number I picked is equal to your guess (i.e. pick == num).
# 4. return the number that the user picked
# ex: Example 1:

#Input: n = 10, pick = 6
#Output: 6

#class Solution(object):
 #   def guessNumber(self, n):
  #      """
   #     :type n: int
    #    :rtype: int
     #   """
      #  if self < n:
       #     return -1
    
    #    elif self > n:
     #       return 1
        
      #  else: 
       #     return 0
*/
