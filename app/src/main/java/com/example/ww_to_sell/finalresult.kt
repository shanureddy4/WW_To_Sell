package com.example.ww_to_sell

import Entropy
import Gain
import Info
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.nio.charset.Charset

class finalresult : Fragment(){
    var attr1:ArrayList<String> = arrayListOf()
    var attr2:ArrayList<String> = arrayListOf()
    var attr3:ArrayList<String> = arrayListOf()
    var output0:ArrayList<String> = arrayListOf()
    var output:ArrayList<String> = arrayListOf()
    var header:Array<String> = arrayOf("Weather","Temperature","Month","ROP","Crop_Price")

    var cropname:String =""
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.final_result_fragment,container,false)
        var tempory = arguments?.getStringArrayList("finalresult")
        cropname = ""
        cropname = cropname+tempory!!.get(0)
        tempory.removeAt(0)
        var resultset:ArrayList<String> = arrayListOf()
        Toast.makeText(context,cropname,Toast.LENGTH_SHORT).show()
        for (i in tempory!!)
        {
            resultset.add(i)
        }

        var textView  = view?.findViewById<TextView>(R.id.shanu)
        textView!!.text = whole_result(resultset)


        return view

    }
    fun getinfile() {
        try {
            var takefile:Int=0
            when(cropname)
            {
                "rice"->takefile=R.raw.rice
                "jower"->takefile=R.raw.barley
                "wheat"->takefile=R.raw.wheat
                "tomato"->takefile=R.raw.tomato
                "groundnut"->takefile=R.raw.groundnut
            }

            val inputStream: InputStream =resources.openRawResource(takefile)
            val reader= BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
            var line =reader.readLine()
            reader.readLines().forEach{
                val record =it.split(",").toMutableList()
                if (record[1].toInt() in 22..24) {
                    record[1] = "verylow"
                } else if (record[1].toInt() in 25..27) {
                    record[1] = "low"
                } else if (record[1].toInt() in 28..30) {
                    record[1] = "medium"
                } else if (record[1].toInt() in 31..33) {
                    record[1] = "high"
                } else if (record[1].toInt() > 33) {
                    record[1] = "veryhigh"
                }
                if (record[2].toInt() in 1..3) {
                    record[2] = "m1"
                } else if (record[2].toInt() in 4..6) {
                    record[2] = "m2"
                } else if (record[2].toInt() in 7..9) {
                    record[2] = "m3"
                } else if (record[2].toInt() in 10..12) {
                    record[2] = "m4"
                }
                attr1.add(record[0])
                attr2.add(record[1])
                attr3.add(record[2])
                output0.add(record[3])
                output.add(record[4])
                Log.d("i","${record[0]}  ${record[1]}    ")

            }
        }catch (e: Exception){
            Log.d("error",e.toString())
        }



    }//get in file ends

    fun getinfoval(A:ArrayList<String>):Map<MutableList<String>,Int>
    {  var list:MutableList<MutableList<String>> = mutableListOf()// list of two info attribute values and output values
        list.clear()
        for (vals in 0..(output.size-1))
        {
            list.add(vals, mutableListOf(A[vals],output[vals]))
        }
//        for (val2 in 0..19)
//        {
//            println("this is mutable list ${list.get(val2)}")
//        }
        return list.groupingBy { it }.eachCount()
    }//getinfoval ends
    var infolist:MutableList<MutableList<Int>> = mutableListOf()
    var tempcoll:MutableMap<MutableList<String>,Int> = mutableMapOf()
    fun getinfo(B:Map<MutableList<String>,Int>):Double
    {
        infolist.clear()
        //convert array values to list
        tempcoll.clear()
        tempcoll= B.toMutableMap()
        var count=0

        for ((keys,vals) in B )
        {var justlist= mutableListOf<Int>()


            for ((x,y )in tempcoll)
            {

                if(keys.first()==x.first()&& y !=0 )
                {

                    tempcoll.replace(x,0)
                    justlist.add(y)
                    count++
                }
            }
            if (count==1)
            { justlist.add(0) }
            count=0
            if (!justlist.isEmpty()) {
                infolist.add(justlist)
            }
        }
//        for (x in infolist) {
//            println("this $x")
//        }
        return (Info().info(infolist,attr1.size))
    }//getinfo ends
    fun whole_result(A:ArrayList<String>):String{
        getinfile()
        // obj.convert()
        // obj.generlist()
        var sumall=output.size
        //getting entropy for output table
        //var sumall=sample.values.sum().toDouble()
        var entcontents: Map<String, Int> = output.groupingBy { it }.eachCount()//print it to know what happend
        var valuesarray = ArrayList<Int>()
        valuesarray.addAll(entcontents.values)
        var getentropy=Entropy().Entropy(valuesarray,sumall)
        println("######### Dataset #########")
        for (x in 0..attr1.size-1)
        { println("${attr1[x]}        ${attr2[x]}     ${attr3[x]}     ${output0[x]}     ${output[x]}")
        }
        println("\n\n")

        println("######### Entropy #########")
        println(getentropy)
        println("\n\n")
        //getting info values
        //testing for attrr1
        var atr1inconte: Map<String, Int> = attr2.groupingBy { it }.eachCount()
        println("######### information gain representation #########")
        println(atr1inconte)
        //getting infovalues of same kind from two different array
        //TODO here you have only 4 attributes inclue output so we are taking statically try to find a way that it should take values dynamically like take from user input
        var infovalattr1= getinfoval(attr1)//Map<MutableList<String>,Int>  type
        var infovalattr2 = getinfoval(attr2)
        var infovalattr3 = getinfoval(attr3)
        // var infovalattr4 = obj.getinfoval(obj.attr4)
        println(infovalattr2)
        println("\n\n")



        //getting info for 3 attributes
        var info1= getinfo(infovalattr1)
        var info2 = getinfo(infovalattr2)
        var info3= getinfo(infovalattr3)
        //var info4= obj.getinfo(infovalattr4)
        println("######### Information Gain #########")
        println("$info1     $info2      $info3      ")
        //TODO end here but unfortunatly continued

        var gain1=Gain().gain(getentropy,info1)
        var gain2=Gain().gain(getentropy,info2)
        var gain3=Gain().gain(getentropy,info3)
        // var gain4=Gain().gain(getentropy,info4)
        println("\n\n")

        println("######### Gain #########")
        println("$gain1     $gain2      $gain3     ")
        println("\n\n")
        //gainmap has the values that are sorted according to the gain value with key assosiate with the header
        var gainmap:MutableMap<String,Double> = mutableMapOf(header[0] to gain1,header[1] to gain2,header[2] to gain3).toList().sortedBy { (key,value) ->value }.toMap().toMutableMap()
        for ((k,v) in gainmap) { println("$k  $v") }
        println("\n\n")
        //attres contain the last result map
        //here to find which element of particular attribute should taken
        //first find the gainmap keys and search that key in the header array and obtain that index
        //now we stored the attr1... into arrattr to access with index that we get from above
        var attres:MutableMap<MutableList<MutableList<String>>,MutableList<MutableList<String>>> = mutableMapOf()//remember here always when you want to send any values to the empty list you cannot use temp list to clear ever time instead try to assign in a loop in which you want to use temp values
        var arrattr = arrayOf(attr1,attr2,attr3)
        var a=header.indexOf(gainmap.keys.elementAt(0))
        var b=header.indexOf(gainmap.keys.elementAt(1))
        var c=header.indexOf(gainmap.keys.elementAt(2))
        //var d=obj.header.indexOf(gainmap.keys.elementAt(3))
        for (A in 0..(attr1.size-1))
        {

            //var tempattr= mutableListOf<String>(obj.attr1[A],obj.attr2[A],obj.attr3[A],obj.attr4[A])
            var tempattr= mutableListOf(arrattr[c][A],arrattr[b][A],arrattr[a][A])//a,b,c,d
            var tempres= mutableListOf<String>(output[A],output0[A])//price and prodoction
            attres.put(mutableListOf(tempattr), mutableListOf(tempres))
////        tempattr.clear()
////        tempres.clear()

        }
        println("######### decision rules are #########")
        for ((keys,vals) in attres)
        {
            println("keys are $keys   result are $vals")
        }
        var listk= arrayListOf<String>("m4","low","sunny")
        var resultset:ArrayList<String> = arrayListOf()

        for((ke,vals) in attres)
        {
            for (i in ke)
            {  var pos =0
                for (k in i)
                {
                    if (A.contains(k))
                    {
                        pos++
                    }

                }
                if (pos==3) {
                    var add = "You will get ${vals[0][1].toUpperCase()} rate of production and ${vals[0][0].toUpperCase()}  crop price"
                    resultset.add(add)
                }
                else if (pos !=3)
                {
                    resultset.add("no")

                }


            }

        }
        resultset.removeIf  {t:String -> t.equals("no")}
        if (resultset.isEmpty())
        {
            resultset.add("we are having shortage of data will inform if we get result  THANKS!!")
        }
        return resultset[0]
    }


}