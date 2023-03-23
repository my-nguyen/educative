package practice2.top_k_elements

import java.util.*

class ConnectRopes {
    fun minimumCostToConnectRopes(ropeLengths: IntArray): Int {
        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,3,11,5), intArrayOf(3,4,5,6), intArrayOf(1,3,11,5,2)
            )
            for (lengths in arrays) {
                val cost = ConnectRopes().minimumCostToConnectRopes(lengths)
                println("lengths: ${lengths.contentToString()}, minimum cost: $cost")
            }
        }
    }
}