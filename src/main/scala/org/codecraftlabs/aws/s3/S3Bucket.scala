package org.codecraftlabs.aws.s3

import java.util.Date

import org.codecraftlabs.aws.AwsRegion

case class S3Bucket (name: String, region: AwsRegion.Value, creationDateTime: Date)