package org.codecraftlabs.aws

import org.codecraftlabs.aws.s3.S3Bucket
import org.codecraftlabs.aws.s3.S3Service.{create, delete}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class S3ServiceSpec extends AnyFlatSpec with Matchers {
  ignore should "create and delete S3 bucket" in {
    try {
      val bucket = new S3Bucket("codecraftlabs-test-s3-bucket")
      create(bucket)
      delete(bucket)
    } catch {
      case _: AwsException => fail("S3 bucket create should work")
    }
  }
}
