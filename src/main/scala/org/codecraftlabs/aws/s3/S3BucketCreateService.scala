package org.codecraftlabs.aws.s3

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.aws.AwsException
import org.codecraftlabs.aws.AwsRegionUtil.region
import software.amazon.awssdk.awscore.exception.AwsServiceException
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest

object S3BucketCreateService {
  @transient private lazy val logger: Logger = LogManager.getLogger(S3BucketCreateService.getClass)

  def create(bucket: S3Bucket): Unit = {
    try {
      logger.info(s"Creating the bucket '$bucket'")
      val s3Client = S3Client.builder.region(region(bucket.getRegion)).build
      val request = CreateBucketRequest.builder.bucket(bucket.getName).build
      s3Client.createBucket(request)
      logger.info(s"Bucket '${bucket.getName}' created successfully")
    } catch {
      case exception: AwsServiceException =>
        logger.warn("Error when creating bucket")
        throw AwsException("Error when creating bucket", exception)
    }
  }

  def create(buckets: List[S3Bucket]): Unit = {
    buckets.foreach(create)
  }
}
