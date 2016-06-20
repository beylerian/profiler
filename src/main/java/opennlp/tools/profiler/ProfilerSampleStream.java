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

import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.FilterObjectStream;
import opennlp.tools.util.ObjectStream;

import java.io.IOException;

/**
 * Created by anthony on 4/20/16.
 */
public class ProfilerSampleStream
  extends FilterObjectStream<String, ProfilerSample> {

  public ProfilerSampleStream(ObjectStream<String> samples) {
    super(samples);
  }

  public ProfilerSample read() throws IOException {
    String sampleString = samples.read();

    if (sampleString != null) {

      // Whitespace tokenize entire string
      String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(sampleString);

      ProfilerSample sample;

      if (tokens.length > 1) {
        String gender = tokens[0];
        String age = tokens[1];
        String docTokens[] = new String[tokens.length - 2];
        System.arraycopy(tokens, 2, docTokens, 0, tokens.length - 2);

        sample = new ProfilerSample(gender, age, docTokens);
      } else {
        throw new IOException(
          "Empty lines, or lines with only age and gender strings are not allowed!");
      }

      return sample;
    } else {
      return null;
    }
  }
}
