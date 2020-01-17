import kotlin.math.log
class Entropy
{



    fun Entropy(A:ArrayList<Int>,T:Int):Double
    {
        A.removeIf { int-> int==0 }
        var result=0.0

        for(value in A)
        {   var a:Double= value/T.toDouble()
            var logvalue=-(a)*(log(a,2.0))
            result=result+logvalue

        }
        return result
    }

}
//fun main(args: Array<String>)
//{
//    var obj=Entropy()
//
//
//    var array=ArrayList<Int>()
//    array.add(0,9)
//    array.add(1,5)
//    array.add(2,0)
//    array.add(3,3)
//    array.add(4,0)
//    array.removeIf { int-> int==0 }
//    var get=obj.Entropy(array,14.0)
//    println(get)
//}