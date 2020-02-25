package org.codecraftlabs.aws

import org.codecraftlabs.aws.AwsRegion.UsEast1
import org.codecraftlabs.aws.s3.S3Bucket
import org.codecraftlabs.aws.s3.S3Service.{blockPublicAccess, create, delete, list}
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

  "This test" should "find S3 bucket created" in {
    try {
      val bucket = new S3Bucket("codecraftlabs-list-test-s3-bucket")
      create(bucket, UsEast1)
      val result = list(UsEast1)
      assert(result.isDefined)
      val filtered = result.get.filter(bucket => bucket.getName.equals("codecraftlabs-list-test-s3-bucket"))
      assert(filtered.nonEmpty)
      delete(bucket)
    } catch {
      case _: AwsException => fail("S3 bucket find should work")
    }
  }

  "This test" should "list all S3 buckets" in {
    try {
      val buckets = list(UsEast1)
      assert(buckets.isDefined)
      assert(buckets.get.nonEmpty)
      val filtered = buckets.get.filter(i => i.getName.equals("kihei-data-lake"))
      assert(filtered.nonEmpty)
    } catch {
      case _: AwsException => fail("S3 bucket list should work")
    }
  }
}
