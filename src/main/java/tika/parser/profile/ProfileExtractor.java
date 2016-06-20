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

import opennlp.tools.profiler.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by anthony on 6/19/16.
 */
public class ProfileExtractor {

  private Profile profile;
  private final AgeProfilerME ageProfiler;
  private final GenderProfilerME genderProfiler;

  public ProfileExtractor(AgeProfilerME ageProfiler, GenderProfilerME genderProfiler)
    throws IOException {
    this.ageProfiler = ageProfiler;
    this.genderProfiler = genderProfiler;
  }

  public Profile getProfileFromInput(InputStream stream) throws IOException {
    String[] in = IOUtils.toString(stream, UTF_8).split(" ");

    profile = new Profile();
    ProfilerSample sample = new ProfilerSample(in[0], in[1], Arrays.copyOfRange(in,2,in.length-1));

    if (ageProfiler != null) {
      synchronized (ageProfiler) {
        // TODO default is binary may want to modify depending on configuration
        String age = ageProfiler.binaryAge(sample);
        // TODO change default result by mapped one
        profile.setAgeRange(AgeProfiler.AGE_RANGES.AGE_18_24.name());
      }
    }
    if (genderProfiler != null) {
      synchronized (genderProfiler) {
        String gender = genderProfiler.genderize(sample);
        // TODO change default result by mapped one
        profile.setGender(GenderProfiler.GENDERS.GENDER_F.name());
      }
    }

    return profile;

  }

}
