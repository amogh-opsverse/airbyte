/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination.s3_glue;

import io.airbyte.integrations.destination.s3.S3BaseJsonlGzipDestinationAcceptanceTest;
import java.util.HashSet;

public class S3GlueJsonlGzipDestinationAcceptanceTest extends S3BaseJsonlGzipDestinationAcceptanceTest {

  @Override
  protected void tearDown(TestDestinationEnv testEnv, HashSet<String> TEST_SCHEMAS) {
    super.tearDown(testEnv, TEST_SCHEMAS);

    GlueDestinationConfig glueDestinationConfig = GlueDestinationConfig.getInstance(configJson);
    try (var glueTestClient = new GlueTestClient(glueDestinationConfig.getAWSGlueInstance())) {

      glueTestClient.purgeDatabase(glueDestinationConfig.getDatabase());

    }
  }

  @Override
  protected String getImageName() {
    return "airbyte/destination-s3-glue:dev";
  }

}
