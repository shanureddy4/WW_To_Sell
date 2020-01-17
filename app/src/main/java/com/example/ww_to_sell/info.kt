import kotlin.collections.ArrayList
import kotlin.math.log

class Info {
    fun info(A: MutableList<MutableList<Int>>, T: Int): Double {
        var result = 0.0
        var alval = ArrayList<Double>()
        var insum = ArrayList<Double>()
        for (values in A) {
            var sum = 0.0
            var index = 0
            for (i in values) {
                sum = sum + i
            }
            alval.add(0, sum)
            index++
        }

        //insum values
        for (values in A) {
            var res = 0.0
            var index = 0
            for (value in values) {
                var alel = value / alval[index]
                res = res + (-alel * log(alel, 2.0))
            }
            insum.add(index, res * (alval[index]) / T)
            index++

        }
        insum.removeIf { filter->filter.isNaN() }
        for (vals in insum) {
            //println(vals)
            result=result+vals}
        return result

    }
}

fun main(args:Array<String>)
{
    var obj=Info()


    var lists= mutableListOf(mutableListOf(3,3), mutableListOf(8,0), mutableListOf(3,3,3))//lists can grow

    var get=obj.info(lists,23)
    //print(get)
}