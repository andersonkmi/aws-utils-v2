package org.codecraftlabs.aws

import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.Region.of

object AwsRegionUtil {
  def region(region: AwsRegion.Value): Region = {
    of(region.regionCode)
  }
}
