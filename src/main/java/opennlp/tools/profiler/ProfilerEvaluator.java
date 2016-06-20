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

import opennlp.tools.util.eval.Evaluator;
import opennlp.tools.util.eval.Mean;

/**
 * Created by anthony on 4/20/16.
 */
public class ProfilerEvaluator extends Evaluator<ProfilerSample> {

  private Mean accuracy = new Mean();

  /**
   * The {@link Profiler} used to create the disambiguated senses.
   */
  private Profiler profiler;

  /**
   * Initializes the current instance with the given {@link Profiler}.
   *
   * @param profiler
   *          the {@link Profiler} to evaluate.
   * @param listeners
   *          evaluation sample listeners
   */
  public ProfilerEvaluator(Profiler profiler,
    ProfilerEvaluationMonitor... listeners) {
    super(listeners);
    this.profiler = profiler;
  }

  @Override
  protected ProfilerSample processSample(ProfilerSample reference) {

    String referenceAge = reference.getAge();
    String referenceGender = reference.getGender();

    // get the best predicted age
    String predictedAge = profiler.binaryAge(reference);

    if (predictedAge == null) {
      System.out
        .println("There was no age for : " + reference.getText());
      return null;
    }
    // get the best predicted age
    String predictedGender = profiler.genderize(reference);

    if (predictedGender == null) {
      System.out
        .println("There was no gender for : " + reference.getText());
      return null;
    }

    if (referenceAge.equals(predictedAge)) {
      accuracy.add(1);
    } else {
      accuracy.add(0);
    }

    if (referenceGender.equals(predictedGender)) {
      accuracy.add(1);
    } else {
      accuracy.add(0);
    }

    return new ProfilerSample(predictedGender, predictedAge, reference.getText());
  }

  /**
   * Retrieves the accuracy.
   *
   * This is defined as: accuracy = correctly profiled / total samples
   *
   * @return the Profiling accuracy
   */
  public double getAccuracy() {
    return accuracy.mean();
  }

  /**
   * Retrieves the total number of words considered in the evaluation.
   *
   * @return the word count
   */
  public long getWordCount() {
    return accuracy.count();
  }

  /**
   * Represents this objects as human readable {@link String}.
   */
  @Override
  public String toString() {
    return "Accuracy: " + (accuracy.mean() * 100) + "%"
      + "\tNumber of Samples: " + accuracy.count();
  }
}
