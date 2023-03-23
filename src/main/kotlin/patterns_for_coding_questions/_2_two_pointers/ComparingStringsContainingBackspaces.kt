package patterns_for_coding_questions._2_two_pointers

import java.util.*

class ComparingStringsContainingBackspaces {
    fun compare(string1: String, string2: String): Boolean {
        // return mine(string1, string2)
        return educative(string1, string2)
    }

    private fun educative(string1: String, string2: String): Boolean {
        // use two pointer approach to compare the strings
        var index1 = string1.lastIndex
        var index2 = string2.lastIndex
        while (index1 >= 0 || index2 >= 0) {
            val i1 = nextIndex(string1, index1)
            val i2 = nextIndex(string2, index2)
            if (i1 < 0 && i2 < 0) {
                // reached the end of both the strings
                return true
            } else if (i1 < 0 || i2 < 0) {
                // reached the end of one of the strings
                return false
            } else if (string1[i1] != string2[i2]) {
                // check if the characters are equal
                return false
            } else {
                index1 = i1 - 1
                index2 = i2 - 1
            }
        }
        return true
    }

    private fun nextIndex(string: String, i: Int): Int {
        var backspaces = 0
        var index = i
        while (index >= 0) {
            if (string[index] == '#') {
                backspaces++
            } else if (backspaces > 0) {
                backspaces--
            } else {
                break
            }
            index--
        }
        return index
    }

    private fun mine(string1: String, string2: String): Boolean {
        val s1 = removeHashes(string1)
        val s2 = removeHashes(string2)
        return s1 == s2
    }

    private fun removeHashes(string: String): String {
        val stack = Stack<Char>()
        for (c in string) {
            if (c == '#') {
                if (stack.isNotEmpty()) {
                    stack.pop()
                }
            } else {
                stack.push(c)
            }
        }
        return String(stack.toCharArray())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings1 = arrayOf("xy#z", "xy#z", "xp#", "xywrrmp")
            val strings2 = arrayOf("xzz#", "xyz#", "xyz##", "xywrrmu#p")
            for (i in strings1.indices) {
                val compare = ComparingStringsContainingBackspaces().compare(strings1[i], strings2[i])
                println("s1: ${strings1[i]}, s2: ${strings2[i]}, are equal? $compare")
            }
        }
    }
}