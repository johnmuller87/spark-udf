package com.ing.wbaa.spark.udf

import org.apache.spark.sql.SparkSession
import org.iban4j._

import scala.util.Try

/** This UDF takes a String, and returns true if the String is a valid IBAN
  */
object ValidateIBAN extends SparkUDF[String, Boolean] {

  override def registerUDF(spark: SparkSession) {
    registerUDF(spark, "validate_iban", evaluate)
  }

  override def evaluate(potentialIBAN: String): Boolean = {
    //Validate IBAN (Whitespace removed). If valid, no execption is thrown in IbanUtil and true is returned
    //If Invalid, an exception is thrown and false is returned.  If null, false is also returned.
    Option(potentialIBAN).exists(ibanString => Try(
      IbanUtil.validate(
        ibanString.replaceAll("\\s", "")
      )
    ).isSuccess)
  }
}
