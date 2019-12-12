package com.atguigu.core.MyTest

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * map与偏函数的结合：偏map，功能极其强大（一对一返回，对类型、格式的转换）
  *     _.map{ case (参数列表) => (参数列表的处理逻辑) }
  * @author lsy
  * @create 2019-12-10 11:22
  */
object Practice {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("Practice").setMaster("local[2]")
        val sc = new SparkContext(conf)
        //1.先读文件
        val sourceRDD: RDD[String] = sc.textFile("agent.log")
        //2.处理成所需字段及其格式。行行处理挑元素，map成1好求和
        val proAdsAndOne= sourceRDD.map(line => {
            val arr = line.split(" ")
            ((arr(1),arr(4)),1)
        })
        //3.广告点击次数的汇总。按key对value求和，偏map成固定格式
        val proAdsAndCount = proAdsAndOne.reduceByKey(_ + _).map{
            case ((pro,ads),count) => (pro,(ads,count))
        }
        //4.按省份进行分组。处理成kv类型，即(pro,List((ads1,count1),......)
        val pacG = proAdsAndCount.groupByKey()
        //5.统计出每一个省份广告被点击次数的 TOP3。使用偏map成最终结果
        val pacT = pacG.map {
            case (pro, adsCountIt) => (pro, adsCountIt.toList.sortBy(_._2).take(3))
        }.sortByKey()/*.sortBy(_._1.toInt)*/
/*
        //6.满足需求，偏map实现！
        val jG = pacT.map {
            case (k, list) => (k, list.map{
                case (a,b) => a
            })
        }
        jG.collect.foreach(println)
*/

        pacT.collect.foreach(println)

        sc.stop()
    }
}

/*
1.	数据结构：时间戳，省份，城市，用户，广告，字段使用空格分割。
    1516609143867 6 7 64 16
    1516609143869 9 4 75 18
    1516609143869 1 7 87 12
2.	需求: 统计出每一个省份广告被点击次数的 TOP3

倒推法：
=>agent.log =>map(_.split...)
=>((pro,ads1),(pro,ads2),(pro,ads3)...) =>map
=>(((pro,ads1),1),((pro,ads1),1)((pro,ads2),1))... =>reduceByKey =>map =>groupByKey =>排序取前3
=>(pro,List((ads1,count1),(ads2,count2),(ads3,count3))) =>map/模式匹配
=>(pro,List(ads1,ads2,ads3))
 */