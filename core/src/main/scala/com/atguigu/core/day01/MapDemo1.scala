package com.atguigu.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Author lzc
  * Date 2019/12/7 16:40
  */
object MapDemo1 {
    def main(args: Array[String]): Unit = {
        val conf= new SparkConf().setAppName("MapDemo1").setMaster("local[2]")
        val sc = new SparkContext(conf)
        val arr1 = Array(30, 50, 70, 60, 10, 20)
        val rdd1: RDD[Int] = sc.parallelize(arr1)  //从集合中创建RDD
        
        val rdd2: RDD[Int] = rdd1.map(x => x * 2)
        println("结果为：" + rdd2.collect().mkString(", "))
        
        sc.stop()
        
    }
}
/*
在 Spark 中创建 RDD 的方式可以分为 3 种：
•	从集合中创建 RDD
•	从外部存储创建 RDD
•	从其他 RDD 转换得到新的 RDD。

 */