package com.ing.wbaa.spark.udf

import org.scalatest.{FlatSpec, Matchers}

class ValidateIBANSpec extends FlatSpec with Matchers {
  private val udf = ValidateIBAN

  "ValidateIBAN.evaluate" should "return true for valid IBANs" in {
    val data = Array(
      "AL47 2121 1009 0000 0002 3569 8741", //Albania
      "AD12 0001 2030 2003 5910 0100", //Andorra
      "AT61 1904 3002 3457 3201", //Austria
      "AZ21 NABZ 0000 0000 1370 1000 1944", //Azerbaijan
      "BH67 BMAG 0000 1299 1234 56", //Bahrain
      "BE62 5100 0754 7061", //Belgium
      "BA39 1290 0794 0102 8494", //Bosnia and Herzegovina
      "BG80 BNBG 9661 1020 3456 78", //Bulgaria
      "HR12 1001 0051 8630 0016 0", //Croatia
      "CY17 0020 0128 0000 0012 0052 7600", //Cyprus
      "CZ65 0800 0000 1920 0014 5399", //Czech Republic
      "DK50 0040 0440 1162 43", //Denmark
      "EE38 2200 2210 2014 5685", //Estonia
      "FO97 5432 0388 8999 44", //Faroe Islands
      "FI21 1234 5600 0007 85", //Finland
      "FR14 2004 1010 0505 0001 3M02 606", //France
      "GE29 NB00 0000 0101 9049 17", //Georgia
      "DE89 3704 0044 0532 0130 00", //Germany
      "GI75NWBK000000007099453", //Gibraltar
      "GR16 0110 1250 0000 0001 2300 695", //Greece
      "GL56 0444 9876 5432 10", //Greenland
      "HU42 1177 3016 1111 1018 0000 0000", //Hungary
      "IS14 0159 2600 7654 5510 7303 39", //Iceland
      "IE29 AIBK 9311 5212 3456 78", //Ireland
      "IL62 0108 0000 0009 9999 999", //Israel
      "IT40 S054 2811 1010 0000 0123 456", //Italy
      "JO94 CBJO 0010 0000 0000 0131 0003 02", //Jordan
      "KW81 CBKU 0000 0000 0000 1234 5601 01", //Kuwait
      "LV80 BANK 0000 4351 9500 1", //Latvia
      "LB62 0999 0000 0001 0019 0122 9114", //Lebanon
      "LI21 0881 0000 2324 013A A", //Liechtenstein
      "LT121000011101001000", //Lithuania
      "LU28 0019 4006 4475 0000", //Luxembourg
      "MK072 5012 0000 0589 84", //Macedonia
      "MT84 MALT 0110 0001 2345 MTLC AST0 01S", //Malta
      "MU17 BOMM 0101 1010 3030 0200 000M UR", //Mauritius
      "MD24 AG00 0225 1000 1310 4168", //Moldova
      "MC93 2005 2222 1001 1223 3M44 555", //Monaco
      "ME25 5050 0001 2345 6789 51", //Montenegro
      "NL39 RABO 0300 0652 64", //Netherlands
      "NO93 8601 1117 947", //Norway
      "PK36 SCBL 0000 001123456702", //Pakistan
      "PL60 1020 1026 0000 0422 7020 1111", //Poland
      "PT50 0002 0123 1234 5678 9015 4", //Portugal
      "QA58 DOHB 0000 1234 5678 90AB CDEF G", //Qatar
      "RO49 AAAA 1B31 0075 9384 0000", //Romania
      "SM86 U032 2509 8000 0000 0270 100", //San Marino
      "SA03 8000 0000 6080 1016 7519", //Saudi Arabia
      "RS35 2600 0560 1001 6113 79", //Serbia
      "SK31 1200 0000 1987 4263 7541", //Slovak Republic
      "SI56 1910 0000 0123 438", //Slovenia
      "ES80 2310 0001 1800 0001 2345", //Spain
      "SE35 5000 0000 0549 1000 0003", //Sweden
      "CH93 0076 2011 6238 5295 7", //Switzerland
      "TN59 1000 6035 1835 9847 8831", //Tunisia
      "TR33 0006 1005 1978 6457 8413 26", //Turkey
      "AE07 0331 2345 6789 0123 456", //UAE
      "GB29 NWBK 6016 1331 9268 19" //United Kingdom
    )

    for (input <- data) {
      withClue(s"IBAN: $input validation returned ") {
        udf.evaluate(input) should be(true)
      }
    }
  }

  "ValidateIBAN.evaluate" should "return false for invalid IBANs" in {
    val data = Array(
      "NLINGB1234", //Too short
      "KW81 CBKU 0000 0000 0000 1234 5601 02", //Wrong check digit
      "1234567890", //is BBAN
      "", // is Empty
      null //is null
    )
    for (input <- data) {
      withClue(s"IBAN: $input validation returned ") {
        udf.evaluate(input) should be(false)
      }
    }
  }

}
