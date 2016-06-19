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

import java.util.ArrayList;

/**
 * Created by anthony on 6/19/16.
 */
public class Profile {



  private String ageRange;
  private String gender;
  private ArrayList<Double> traits;

  public String getAgeRange() {
    return ageRange;
  }

  public void setAgeRange(String ageRange) {
    this.ageRange = ageRange;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public ArrayList<Double> getTraits() {
    return traits;
  }

  public void setTraits(ArrayList<Double> traits) {
    this.traits = traits;
  }
}
