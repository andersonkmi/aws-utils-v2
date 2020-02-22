package org.codecraftlabs.aws.s3

import java.util.Date

import org.codecraftlabs.aws.AwsRegion

class S3Bucket {
  private var name = ""
  private var region = AwsRegion.UsEast1
  private var creationDateTime = new Date()

  def this(name: String) {
    this()
    this.name = name
  }

  def getName: String = this.name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getRegion: AwsRegion.Value = this.region

  def setRegion(region: AwsRegion.Value): Unit = {
    this.region = region
  }

  def getCreationDateTime: Date = this.creationDateTime

  def setCreationDateTime(creationDateTime: Date): Unit = {
    this.creationDateTime = creationDateTime
  }

  override def toString: String = s"{'name':'$name', 'region': '${region.code}', 'creationDateTime': '${creationDateTime.toInstant}'}"
}