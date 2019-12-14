package com.atguigu.core.day04.file

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 本代码为文件切片分析
  * Author lsy
  * Date 2019/12/11 11:15
  */
object FileDemo1 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("FileDemo1").setMaster("local[4]")
        val sc: SparkContext = new SparkContext(conf)
        val rdd1 = sc.textFile("d:/0508")
        println(rdd1.partitions.length)
        rdd1.collect
//        Thread.sleep(10000000000L)
        sc.stop()
    }
}
/*
若0722文件夹中存储a|b|c|d四个1字节文件和196字节的e文件
    那么读取的时候为6个切片，即为6个分区（跟Hadoop底层的切片有差异）：
        totalSize：196+1+1+1+1=200 字节
        goalSize：200 / 2 =100 字节
        (double) bytesRemaining)/splitSize：196/100 > 1.1，从而100字节切1片
        bytesRemaining -= splitSize：196-100=96，剩下的96字节切一片，
        val array = new Array[Partition](inputSplits.size)：故有6个切片（即6个分区）

本地模式跑的话，一个块大小为32M
Hadoop 1版本为64M，2版本为128M
 */