package com.slice

// import project
import scala.io.Source
import java.io.File
import java.util.HashMap
import scala.collection.mutable.LinkedHashMap

object Solution {

  def numberIndexMap(fileName: String): Map[List[Char], Int] = {
    var allCharList = Source.fromResource(fileName).getLines()
                            .map(_.toCharArray().toList.grouped(3).zipWithIndex.toList)
                            .toList

    return allCharList.flatten    // remove one List wrap
                      .groupBy(_._2)  // group by index in the tuple
                      .map({case(k, v) => v.flatMap(_._1) -> k})  // swap key, value and merge list of char without index
  }

  def numberList(numberMap: Map[List[Char], Int], fileName: String): List[(Iterable[String], String)] = {
    var allCharList = Source.fromResource(fileName).getLines().grouped(4)
                            .map(_.dropRight(1).toList.map(_.toCharArray().toList.grouped(3).zipWithIndex.toList))
                            .toList

    val numberMapList = allCharList.map(_.flatten.groupBy(_._2).map({case(k, v) => k -> v.flatMap(_._1)}))

    return numberMapList.map( m => LinkedHashMap(m.toSeq.sortBy(_._1):_*) )
                          .map( md => md.values.map( charList => numberMap.get(charList).map(r => (r + 1).toString()).getOrElse("?") ) )
                          .map( lm => lm -> checkValidChecksum(lm.toList) )
  }

  def checkValidChecksum(numList: List[String]): String = {
    if (numList.contains("?")) {
      "ILL"
    } else {
      val resultNum = numList.zip(List.range(numList.length,0,-1))
                              .map({case(v1, v2) => v1.toInt * v2})
                              .reduce(_ + _)
      if (resultNum % 11 == 0) {
        ""
      } else {
        "ERR"
      }
    }
  }

  def solution( ) : Unit = {
      val numberMap: Map[List[Char], Int] = this.numberIndexMap("sample.txt")
      // println(numberMap) // for debug

      // println(numberMap.get(List(' ',' ',' ','|','_','|',' ',' ','|'))) // for test should be 3

      println(numberList(numberMap, "test.txt"))
      
      // checkValidChecksum(List("1", "2", "3", "4", "5", "6", "7", "8", "9"))
  }
}