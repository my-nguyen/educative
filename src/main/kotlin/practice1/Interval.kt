package practice1

data class Interval(var start: Int, var end: Int) {
    override fun toString() = "[$start, $end]"

    companion object {
        fun build(array: Array<IntArray>): MutableList<Interval> {
            val intervals = mutableListOf<Interval>()
            for (pair in array) {
                val interval = Interval(pair[0], pair[1])
                intervals.add(interval)
            }
            return intervals
        }
    }
}
