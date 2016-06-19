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
import opennlp.tools.profiler.ProfilerModel;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.ParseContext;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by anthony on 6/19/16.
 */
public class ProfileParser extends AbstractParser {

  private static final Logger LOG = Logger
    .getLogger(ProfileParser.class.getName());
  private static final MediaType MEDIA_TYPE = MediaType.application("profile");
  private static final Set<MediaType> SUPPORTED_TYPES = Collections
    .singleton(MEDIA_TYPE);

  private ProfileParserConfig config = new ProfileParserConfig();

  private ProfilerME ageProfiler;
  private ProfilerME genderProfiler;

  private URL ageModelUrl;
  private URL genderModelUrl;

  private boolean available;
  private boolean initialized;

  public Set<MediaType> getSupportedTypes(ParseContext parseContext) {
    return SUPPORTED_TYPES;
  }

  /**
   * Initializes this parser
   * @param ageModelUrl the URL to age Profiler model
   * @param genderModelUrl the URL to gender Profiler model
   */
  public void initialize(URL ageModelUrl, URL genderModelUrl) {
    if (this.ageModelUrl != null && this.ageModelUrl.equals(ageModelUrl)
      && this.genderModelUrl != null && this.genderModelUrl.equals(genderModelUrl)) {
      return;
    }

    this.ageModelUrl = ageModelUrl;
    this.genderModelUrl = genderModelUrl;

      try {
        ProfilerModel ageModel = new ProfilerModel(ageModelUrl);
        this.ageProfiler = new ProfilerME(ageModel);

        ProfilerModel genderModel = new ProfilerModel(genderModelUrl);
        this.genderProfiler = new ProfilerME(genderModel);
      } catch (Exception e) {
        LOG.warning("Profiler setup failed: " + e);
        this.available = false;
        e.printStackTrace();
      }

    initialized = true;
  }


  public void parse(InputStream inputStream, ContentHandler contentHandler,
    Metadata metadata, ParseContext parseContext)
    throws IOException, SAXException, TikaException {

    this.config = parseContext.get(ProfileParserConfig.class, config);
    initialize(this.config.getAgeProfilerModelUrl(), this.config.getGenderProfilerModelUrl());
    if (!isAvailable()) {
      return;
    }
    ProfileExtractor extractor = null;

    try {
      extractor = new ProfileExtractor(this.ageProfiler, this.genderProfiler);
    } catch (Exception e) {
      LOG.warning("Profiler setup failed: " + e);
      return;
    }

    Profile profile = extractor.getProfileFromInput(inputStream);

    metadata.add("Author_AGE", profile.getAgeRange());
    metadata.add("Author_GENDER", profile.getGender());
    metadata.add(Profiler.TRAITS.TRAIT_AGREEABLE.name(), Double.toString(profile.getTraits().get(0)));
    metadata.add(Profiler.TRAITS.TRAIT_CONSCIENTIOUS.name(), Double.toString(profile.getTraits().get(1)));
    metadata.add(Profiler.TRAITS.TRAIT_EXTROVERT.name(), Double.toString(profile.getTraits().get(2)));
    metadata.add(Profiler.TRAITS.TRAIT_OPEN.name(), Double.toString(profile.getTraits().get(3)));
    metadata.add(Profiler.TRAITS.TRAIT_STABLE.name(), Double.toString(profile.getTraits().get(4)));

  }


  public boolean isAvailable() {
    if (!initialized) {
      initialize(config.getAgeProfilerModelUrl(), config.getGenderProfilerModelUrl());
    }
    return this.available;
  }
}
