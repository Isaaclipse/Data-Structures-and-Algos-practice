// 322. Coin Change
// Medium

// You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

// You may assume that you have an infinite number of each kind of coin.

 

// Example 1:

// Input: coins = [1,2,5], amount = 11
// Output: 3
// Explanation: 11 = 5 + 5 + 1

// Example 2:

// Input: coins = [2], amount = 3
// Output: -1

// Example 3:

// Input: coins = [1], amount = 0
// Output: 0

// Example 4:

// Input: coins = [1], amount = 1
// Output: 1

// Example 5:

// Input: coins = [1], amount = 2
// Output: 2

 

// Constraints:

//     1 <= coins.length <= 12
//     1 <= coins[i] <= 231 - 1
//     0 <= amount <= 104

// Summary

// This article is for intermediate users. It introduces the following ideas: Backtracking, Dynamic programming.
// Solution
// Approach #1 (Brute force) [Time Limit Exceeded]

// Intuition

// The problem could be modeled as the following optimization problem : min⁡x∑i=0n−1xisubject to∑i=0n−1xi∗ci=S \min_{x} \sum_{i=0}^{n - 1} x_i \\ \text{subject to} \sum_{i=0}^{n - 1} x_i*c_i = S minx​∑i=0n−1​xi​subject to∑i=0n−1​xi​∗ci​=S

// , where SSS is the amount, cic_ici​ is the coin denominations, xix_ixi​ is the number of coins with denominations cic_ici​ used in change of amount SSS. We could easily see that xi=[0,Sci]x_i = [{0, \frac{S}{c_i}}]xi​=[0,ci​S​].

// A trivial solution is to enumerate all subsets of coin frequencies [x0…xn−1][x_0\dots x_{n - 1}][x0​…xn−1​] that satisfy the constraints above, compute their sums and return the minimum among them.

// Algorithm

// To apply this idea, the algorithm uses backtracking technique to generate all combinations of coin frequencies [x0…xn−1][x_0\dots x_{n-1}][x0​…xn−1​] in the range ([0,Sci])([{0, \frac{S}{c_i}}])([0,ci​S​]) which satisfy the constraints above. It makes a sum of the combinations and returns their minimum or −1-1−1 in case there is no acceptable combination.

// Approach #2 (Dynamic programming - Top down) [Accepted]

// Intuition

// Could we improve the exponential solution above? Definitely! The problem could be solved with polynomial time using Dynamic programming technique. First, let's define:

//     F(S)F(S)F(S) - minimum number of coins needed to make change for amount SSS using coin denominations [c0…cn−1][{c_0\ldots c_{n-1}}][c0​…cn−1​]

// We note that this problem has an optimal substructure property, which is the key piece in solving any Dynamic Programming problems. In other words, the optimal solution can be constructed from optimal solutions of its subproblems. How to split the problem into subproblems? Let's assume that we know F(S)F(S)F(S) where some change val1,val2,…val_1, val_2, \ldotsval1​,val2​,… for SSS which is optimal and the last coin's denomination is CCC. Then the following equation should be true because of optimal substructure of the problem:

// F(S)=F(S−C)+1 F(S) = F(S - C) + 1 F(S)=F(S−C)+1

// But we don't know which is the denomination of the last coin CCC. We compute F(S−ci)F(S - c_i)F(S−ci​) for each possible denomination c0,c1,c2…cn−1c_0, c_1, c_2 \ldots c_{n -1}c0​,c1​,c2​…cn−1​ and choose the minimum among them. The following recurrence relation holds:

// F(S)=min⁡i=0...n−1F(S−ci)+1subject to S−ci≥0 F(S) = \min_{i=0 ... n-1} { F(S - c_i) } + 1 \\ \text{subject to} \ S-c_i \geq 0 \\ F(S)=mini=0...n−1​F(S−ci​)+1subject to S−ci​≥0

// F(S)=0,whenS=0F(S)=−1,whenn=0 F(S) = 0 , \text{when} S = 0 \\ F(S) = -1 , \text{when} n = 0 F(S)=0,whenS=0F(S)=−1,whenn=0

// Recursion tree for finding coin change of amount 6 with coin denominations {1,2,3}.

// https://assets.leetcode.com/static_assets/media/original_images/322_coin_change_tree.png

// In the recursion tree above, we could see that a lot of subproblems were calculated multiple times. For example the problem F(1)F(1)F(1) was calculated 131313 times. Therefore we should cache the solutions to the subproblems in a table and access them in constant time when necessary

// Algorithm

// The idea of the algorithm is to build the solution of the problem from top to bottom. It applies the idea described above. It use backtracking and cut the partial solutions in the recursive tree, which doesn't lead to a viable solution. Тhis happens when we try to make a change of a coin with a value greater than the amount SSS. To improve time complexity we should store the solutions of the already calculated subproblems in a table.

// Complexity Analysis

//     Time complexity : O(S∗n)O(S*n)O(S∗n). where S is the amount, n is denomination count. In the worst case the recursive tree of the algorithm has height of SSS and the algorithm solves only SSS subproblems because it caches precalculated solutions in a table. Each subproblem is computed with nnn iterations, one by coin denomination. Therefore there is O(S∗n)O(S*n)O(S∗n) time complexity.

//     Space complexity : O(S)O(S)O(S), where SSS is the amount to change We use extra space for the memoization table.

public class Solution {

  public int coinChange(int[] coins, int amount) {
    if (amount < 1) return 0;
    return coinChange(coins, amount, new int[amount]);
  }

  private int coinChange(int[] coins, int rem, int[] count) {
    if (rem < 0) return -1;
    if (rem == 0) return 0;
    if (count[rem - 1] != 0) return count[rem - 1];
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = coinChange(coins, rem - coin, count);
      if (res >= 0 && res < min)
        min = 1 + res;
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }
}