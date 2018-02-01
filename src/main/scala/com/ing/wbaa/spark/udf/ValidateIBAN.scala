package com.ing.wbaa.spark.udf

import org.apache.spark.sql.api.java.UDF1
import org.iban4j._

import scala.util.Try


/** Validate IBAN (Whitespace removed). If valid, no execption is thrown in IbanUtil and true is returned
  * If Invalid, an exception is thrown and false is returned.  If null, false is also returned.
  */
class ValidateIBAN extends UDF1[String, Boolean] {

  override def call(potentialIBAN: String): Boolean = {
    Option(potentialIBAN).exists(ibanString => Try(
      IbanUtil.validate(
        ibanString.replaceAll("\\s", "")
      )
    ).isSuccess)
  }
}
