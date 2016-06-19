/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright owlocationNameEntitieship.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tika.parser.profile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by anthony on 6/19/16.
 */
public class ProfileParserConfig {

  private URL ageProfilerModelUrl = null;
  private URL genderProfilerModelUrl = null;

  private static final Logger LOG = Logger
    .getLogger(ProfileParserConfig.class.getName());

  public ProfileParserConfig() {
    this.ageProfilerModelUrl = ProfileParserConfig.class
      .getResource("en-age-profiler.bin");
    this.genderProfilerModelUrl = ProfileParserConfig.class
      .getResource("en-gender-profiler.bin");
    init(this.getClass().getResourceAsStream("ProfileParserConfig.properties"));
  }

  /**
   * Initialize configurations from property files
   *
   * @param stream InputStream for GeoTopicConfig.properties
   */
  private void init(InputStream stream) {
    if (stream == null) {
      return;
    }
    Properties props = new Properties();

    try {
      props.load(stream);
    } catch (IOException e) {
      LOG.warning("ProfileParserConfig.properties not found in class path");
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException ioe) {
          LOG.severe("Unable to close stream: " + ioe.getMessage());
        }
      }
    }
  }

  public URL getAgeProfilerModelUrl() {
    return ageProfilerModelUrl;
  }

  public void setAgeProfilerModelUrl(URL ageProfilerModelUrl) {
    this.ageProfilerModelUrl = ageProfilerModelUrl;
  }

  public URL getGenderProfilerModelUrl() {
    return genderProfilerModelUrl;
  }

  public void setGenderProfilerModelUrl(URL genderProfilerModelUrl) {
    this.genderProfilerModelUrl = genderProfilerModelUrl;
  }
}
