package com.ing.wbaa.spark.udf

import org.apache.spark.sql.SparkSession

import scala.reflect.runtime.universe._

/*
This Abstract class takes care of registering a UDF into your SparkSession and requires that you implement:
registerUDF
evaluate
* */
abstract class SparkUDF[A: TypeTag, B: TypeTag] {

  def registerUDF(spark: SparkSession)

  def registerUDF(spark: SparkSession, UDFName: String, scalaFunction: A => B) {
    spark.udf.register(UDFName, scalaFunction)
  }

  def evaluate(arg: A): B
}
