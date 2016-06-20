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

import opennlp.tools.ml.model.AbstractModel;
import opennlp.tools.ml.model.MaxentModel;
import opennlp.tools.util.BaseToolFactory;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.model.BaseModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * Created by anthony on 4/20/16.
 */
public class ProfilerModel extends BaseModel {
  private static final String COMPONENT_NAME = "GenderProfilerME";
  private static final String PROFILER_MODEL_ENTRY_NAME = "profiler.model";

  public ProfilerModel(String languageCode, MaxentModel profilerModel,
    Map<String, String> manifestInfoEntries, ProfilerFactory factory) {
    super(COMPONENT_NAME, languageCode, manifestInfoEntries, factory);

    artifactMap.put(PROFILER_MODEL_ENTRY_NAME, profilerModel);
    checkArtifactMap();
  }

  public ProfilerModel(InputStream in)
    throws IOException, InvalidFormatException {
    super(COMPONENT_NAME, in);
  }

  public ProfilerModel(File modelFile)
    throws IOException, InvalidFormatException {
    super(COMPONENT_NAME, modelFile);
  }

  public ProfilerModel(URL modelURL)
    throws IOException, InvalidFormatException {
    super(COMPONENT_NAME, modelURL);
  }

  @Override protected void validateArtifactMap() throws InvalidFormatException {
    super.validateArtifactMap();

    if (!(artifactMap
      .get(PROFILER_MODEL_ENTRY_NAME) instanceof AbstractModel)) {
      throw new InvalidFormatException("problem in the model");
    }
  }

  public ProfilerFactory getFactory() {
    return (ProfilerFactory) this.toolFactory;
  }

  @Override protected Class<? extends BaseToolFactory> getDefaultFactory() {
    return ProfilerFactory.class;
  }

  public MaxentModel getMaxentModel() {
    return (MaxentModel) artifactMap.get(PROFILER_MODEL_ENTRY_NAME);
  }
}
