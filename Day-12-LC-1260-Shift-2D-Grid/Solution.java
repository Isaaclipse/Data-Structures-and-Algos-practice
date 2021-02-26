// 1260. Shift 2D Grid
// Easy

// Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.

// In one shift operation:

//     Element at grid[i][j] moves to grid[i][j + 1].
//     Element at grid[i][n - 1] moves to grid[i + 1][0].
//     Element at grid[m - 1][n - 1] moves to grid[0][0].

// Return the 2D grid after applying shift operation k times.

// Example 1:

// Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
// Output: [[9,1,2],[3,4,5],[6,7,8]]

// Example 2:

// Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
// Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]

// Example 3:

// Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
// Output: [[1,2,3],[4,5,6],[7,8,9]]

 

// Constraints:

//     m == grid.length
//     n == grid[i].length
//     1 <= m <= 50
//     1 <= n <= 50
//     -1000 <= grid[i][j] <= 1000
//     0 <= k <= 100

// Solution
// Approach 1: Simulation

// Intuition

// We are given instructions on how to apply the transformation. Therefore, we could just repeat applying the transformation kkk times.

// Algorithm

// Reading explanations with lots of notation and array indexes can be confusing, and there is a lot of room for misunderstanding. A good first step might be to draw a picture (a rough sketch on a whiteboard would be fine) of each of the 3 cases to be certain that you understand them.

// Element at grid[i][j] moves to grid[i][j + 1].

// Diagram showing case #1.

// Element at grid[i][n - 1] moves to grid[i + 1][0].

// Diagram showing case #2.

// Element at grid[m - 1][n - 1] moves to grid[0][0].

// Diagram showing case #3.

// Then, kkk times, we need to create a new 2D array and follow the given rules to move the values. If we're using Java, we'll also need to then convert the output from a 2D Array to a 2D list.

// Complexity Analysis

//     Time Complexity: O(n⋅m⋅k)O(n \cdot m \cdot k)O(n⋅m⋅k), where the grid size is n⋅mn \cdot mn⋅m and we need to apply the transform kkk times.

//     Space Complexity: O(n⋅m)O(n \cdot m)O(n⋅m). We are creating a new array in each iteration of the loop. We only keep track of at most 2 arrays at a time, though. The rest are garbage collected/ free'd.

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        // Repeat the transform k times.
        for (;k > 0; k--) {
            // We'll write the transform into a new 2D array.
            int[][] newGrid = new int[grid.length][grid[0].length];

            // Case #1: Move everything not in the last column.
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length - 1; col++) {
                    newGrid[row][col + 1] = grid[row][col];
                }
            }

            // Case #2: Move everything in last column, but not last row.
            for (int row = 0; row < grid.length - 1; row++) {
                newGrid[row + 1][0] = grid[row][grid[0].length - 1];
            }

            // Case #3: Move the bottom right.
            newGrid[0][0] = grid[grid.length - 1][grid[0].length - 1];

            // Update grid to be the transformed grid.
            grid = newGrid;
        }

        // Copy the grid into a list for returning.
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : grid) {
            List<Integer> listRow = new ArrayList<>();
            result.add(listRow);
            for (int v : row) listRow.add(v);
        }

        return result;
    }
}