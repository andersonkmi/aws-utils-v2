package org.codecraftlabs.aws.s3

import java.util.Date

case class S3Object(key: String, eTag: String, lastModified: Date, size: Long, storageClass: String)
