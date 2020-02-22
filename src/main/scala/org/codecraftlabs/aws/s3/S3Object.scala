package org.codecraftlabs.aws.s3

import java.util.Date

class S3Object {
  private var key: Option[String] = None
  private var eTag: Option[String] = None
  private var lastModified: Option[Date] = None
  private var size: Option[Long] = None
  private var storageClass: Option[String] = None

  def this(key: String) {
    this()
    this.key = Option(key)
  }

  def getKey: Option[String] = this.key

  def getETag: Option[String] = this.eTag

  def setETag(eTag: String): Unit = {
    this.eTag = Option(eTag)
  }

  def setLastModified(lastModified: Date): Unit = {
    this.lastModified = Option(lastModified)
  }

  def getLastModified: Option[Date] = this.lastModified

  def setSize(size: Long): Unit = {
    this.size = Option(size)
  }

  def getSize: Option[Long] = this.size

  def setStorageClass(storageClass: String): Unit = {
    this.storageClass = Option(storageClass)
  }

  def getStorageClass: Option[String] = this.storageClass
}
