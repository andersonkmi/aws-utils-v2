package org.codecraftlabs.aws.s3

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.aws.AwsException
import software.amazon.awssdk.awscore.exception.AwsServiceException
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest

object S3BucketDeleteService {
  @transient private lazy val logger: Logger = LogManager.getLogger(S3BucketCreateService.getClass)

  def delete(bucket: S3Bucket): Unit = {
    try {
      logger.info(s"Deleting the bucket '$bucket'")
      val s3Client = S3Client.builder.region(region(bucket.getRegion)).build
      val request = DeleteBucketRequest.builder.bucket(bucket.getName).build
      s3Client.deleteBucket(request)
      logger.info(s"Bucket '${bucket.getName}' deleted successfully")
    } catch {
      case exception: AwsServiceException =>
        logger.warn("Error when deleting bucket")
        throw AwsException("Error when deleting bucket", exception)
    }
  }

  def delete(buckets: List[S3Bucket]): Unit = {
    buckets.foreach(delete)
  }
}
