package org.codecraftlabs.aws.s3

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.aws.AwsRegionUtil.region
import org.codecraftlabs.aws.{AwsException, AwsRegion}
import software.amazon.awssdk.awscore.exception.AwsServiceException
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ListBucketsRequest
import scala.jdk.CollectionConverters._

object S3BucketListService {
  @transient private lazy val logger: Logger = LogManager.getLogger(S3BucketListService.getClass)

  def list(awsRegion: AwsRegion.Value): Option[List[S3Bucket]] = {
    try {
      logger.info("Listing all buckets")
      val s3Client = S3Client.builder.region(region(awsRegion)).build
      val request = ListBucketsRequest.builder.build
      val response = s3Client.listBuckets(request)
      val buckets = response.buckets.asScala
      Option(buckets.map(element => new S3Bucket(element.name(), element.creationDate())).toList)
    } catch  {
      case exception: AwsServiceException =>
        logger.warn("Error when listing buckets")
        throw AwsException("Error when listing buckets", exception)
    }
  }
}
