package patterns_for_coding_questions._10_subsets

class StringPermutationsByChangingCase {
    // from educative
    // This problem follows the Subsets pattern and can be mapped to Permutations.
    // Let’s take Example-2 mentioned above to generate all the permutations. Following a BFS approach, we will consider
    // one character at a time. Since we need to preserve the character sequence, we can start with the actual string
    // and process each character (i.e., make it upper-case or lower-case) one by one:
    // 1. Starting with the actual string: "ab7c"
    // 2. Processing the first character (‘a’), we will get two permutations: "ab7c", "Ab7c"
    // 3. Processing the second character (‘b’), we will get four permutations: "ab7c", "Ab7c", "aB7c", "AB7c"
    // 4. Since the third character is a digit, we can skip it.
    // 5. Processing the fourth character (‘c’), we will get a total of eight permutations: "ab7c", "Ab7c", "aB7c",
    //    "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
    // Let’s analyze the permutations in the 3rd and the 5th step. How can we generate the permutations in the 5th step
    // from the permutations in the 3rd step?
    // If we look closely, we will realize that in the 5th step, when we processed the new character (‘c’), we took all
    // the permutations of the previous step (3rd) and changed the case of the letter (‘c’) in them to create four new
    // permutations.
    fun findLetterCaseStringPermutations(string: String): List<String> {
        val permutations = mutableListOf<String>()
        if (string.isEmpty()) {
            return permutations
        }

        // the original string is one permutation
        permutations.add(string)
        // iterate thru each character in string
        for (i in string.indices) {
            // println("i: $i")
            // only process characters and skip digits
            if (string[i].isLetter()) {
                val size = permutations.size
                // println("at $i is letter, permutations size: $size")
                // for each permutation in the current list, create a new permutation by changing the case of the current character
                for (j in 0 until size) {
                    // convert string to a mutable array of characters
                    val permutation = permutations[j].toCharArray()
                    // println("j=$j, string-perm: ${permutations[j]}, array-perm: ${permutation.contentToString()}")
                    // convert current character to lower case or upper case
                    if (permutation[i].isUpperCase()) {
                        permutation[i] = permutation[i].lowercaseChar()
                    } else {
                        permutation[i] = permutation[i].uppercaseChar()
                    }
                    // println("post perm: ${permutation.contentToString()}")
                    // convert character array back to string and save it as a new permutation so the next character
                    // will have to account for this new permutation
                    permutations.add(permutation.joinToString(""))
                }
            }
        }
        return permutations
    }

    companion object {
        @JvmStatic
        fun main() {
            val strings = arrayOf("ad52", "ab7c")
            for (string in strings) {
                val permutations = StringPermutationsByChangingCase().findLetterCaseStringPermutations(string)
                print("string: $string, permutations: ")
                for (permutation in permutations) {
                    print("$permutation, ")
                }
                println()
            }
        }
    }
}