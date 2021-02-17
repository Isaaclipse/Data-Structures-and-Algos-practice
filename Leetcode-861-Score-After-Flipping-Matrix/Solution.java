//Description: We have a two dimensional matrix A where each value is 0 or 1.

// A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

// After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

// Return the highest possible score.

// Example 1:

// Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
// Output: 39
// Explanation:
// Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
// 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

// Note:

//     1 <= A.length <= 20
//     1 <= A[0].length <= 20
//     A[i][j] is 0 or 1.


// SOLUTION:

// Approach 1: Brute Force

// Intuition

// Notice that a 1 in the iiith column from the right, contributes 2i2^i2i to the score.

// Say we are finished toggling the rows in some configuration. Then for each column, (to maximize the score), we'll toggle the column if it would increase the number of 1s.

// We can brute force over every possible way to toggle rows.

// Algorithm

// Say the matrix has R rows and C columns.

// For each state, the transition trans = state ^ (state-1) represents the rows that must be toggled to get into the state of toggled rows represented by (the bits of) state.

// We'll toggle them, and also maintain the correct column sums of the matrix on the side.

// Afterwards, we'll calculate the score. If for example the last column has a column sum of 3, then the score is max(3, R-3), where R-3 represents the score we get from toggling the last column.

// In general, the score is increased by max(col_sum, R - col_sum) * (1 << (C-1-c)), where the factor (1 << (C-1-c)) is the power of 2 that each 1 contributes.

// Note that this approach may not run in the time allotted.

class Solution {
    public int matrixScore(int[][] A) {
        int R = A.length, C = A[0].length;
        int[] colsums = new int[C];
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c)
                colsums[c] += A[r][c];

        int ans = 0;
        for (int state = 0; state < (1<<R); ++state) {
            // Toggle the rows so that after, 'state' represents
            // the toggled rows.
            if (state > 0) {
                int trans = state ^ (state-1);
                for (int r = 0; r < R; ++r) {
                    if (((trans >> r) & 1) > 0) {
                        for (int c = 0; c < C; ++c) {
                            colsums[c] += A[r][c] == 1 ? -1 : 1;
                            A[r][c] ^= 1;
                        }
                    }
                }
            }

            // Calculate the score with the rows toggled by 'state'
            int score = 0;
            for (int c = 0; c < C; ++c)
                score += Math.max(colsums[c], R - colsums[c]) * (1 << (C-1-c));
            ans = Math.max(ans, score);
        }

        return ans;
    }
}

// Complexity Analysis

//     Time Complexity: O(2R∗R∗C)O(2^R * R * C)O(2R∗R∗C), where R,CR, CR,C is the number of rows and columns in the matrix.

//     Space Complexity: O(C)O(C)O(C) in additional space complexity. 