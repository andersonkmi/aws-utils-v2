package org.codecraftlabs.aws.s3

import java.time.Instant
import java.time.Instant.now

import org.codecraftlabs.aws.AwsRegion

class S3Bucket {
  private var name = ""
  private var region = AwsRegion.UsEast1
  private var creationDateTime = now

  def this(name: String) {
    this()
    this.name = name
  }

  def this(name: String, region: AwsRegion.Value) {
    this(name)
    this.region = region
  }

  def this(name: String, creationDateTime: Instant) {
    this(name)
    this.creationDateTime = creationDateTime
  }

  def this(name: String, region: AwsRegion.Value, creationDateTime: Instant) {
    this(name, region)
    this.creationDateTime = creationDateTime
  }

  def getName: String = this.name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getRegion: AwsRegion.Value = this.region

  def setRegion(region: AwsRegion.Value): Unit = {
    this.region = region
  }

  def getCreationDateTime: Instant = this.creationDateTime

  def setCreationDateTime(creationDateTime: Instant): Unit = {
    this.creationDateTime = creationDateTime
  }

  override def toString: String = s"{'name':'$name', 'region': '${region.code}', 'creationDateTime': '${creationDateTime.toString}'}"

}