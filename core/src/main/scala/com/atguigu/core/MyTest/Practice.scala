package com.atguigu.core.MyTest

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author lsy
  * @create 2019-12-10 11:22
  */
object Practice {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("Practice").setMaster("local[2]")
        val sc = new SparkContext(conf)
        //1.先读文件
        val sourceRDD: RDD[String] = sc.textFile("agent.log")



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

 */