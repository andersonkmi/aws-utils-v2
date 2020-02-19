package org.codecraftlabs.aws

import software.amazon.awssdk.regions.Region._
import org.codecraftlabs.aws.AwsRegion._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AwsRegionUtilSpec extends AnyFlatSpec with Matchers {
  "When passing us-east-1" should "return US_EAST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(UsEast1)
    region shouldEqual US_EAST_1
  }

  "When passing us-east-2" should "return US_EAST_2 (AWS region)" in {
    val region = AwsRegionUtil.region(UsEast2)
    region shouldEqual US_EAST_2
  }

  "When passing us-west-1" should "return US_WEST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(UsWest1)
    region shouldEqual US_WEST_1
  }

  "When passing us-west-2" should "return US_WEST_2 (AWS region)" in {
    val region = AwsRegionUtil.region(UsWest2)
    region shouldEqual US_WEST_2
  }

  "When passing sa-east-1" should "return SA_EAST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(SaEast1)
    region shouldEqual SA_EAST_1
  }

  "When passing ca-central-1" should "return CA_CENTRAL_1 (AWS region)" in {
    val region = AwsRegionUtil.region(CaCentral1)
    region shouldEqual CA_CENTRAL_1
  }

  "When passing eu-north-1" should "return EU_NORTH_1 (AWS region)" in {
    val region = AwsRegionUtil.region(EuNorth1)
    region shouldEqual EU_NORTH_1
  }

  "When passing eu-central-1" should "return EU_CENTRAL_1 (AWS region)" in {
    val region = AwsRegionUtil.region(EuCentral1)
    region shouldEqual EU_CENTRAL_1
  }

  "When passing eu-west-1" should "return EU_WEST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(EuWest1)
    region shouldEqual EU_WEST_1
  }

  "When passing eu-west-2" should "return EU_WEST_2 (AWS region)" in {
    val region = AwsRegionUtil.region(EuWest2)
    region shouldEqual EU_WEST_2
  }

  "When passing eu-west-3" should "return EU_WEST_3 (AWS region)" in {
    val region = AwsRegionUtil.region(EuWest3)
    region shouldEqual EU_WEST_3
  }

  "When passing ap-northeast-1" should "return AP_NORTHEAST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(ApNorthEast1)
    region shouldEqual AP_NORTHEAST_1
  }

  "When passing ap-northeast-2" should "return AP_NORTHEAST_2 (AWS region)" in {
    val region = AwsRegionUtil.region(ApNorthEast2)
    region shouldEqual AP_NORTHEAST_2
  }

  "When passing ap-southeast-1" should "return AP_SOUTHEAST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(ApSouthEast1)
    region shouldEqual AP_SOUTHEAST_1
  }

  "When passing ap-southeast-2" should "return AP_SOUTHEAST_2 (AWS region)" in {
    val region = AwsRegionUtil.region(ApSouthEast2)
    region shouldEqual AP_SOUTHEAST_2
  }

  "When passing ap-south-1" should "return AP_SOUTH_1 (AWS region)" in {
    val region = AwsRegionUtil.region(ApSouth1)
    region shouldEqual AP_SOUTH_1
  }
}
