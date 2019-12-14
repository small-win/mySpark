package com.atguigu.core.MyTest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author lsy
  * @create 2019-12-12 16:10
  */
object Fighting {
    def main(args: Array[String]): Unit = {
        //new一个sc对象
        val conf = new SparkConf().setAppName("Fighting!!!").setMaster("local[2]")
        val sc = new SparkContext(conf)
        //new一个RDD
        val list = List(1 to 6)
        val rdd = sc.parallelize(list,4)

        //自己具体的业务逻辑实现
//        val res = rdd.mapPartitionsWithIndex((index, items) => items.map((index, _)))


        res.collect.foreach(println)
        //关闭sc
        sc.stop()

    }

}
