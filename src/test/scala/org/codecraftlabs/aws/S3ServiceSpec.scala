package org.codecraftlabs.aws

import java.util.Date

import org.codecraftlabs.aws.AwsRegion.UsEast1
import org.codecraftlabs.aws.s3.S3Bucket
import org.codecraftlabs.aws.s3.S3Service.{blockPublicAccess, create, delete}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class S3ServiceSpec extends AnyFlatSpec with Matchers {
  "This test" should "create and delete S3 bucket" in {
    try {
      val bucket = new S3Bucket("codecraftlabs-test-s3-bucket")
      create(bucket, UsEast1)
      blockPublicAccess(bucket, UsEast1)
      delete(bucket)
    } catch {
      case _: AwsException => fail("S3 bucket create and delete should work")
    }
  }
}
