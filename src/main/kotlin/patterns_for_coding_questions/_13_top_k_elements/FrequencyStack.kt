package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class FrequencyStack {
    private val stack = EducativeStack()
    fun push(num: Int) {
        stack.push(num)
    }

    fun pop() = stack.pop()

    // from educative
    // This problem follows the Top ‘K’ Elements pattern, and shares similarities with Top ‘K’ Frequent Numbers.
    // We can use a Max Heap to store the numbers. Instead of comparing the numbers we will compare their frequencies
    // so that the root of the heap is always the most frequently occurring number. There are two issues that need to be
    // resolved though:
    // 1. How can we keep track of the frequencies of numbers in the heap? When we are pushing a new number to the Max
    //    Heap, we don’t know how many times the number has already appeared in the Max Heap. To resolve this, we will
    //    maintain a HashMap to store the current frequency of each number. Thus whenever we push a new number in
    //    the heap, we will increment its frequency in the HashMap and when we pop, we will decrement its frequency.
    // 2. If two numbers have the same frequency, we will need to return the number which was pushed later while popping.
    //    To resolve this, we need to attach a sequence number to every number to know which number came first.
    // In short, we will keep three things with every number that we push to the heap:
    // 1. number // value of the number
    // 2. frequency // current frequency of the number when it was pushed to the heap
    // 3. sequenceNumber // a sequence number, to know what number came first
    class EducativeStack {
        data class Element(val number: Int, val frequency: Int, val sequence: Int)

        val frequencies = mutableMapOf<Int, Int>()
        val maxHeap = PriorityQueue<Element> { e1, e2 ->
            if (e1.frequency != e2.frequency) e2.frequency - e1.frequency else e2.sequence - e1.sequence
        }
        var sequence = 0

        fun push(number: Int) {
            val frequency = frequencies.getOrDefault(number, 0)
            this.frequencies[number] = frequency + 1
            val element = Element(number, frequency+1, sequence)
            maxHeap.offer(element)
            sequence++
        }

        fun pop(): Int {
            val num = maxHeap.poll().number

            // decrement the frequency or remove if this is the last number
            if (frequencies[num]!! > 1) {
                frequencies[num] = frequencies[num]!! - 1
            } else {
                frequencies.remove(num)
            }

            return num
        }
    }

    // incomplete solution
    class MyStack {
        data class Record(val value: Int, var id: Int)

        val map = mutableMapOf<Record, Int>()
        val maxHeap = PriorityQueue<MutableMap.MutableEntry<Record, Int>>() { e1, e2 ->
            if (e1.value == e2.value) e2.key.id - e1.key.id else e2.value - e1.value
        }
        var id = 0

        fun push(num: Int) {
            val entry = Record(num, id)
            id++
        }

        fun pop(): Int {
            return -1
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stack = FrequencyStack()
            stack.push(1)
            stack.push(2)
            stack.push(3)
            stack.push(2)
            stack.push(1)
            stack.push(2)
            stack.push(5)
            println(stack.pop())
            println(stack.pop())
            println(stack.pop())
        }
    }
}