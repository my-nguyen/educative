package patterns_for_coding_questions._9_two_heaps

import java.util.*

class MaximizeCapital {
    // i don't quite understand this solution
    // from educative
    // While selecting projects we have two constraints:
    // 1. We can select a project only when we have the required capital.
    // 2. There is a maximum limit on how many projects we can select.
    // Since we don’t have any constraint on time, we should choose a project, among the projects for which we have
    // enough capital, which gives us a maximum profit. Following this greedy approach will give us the best solution.
    // While selecting a project, we will do two things:
    // 1. Find all the projects that we can choose with the available capital.
    // 2. From the list of projects in the 1st step, choose the project that gives us a maximum profit.
    // We can follow the Two Heaps approach similar to Find the Median of a Number Stream. Here are the steps of our algorithm:
    // 1. Add all project capitals to a min-heap, so that we can select a project with the smallest capital requirement.
    // 2. Go through the top projects of the min-heap and filter the projects that can be completed within our available
    //    capital. Insert the profits of all these projects into a max-heap, so that we can choose a project with the
    //    maximum profit.
    // 3. Finally, select the top project of the max-heap for investment.
    // 4. Repeat the 2nd and 3rd steps for the required number of projects
    fun findMaximumCapital(capitals: IntArray, profits: IntArray, projects: Int, initial: Int): Int {
        val n = capitals.size
        val minCapital = PriorityQueue<Int>(n) { i0, i1 -> capitals[i0] - capitals[i1]}
        val maxProfit = PriorityQueue<Int>(n) { i0, i1 -> profits[i1] - profits[i0] }

        // insert all project capitals to a min-heap
        for (i in 0 until n) {
            minCapital.offer(i)
        }

        // try to find a total of 'numberOfProjects' best projects
        var capital = initial
        for (i in 1..projects) {
            // find all projects that can be selected within the available capital and insert them in a max-heap
            while (minCapital.isNotEmpty() && capitals[minCapital.peek()] <= capital) {
                maxProfit.offer(minCapital.poll())
            }

            if (maxProfit.isEmpty()) {
                // terminate if we are not able to find any project that can be completed within the available capital
                break
            } else {
                // select the project with the maximum profit
                capital += profits[maxProfit.poll()]
            }
        }
        return capital
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val capitals = arrayOf(intArrayOf(0,1,2), intArrayOf(0,1,2,3), )
            val profits = arrayOf(intArrayOf(1,2,3), intArrayOf(1,2,3,5), )
            val projects = arrayOf(2, 3, )
            val initials = arrayOf(1, 0, )

            for (i in capitals.indices) {
                val capital = MaximizeCapital().findMaximumCapital(capitals[i], profits[i], projects[i], initials[i])
                println("Maximum capital: $capital")
            }
        }
    }
}