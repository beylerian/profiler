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

import java.util.ArrayList;

/**
 * Created by anthony on 4/20/16.
 */
public abstract class Profiler {

  /**
   * @param sample
   * @return M or F based on the classification based on one sample
   */
  public abstract String genderize(ProfilerSample sample);

  /**
   * @param samples
   * @return M or F based on the classification based on many samples
   */
  public abstract String genderize(ArrayList<ProfilerSample> samples);

  /**
   * @param sample
   * @return [18-34] or [35-xx] based on the classification based on one sample
   */
  public abstract String binaryAge(ProfilerSample sample);

  /**
   * @param samples
   * @return [18-34] or [35-xx] based on the classification based on many samples
   */
  public abstract String binaryAge(ArrayList<ProfilerSample> samples);

  /**
   * @param sample
   * @return [18-24], [25-34], [35-49], [50-64], [65-xx] based on the classification based on one sample
   */
  public abstract String quinaryAge(ProfilerSample sample);

  /**
   * @param samples
   * @return [18-24], [25-34], [35-49], [50-64], [65-xx] based on the classification based on many samples
   */
  public abstract String quinaryAge(ArrayList<ProfilerSample> samples);

}
