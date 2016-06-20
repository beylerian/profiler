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

import opennlp.tools.doccat.FeatureGenerator;
import opennlp.tools.ml.model.Event;
import opennlp.tools.util.AbstractEventStream;
import opennlp.tools.util.ObjectStream;

import java.util.Iterator;

/**
 * Created by anthony on 4/20/16.
 */
public class ProfilerEventStream extends AbstractEventStream<ProfilerSample> {

  /**
   * Initializes the current instance.
   *
   * @param data {@link ObjectStream} of {@link ProfilerSample}s
   *
   * @param featureGenerators
   */
  public ProfilerEventStream(ObjectStream<ProfilerSample> data, FeatureGenerator... featureGenerators) {
    super(data);
  }

  @Override
  protected Iterator<Event> createEvents(final ProfilerSample sample) {
    return null;

  }
}