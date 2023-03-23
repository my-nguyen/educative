package patterns_for_coding_questions._11_modified_binary_search

class SearchInASortedInfiniteArray {
    class ArrayReader(val array: IntArray) {
        fun get(index: Int): Int {
            return if (index >= array.size) {
                Integer.MAX_VALUE
            } else {
                array[index]
            }
        }
    }

    fun search(reader: ArrayReader, key: Int): Int {
        return mine(reader, key)
        // return educative(reader, key)
    }

    // from educative
    // The problem follows the Binary Search pattern. Since Binary Search helps us find a number in a sorted array
    // efficiently, we can use a modified version of the Binary Search to find the ‘key’ in an infinite sorted array.
    // The only issue with applying binary search in this problem is that we don’t know the bounds of the array. To
    // handle this situation, we will first find the proper bounds of the array where we can perform a binary search.
    // An efficient way to find the proper bounds is to start at the beginning of the array with the bound’s size as ‘1’
    // and exponentially increase the bound’s size (i.e., double it) until we find the bounds that can have the key.
    private fun educative(reader: ArrayReader, key: Int): Int {
        // find the proper bounds first
        var start = 0
        var end = 1
        while (reader.get(end) < key) {
            // as long as the end index doesn't cover key yet, create a new boundary with the new start just beyond
            // the current end and the new end to double the current size
            val newStart = end + 1
            end += (end - start + 1) * 2
            start = newStart
        }
        return binarySearch(reader, key, start, end)
    }

    private fun binarySearch(reader: ArrayReader, key: Int, start: Int, end: Int): Int {
        var start = start
        var end = end
        while (start <= end) {
            val mid = start + (end - start) / 2
            when {
                key < reader.get(mid) -> end = mid - 1
                key > reader.get(mid) -> start = mid + 1
                else -> return mid
            }
        }
        return -1
    }

    private fun mine(reader: ArrayReader, key: Int): Int {
        var left = 0
        var right = findRight(reader)
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                reader.get(mid) < key -> left = mid + 1
                reader.get(mid) > key -> right = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    private fun findRight(reader: ArrayReader): Int {
        var left = Integer.MAX_VALUE
        var right = Integer.MAX_VALUE
        while (reader.get(left) == Integer.MAX_VALUE) {
            right = left
            left /= 2
        }
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (reader.get(mid) == Integer.MAX_VALUE) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return right
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30),
                intArrayOf(4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30),
                intArrayOf(1, 3, 8, 10, 15),
                intArrayOf(1, 3, 8, 10, 15),
            )
            val keys = arrayOf(16, 11, 15, 200)
            for (i in arrays.indices) {
                val reader = ArrayReader(arrays[i])
                val index = SearchInASortedInfiniteArray().search(reader, keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
                // println("array: ${arrays[i].contentToString()}, right: ${patterns_for_coding_questions.SearchInASortedInfiniteArray().findRight(reader)}")
            }
        }
    }
}