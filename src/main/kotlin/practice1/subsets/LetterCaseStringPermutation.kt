package practice1.subsets

class LetterCaseStringPermutation {
    fun findLetterCaseStringPermutations(string: String): List<String> {
        return listOf()
    }

    companion object {
        @JvmStatic
        fun main() {
            val strings = arrayOf("ad52", "ab7c")
            for (string in strings) {
                val permutations = LetterCaseStringPermutation().findLetterCaseStringPermutations(string)
                print("string: $string, permutations: ")
                for (permutation in permutations) {
                    print("$permutation, ")
                }
                println()
            }
        }
    }
}