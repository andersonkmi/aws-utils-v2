package org.codecraftlabs.aws.s3

import java.util.Date

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.aws.AwsRegion.UsEast1
import org.codecraftlabs.aws.AwsRegionUtil.region
import org.codecraftlabs.aws.{AwsException, AwsRegion}
import software.amazon.awssdk.awscore.exception.AwsServiceException
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{CreateBucketRequest, DeleteBucketRequest, ListBucketsRequest, ListObjectsRequest, ListObjectsV2Request, ListObjectsV2Response, PublicAccessBlockConfiguration, PutPublicAccessBlockRequest, S3Exception}

import scala.collection.mutable.ListBuffer

object S3Service {
  @transient private lazy val logger: Logger = LogManager.getLogger(S3Service.getClass)

  def create(buckets: List[S3Bucket], awsRegion: AwsRegion.Value): Unit = {
    buckets.foreach(item => create(item, awsRegion))
  }

  def create(bucket: S3Bucket, awsRegion: AwsRegion.Value): Unit = {
    try {
      logger.info(s"Creating the bucket '$bucket' in region '$awsRegion'")
      val s3Client = S3Client.builder.region(region(awsRegion)).build
      val request = CreateBucketRequest.builder.bucket(bucket.getName).build
      s3Client.createBucket(request)

      logger.info(s"Bucket '${bucket.getName}' created successfully")
    } catch {
      case exception: AwsServiceException =>
        logger.warn("Error when creating bucket")
        throw AwsException("Error when creating bucket", exception)
    }
  }

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

  def list(awsRegion: AwsRegion.Value): Option[List[S3Bucket]] = {
    import scala.jdk.CollectionConverters._
    try {
      logger.info("Listing all buckets")
      val s3Client = S3Client.builder.region(region(awsRegion)).build
      val request = ListBucketsRequest.builder.build
      val response = s3Client.listBuckets(request)
      val buckets = response.buckets.asScala
      Option(buckets.map(
        element => {
          val bucket = new S3Bucket(element.name())
          bucket.setRegion(awsRegion)
          bucket.setCreationDateTime(Date.from(element.creationDate()))
          bucket
        }).toList)
    } catch {
      case exception: AwsServiceException =>
        logger.warn("Error when listing buckets")
        throw AwsException("Error when listing buckets", exception)
    }
  }

  def blockPublicAccess(bucket: S3Bucket, awsRegion: AwsRegion.Value): Unit = {
    try {
      logger.info(s"Blocking public access for the bucket '$bucket' in region '$awsRegion'")

      val config = PublicAccessBlockConfiguration.builder()
        .blockPublicAcls(true)
        .blockPublicPolicy(true)
        .restrictPublicBuckets(true)
        .ignorePublicAcls(true)
        .build()
      val blockRequest = PutPublicAccessBlockRequest.builder().bucket(bucket.getName).publicAccessBlockConfiguration(config).build()
      val s3Client = S3Client.builder.region(region(awsRegion)).build
      s3Client.putPublicAccessBlock(blockRequest)
    } catch {
      case exception: AwsServiceException =>
        logger.warn("Error when blocking bucket")
        throw AwsException("Error when blocking bucket", exception)
    }
  }

  def listObjects(bucket: S3Bucket): Option[List[S3Object]] = {
    import scala.jdk.CollectionConverters._
    try {
      logger.info(s"Listing all S3 objects inside '${bucket.getName}'")
      val s3Client = S3Client.builder.region(region(UsEast1)).build
      val items = ListBuffer[S3Object]()

      var continuationToken: Option[String] = None
      var result: ListObjectsV2Response = null
      do {
        var listObjectsRequest: ListObjectsV2Request = null
        if (continuationToken.isDefined) {
          listObjectsRequest = ListObjectsV2Request.builder().bucket(bucket.getName).continuationToken(continuationToken.get).build()
        } else {
          listObjectsRequest = ListObjectsV2Request.builder().bucket(bucket.getName).build()
        }

        result = s3Client.listObjectsV2(listObjectsRequest)
        val objects = result.contents().asScala
        items.addAll(objects.map(item => {
          val s3Object = new S3Object(item.key())
          s3Object.setETag(item.eTag())
          s3Object.setLastModified(Date.from(item.lastModified()))
          s3Object.setSize(item.size())
          s3Object.setStorageClass(item.storageClassAsString())
          s3Object
        }).toList)

        continuationToken = Option(result.continuationToken())
      } while (result.isTruncated)

      Option(items.toList)
    } catch {
      case exception: S3Exception =>
        logger.warn("Error when listing S3 objects")
        throw AwsException("Error when listing S3 objects", exception)
    }
  }
}
