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

import java.util.*;

/**
 * Created by anthony on 4/20/16.
 */
public class ProfilerSample {

  private final String age;
  private final String gender;
  private final List<String> text;

  public ProfilerSample(String gender, String age, String text) {
    this(gender, age, WhitespaceTokenizer.INSTANCE.tokenize(text));
  }

  public ProfilerSample(String gender, String age, String text[]) {
    if (age == null || gender == null) {
      throw new IllegalArgumentException("age and gender must not be null");
    }
    if (text == null) {
      throw new IllegalArgumentException("text must not be null");
    }

    this.age = age;
    this.gender = gender;
    this.text = Collections
      .unmodifiableList(new ArrayList<String>(Arrays.asList(text)));

  }

  public String getAge() {
    return age;
  }

  public String getGender() {
    return gender;
  }

  public String[] getText() {
    return text.toArray(new String[text.size()]);
  }

  @Override public String toString() {

    StringBuilder sampleString = new StringBuilder();

    sampleString.append(getGender() + " " + getAge()).append('\t');

    for (String s : text) {
      sampleString.append(s).append(' ');
    }

    if (sampleString.length() > 0) {
      // remove last space
      sampleString.setLength(sampleString.length() - 1);
    }

    return sampleString.toString();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof ProfilerSample) {
      ProfilerSample a = (ProfilerSample) obj;

      return getAge().equals(a.getAge()) && getGender().equals(a.getGender())
        && Arrays.equals(getText(), a.getText());
    } else {
      return false;
    }
  }
}
