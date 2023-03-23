package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class SchedulingTasks {
    fun scheduleTasks(tasks: CharArray, k: Int): Int {
        // return mine(tasks, k)
        return educative(tasks, k)
    }

    // from educative
    // This problem follows the Top ‘K’ Elements pattern and is quite similar to Rearrange String K Distance Apart.
    // We need to rearrange tasks such that same tasks are ‘K’ distance apart.
    // Following a similar approach, we will use a Max Heap to execute the highest frequency task first. After executing
    // a task we decrease its frequency and put it in a waiting list. In each iteration, we will try to execute as many
    // as k+1 tasks. For the next iteration, we will put all the waiting tasks back in the Max Heap. If, for any iteration,
    // we are not able to execute k+1 tasks, the CPU has to remain idle for the remaining time in the next iteration.
    private fun educative(tasks: CharArray, k: Int): Int {
        val frequencies = mutableMapOf<Char, Int>()
        for (chr in tasks) {
            frequencies[chr] = frequencies.getOrDefault(chr, 0) + 1
        }

        val maxHeap = PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_,v1), (_,v2) -> v2 - v1 }

        // add all tasks to the max heap
        maxHeap.addAll(frequencies.entries)

        var count = 0
        while (maxHeap.isNotEmpty()) {
            val waitList = mutableListOf<MutableMap.MutableEntry<Char, Int>>()
            var n = k + 1 // try to execute as many as 'k+1' tasks from the max-heap
            while (n > 0 && maxHeap.isNotEmpty()) {
                count++
                val currentEntry = maxHeap.poll()
                if (currentEntry.value > 1) {
                    currentEntry.setValue(currentEntry.value - 1)
                    waitList.add(currentEntry)
                }
                n--
            }
            maxHeap.addAll(waitList) // put all the waiting list back on the heap
            if (maxHeap.isNotEmpty()) {
                // we'll be having 'n' idle intervals for the next iteration
                count += n
            }
        }

        return count
    }

    // incorrect solution
    private fun mine(tasks: CharArray, k: Int): Int {
        val map = mutableMapOf<Char, Int>()
        for (c in tasks) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }

        val maxHeap = PriorityQueue<MutableMap.MutableEntry<Char, Int>> { (_, v1), (_, v2) -> v2 - v1 }
        maxHeap.addAll(map.entries)

        val queue = LinkedList<MutableMap.MutableEntry<Char, Int>>()
        var count = 0
        while (maxHeap.isNotEmpty()) {
            val current = maxHeap.poll()
            current.setValue(current.value - 1)
            println("char: ${current.key}")
            count++

            queue.offer(current)
            if (queue.size == k) {
                val entry = queue.poll()
                if (entry.value > 0) {
                    maxHeap.offer(entry)
                }
            }
        }

        while (queue.isNotEmpty()) {
            val entry = queue.poll()
            count += entry.value
        }
        return count
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("aaabcc", "aba")
            val distances = arrayOf(2, 3)
            for (i in strings.indices) {
                val tasks = strings[i].toCharArray()
                val scheduled = SchedulingTasks().scheduleTasks(tasks, distances[i])
                println("tasks: ${tasks.contentToString()}, k: ${distances[i]}, scheduled: $scheduled")
            }
        }
    }
}