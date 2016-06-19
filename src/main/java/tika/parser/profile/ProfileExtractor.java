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

import opennlp.tools.profiler.Profiler;
import opennlp.tools.profiler.ProfilerME;
import opennlp.tools.profiler.ProfilerSample;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by anthony on 6/19/16.
 */
public class ProfileExtractor {

  private Profile profile;
  private final ProfilerME ageProfiler;
  private final ProfilerME genderProfiler;

  public ProfileExtractor(ProfilerME ageProfiler, ProfilerME genderProfiler)
    throws IOException {
    this.ageProfiler = ageProfiler;
    this.genderProfiler = genderProfiler;
  }

  // TODO fix when sample is ready
  public Profile getProfileFromInput(InputStream stream) throws IOException {
    String[] in = IOUtils.toString(stream, UTF_8).split(" ");

    profile = new Profile();

    ProfilerSample sample = new ProfilerSample();

    if (ageProfiler != null) {
      String age = ageProfiler.binaryAge(sample);
      profile.setAgeRange(Profiler.AGE_RANGES.AGE_18_24.name());
    }
    if (genderProfiler != null) {
      String gender = genderProfiler.genderize(sample);
      profile.setGender(Profiler.GENDERS.GENDER_F.name());
    }

    return profile;

  }

}
