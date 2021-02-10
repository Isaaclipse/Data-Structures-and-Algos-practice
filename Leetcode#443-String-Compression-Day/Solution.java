class Solution {
    public int compress(char[] chars) {
        int index = 0;
        int i = 0;
        while (i < chars.length) {
            int j = i;
            while (j < chars.length && chars[j] == chars[i]) {
                j++;
            }
            
            chars [index++] = chars[i];
            if (j - i > 1) {
                String count = j - i + "";
                for (char c: count.toCharArray()) {
                    chars[index++] = c;
                    
                }
            }
            
            i = j;
        }
        
        return index;
    }
}

//Runtime/Time complexity: O(n) where n is number of characters in string
// Reason: We're iterating through the entire length of the characters, 
//  using a nested while loop to move the j pointer further than
//          the i pointer (walking through the string once)
// Space Complexity: O(1)