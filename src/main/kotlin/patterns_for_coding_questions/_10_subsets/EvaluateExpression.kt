package patterns_for_coding_questions._10_subsets

class EvaluateExpression {
    fun diffWaysToEvaluateExpression(string: String): List<Int> {
        // return solution(string)
        return memoization(string)
    }

    // from educative
    // This problem follows the patterns_for_coding_questions.Subsets pattern and can be mapped to Balanced Parentheses. We can follow a similar BFS approach.
    // Let’s take Example-1 mentioned above to generate different ways to evaluate the expression.
    // 1. We can iterate through the expression character-by-character.
    // 2. We can break the expression into two halves whenever we get an operator (+, -, *).
    // 3. The two parts can be calculated by recursively calling the function.
    // 4. Once we have the evaluation results from the left and right halves, we can combine them to produce all results.
    private fun solution(string: String): List<Int> {
        val ways = mutableListOf<Int>()
        // base case: if the input string is a number, parse and add it to output
        if (!string.contains("+") && !string.contains("-") && !string.contains("*")) {
            ways.add(string.toInt())
        } else {
            for (i in string.indices) {
                val c = string[i]
                if (!c.isDigit()) {
                    // break the equation here into two parts and make recursively calls
                    val left = solution(string.substring(0, i))
                    val right = solution(string.substring(i + 1))
                    for (l in left) {
                        for (r in right) {
                            when (c) {
                                '+' -> ways.add(l + r)
                                '-' -> ways.add(l - r)
                                '*' -> ways.add(l * r)
                            }
                        }
                    }
                }
            }
        }
        return ways
    }

    // from educative
    // The problem has overlapping subproblems, as our recursive calls can be evaluating the same sub-expression
    // multiple times. To resolve this, we can use memoization and store the intermediate results in a HashMap. In each
    // function call, we can check our map to see if we have already evaluated this sub-expression before. Here is the
    // memoized version of our algorithm; please see highlighted changes:
    private val map = mutableMapOf<String, List<Int>>()
    fun memoization(string: String): List<Int> {
        if (map.containsKey(string)) {
            return map[string]!!
        }

        val ways = mutableListOf<Int>()
        // base case: if the input string is a number, parse and add it to output
        if (!string.contains("+") && !string.contains("-") && !string.contains("*")) {
            ways.add(string.toInt())
        } else {
            for (i in string.indices) {
                val c = string[i]
                if (!c.isDigit()) {
                    // break the equation here into two parts and make recursively calls
                    val left = memoization(string.substring(0, i))
                    val right = memoization(string.substring(i + 1))
                    for (l in left) {
                        for (r in right) {
                            when (c) {
                                '+' -> ways.add(l + r)
                                '-' -> ways.add(l - r)
                                '*' -> ways.add(l * r)
                            }
                        }
                    }
                }
            }
        }
        map[string] = ways
        return ways
    }

    companion object {
        @JvmStatic
        fun main() {
            val strings = arrayOf("1+2*3", "2*3-4-5")
            for (string in strings) {
                val ways = EvaluateExpression().diffWaysToEvaluateExpression(string)
                println("string: $string, ways: $ways")
            }
        }
    }
}