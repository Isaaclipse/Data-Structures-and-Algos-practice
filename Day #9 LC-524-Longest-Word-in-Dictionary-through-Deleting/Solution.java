// DESCRIPTION:  Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

// Example 1:

// Input:
// s = "abpcplea", d = ["ale","apple","monkey","plea"]

// Output: 
// "apple"

// Example 2:

// Input:
// s = "abpcplea", d = ["a","b","c"]

// Output: 
// "a"

// Note:

//     All the strings in the input will only contain lower-case letters.
//     The size of the dictionary won't exceed 1,000.
//     The length of all the strings in the input won't exceed 1,000.

// Solution
// Approach 1: Brute Force

// Algorithm

// The idea behind this approach is as follows. We create a list of all the possible strings that can be formed by deleting one or more characters from the given string sss. In order to do so, we make use of a recursive function generate(s, str, i, l) which creates a string by adding and by removing the current character(ithi^{th}ith) from the string sss to the string strstrstr formed till the index iii. Thus, it adds the ithi^{th}ith character to strstrstr and calls itself as generate(s, str + s.charAt(i), i + 1, l). It also omits the ithi^{th}ith character to strstrstr and calls itself as generate(s, str, i + 1, l).

// Thus, at the end the list lll contains all the required strings that can be formed using sss. Then, we look for the strings formed in lll into the dictionary available to see if a match is available. Further, in case of a match, we check for the length of the matched string to maximize the length and we also take care to consider the lexicographically smallest string in case of length match as well.

public class Solution {
    public String findLongestWord(String s, List < String > d) {
        HashSet < String > set = new HashSet < > (d);
        List < String > l = new ArrayList < > ();
        generate(s, "", 0, l);
        String max_str = "";
        for (String str: l) {
            if (set.contains(str))
                if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
                    max_str = str;
        }
        return max_str;
    }
    public void generate(String s, String str, int i, List < String > l) {
        if (i == s.length())
            l.add(str);
        else {
            generate(s, str + s.charAt(i), i + 1, l);
            generate(s, str, i + 1, l);
        }
    }
}