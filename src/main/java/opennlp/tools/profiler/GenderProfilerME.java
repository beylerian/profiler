/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package opennlp.tools.profiler;

import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.TrainingParameters;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by anthony on 4/20/16.
 */
public class GenderProfilerME implements GenderProfiler {

  private ProfilerModel model;
  private ProfilerContextGenerator mContextGenerator;

  public GenderProfilerME(ProfilerModel model) {
    this.model = model;
  }

  public String genderize(ProfilerSample sample) {
    return null;
  }

  public String genderize(ArrayList<ProfilerSample> samples) {
    return null;
  }

  /**
   * Trains a Profiler model with default feature generation.
   *
   * @param languageCode
   * @param samples
   * @return the trained profiler model
   * @throws IOException
   */
  public static ProfilerModel train(String languageCode,
    ObjectStream<ProfilerSample> samples, TrainingParameters trainParams,
    ProfilerFactory profilerFactory) throws IOException {

    return null;

  }
}
