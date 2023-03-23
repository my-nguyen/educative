package patterns_for_coding_questions._1_sliding_window

class StringAnagrams {
    fun findStringAnagrams(string: String, pattern: String): List<Int> {
        val counts = IntArray(26);
        for (c in pattern) {
            val index = c - 'a'
            counts[index]++
        }

        val list = mutableListOf<Int>()
        for (i in 0..(string.length-pattern.length)) {
            val c = string[i]
            val index = c - 'a'
            if (counts[index] > 0) {
                val substring = string.substring(i, i+pattern.length)
                val clone = counts.clone()
                if (isAnagram(substring, clone)) {
                    list.add(i)
                }
            }
        }
        return list
    }

    private fun isAnagram(string: String, counts: IntArray): Boolean {
        for (c in string) {
            val index = c - 'a'
            if (counts[index] == 0) {
                return false
            } else {
                counts[index]--
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("ppqp", "abbcabc")
            val patterns = arrayOf("pq", "abc")
            for (i in strings.indices) {
                val indices = StringAnagrams().findStringAnagrams(strings[i], patterns[i])
                println("string: ${strings[i]}, pattern: ${patterns[i]}, indices: $indices")
            }
        }
    }
}