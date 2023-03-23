For a given number ‘N’, write a function to generate all combination of ‘N’ pairs of balanced parentheses.

**Example 1:**  
**Input:** N=2  
**Output:** (()), ()()

**Example 2:**  
**Input:** N=3  
**Output:** ((())), (()()), (())(), ()(()), ()()()

##Solution

This problem follows the Subsets pattern and can be mapped to Permutations. We can follow a similar BFS approach.

Let’s take Example-2 mentioned above to generate all the combinations of balanced parentheses. Following a BFS approach,
we will keep adding open parentheses ( or close parentheses ). At each step we need to keep two things in mind:
* We can’t add more than ‘N’ open parenthesis.
* To keep the parentheses balanced, we can add a close parenthesis ) only when we have already added enough
  open parenthesis (. For this, we can keep a count of open and close parenthesis with every combination.

Following this guideline, let’s generate parentheses for N=3:
1. Start with an empty combination: “”
2. At every step, let’s take all combinations of the previous step and add ( or ) keeping the above-mentioned two rules
   in mind.
3. For the empty combination, we can add ( since the count of open parenthesis will be less than ‘N’. We can’t add )
   as we don’t have an equivalent open parenthesis, so our list of combinations will now be: “(”
4. For the next iteration, let’s take all combinations of the previous set. For “(” we can add another ( to it since
   the count of open parenthesis will be less than ‘N’. We can also add ) as we do have an equivalent open parenthesis,
   so our list of combinations will be: “((”, “()”
5. In the next iteration, for the first combination “((”, we can add another ( to it as the count of open parenthesis
   will be less than ‘N’, we can also add ) as we do have an equivalent open parenthesis. This gives us two new combinations:
   “(((” and “(()”. For the second combination “()”, we can add another ( to it since the count of open parenthesis
   will be less than ‘N’. We can’t add ) as we don’t have an equivalent open parenthesis, so our list of combinations
   will be: “(((”, “(()”, ()("
6. Following the same approach, next we will get the following list of combinations: “((()”, “(()(”, “(())”, “()((”, “()()”
7. Next we will get: “((())”, “(()()”, “(())(”, “()(()”, “()()(”
8. Finally, we will have the following combinations of balanced parentheses: “((()))”, “(()())”, “(())()”, “()(())”, “()()()”
9. We can’t add more parentheses to any of the combinations, so we stop here.