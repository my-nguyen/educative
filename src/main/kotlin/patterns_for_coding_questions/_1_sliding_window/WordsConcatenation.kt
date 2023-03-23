package patterns_for_coding_questions._1_sliding_window

class WordsConcatenation {
    // from educative
    // This problem follows the Sliding Window pattern and has a lot of similarities with Maximum Sum Subarray of Size K.
    // We will keep track of all the words in a HashMap and try to match them in the given string. Here are the set of
    // steps for our algorithm:
    // 1. Keep the frequency of every word in a HashMap.
    // 2. Starting from every index in the string, try to match all the words.
    // 3. In each iteration, keep track of all the words that we have already seen in another HashMap.
    // 4. If a word is not found or has a higher frequency than required, we can move on to the next character in the string.
    // 5. Store the index if we have found all the words.
    fun findWordConcatenation(string: String, words: Array<String>): List<Int>? {
        val frequencies = mutableMapOf<String, Int>()
        for (word in words) {
            val count = frequencies.getOrDefault(word, 0)
            frequencies[word] = count + 1
        }

        val indices = mutableListOf<Int>()
        val wordsCount = words.size
        val wordLength = words[0].length
        // iterate thru each character in string
        for (i in 0..(string.length - wordsCount*wordLength)) {
            val wordsSeen = mutableMapOf<String, Int>()

            // iterate thru each word in words
            for (j in 0 until wordsCount) {
                // get the next word from the string
                val nextWordIndex = i + j*wordLength
                val word = string.substring(nextWordIndex, nextWordIndex + wordLength)

                // break if we don't need this word
                if (!frequencies.containsKey(word)) {
                    break
                }

                // add the word to the 'wordsSeen' map
                val count = wordsSeen.getOrDefault(word, 0)
                wordsSeen[word] = count + 1

                // no need to process further if the word has higher frequency than required
                if (wordsSeen[word]!! > frequencies.getOrDefault(word, 0)) {
                    break
                }

                // store index if we have found all the words
                if (j + 1 == wordsCount) {
                    indices.add(i)
                }
            }
        }
        return indices
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("catfoxcat", "catcatfoxfox")
            val words = arrayOf(arrayOf("cat", "fox"), arrayOf("cat", "fox"))
            for (i in strings.indices) {
                val indices = WordsConcatenation().findWordConcatenation(strings[i], words[i])
                println("string: ${strings[i]}, words: ${words[i].contentToString()}, indices: ${indices}")
            }
        }
    }
}