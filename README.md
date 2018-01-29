# WBAA Spark UDF 

## Introduction

This projects contains an example Scala UDF function, for use in PySpark.

## Why use a Scala UDF?

Native Spark UDFs written in Python are slow, because they have to be
executed in a Python process, rather than a JVM-based Spark Executor.
For a Spark Executor to run a Python UDF, it must:

* send data from the partition over to a Python process associated with
  the Executor, and
* wait for the Python process to deserialize the data, run the UDF on it,
  reserialize the data, and send it back.

By contrast, a Spark Scala UDF, whether written in Scala or Java, can be executed
in the Executor JVM, _even if the DataFrame logic is in Python_.

## Building

To build the jar file, use this command:

```
$ sbt clean assembly
```

That command will download the dependencies (if they haven't already been
downloaded), compile the code, run the unit tests, and create a jar files 
for Scala 2.11. That jars will be:

* Scala 2.11: `target/scala-2.11/spark-udf-assembly-0.2.0.jar`

## Using UDF in Spark
You can now register the UDF in Spark with the following line:
```
spark._jvm.com.ing.wbaa.spark.udf.ValidateIBAN.registerUDF(spark._jsparkSession)
```
You can now use the function as you would any other function:
```
spark.sql("""SELECT validate_iban('NL20INGB0001234567')""").show()
```
[Apache Spark]: http://spark.apache.org
[SBT]: http://scala-sbt.org
