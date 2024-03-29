package com.atguigu.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Author lzc
  * Date 2019/12/7 16:40
  */
object MapPartitionsDemo1 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("MapDemo1").setMaster("local[2]")
        val sc: SparkContext = new SparkContext(conf)
        val arr1 = Array(30, 50, 70, 60, 10, 20, 100)
        val rdd1: RDD[Int] = sc.parallelize(arr1, 2)
        
        val rdd2 = rdd1.mapPartitions(it => {

            it.map(x => (x * x,1))
        })
        /*rdd1.map(x => {
        // 建立一个到mysql的连接
        
        })*/

//        val rdd2 = rdd1.mapPartitionsWithIndex((index, it) => it.map((_, index)))
        println(rdd2.collect().mkString(", "))
        
        sc.stop()
        
    }
}
