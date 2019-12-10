package com.atguigu.core.day03

import scala.reflect.ClassTag

/**
  * ClassTag：类标签-类型上下文
  * Author lzc
  * Date 2019/12/10 9:21
  */
object ClassTagDemo {
    def main(args: Array[String]): Unit = {
        newArr[Int](10)
    }
    
    /*def newArr[T](len: Int)(implicit ct: ClassTag[T]) = {
        
        new Array[T](len)
    }*/
    
    def newArr[T: ClassTag](len: Int) = {
        new Array[T](len)
        val value: ClassTag[T] = implicitly[ClassTag[T]]  //从冥界召唤
        println(value)
    }
}
