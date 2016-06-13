package org.apache.tika.parser.profile;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.ParseContext;
import java.util.logging.Logger;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.net.URL;
import java.io.InputStream;

/**
 * Created by anthony on 6/14/16.
 */
public class ProfileParser extends AbstractParser{

  private static final long serialVersionUID = -2241391757440215491L;
  private static final Logger LOG = Logger.getLogger(ProfileParser.class.getName());
  private static final MediaType MEDIA_TYPE =
    MediaType.application("profile-parser");
  private static final Set<MediaType> SUPPORTED_TYPES =
    Collections.singleton(MEDIA_TYPE);

  private boolean initialized;
  private URL modelUrl;
  private boolean available;

  public Set<MediaType> getSupportedTypes(ParseContext parseContext) {
    return SUPPORTED_TYPES;
  }

  /**
   * Initializes this parser
   * @param modelUrl the URL to NER model
   */
  public void initialize(URL modelUrl) {
    if (this.modelUrl != null && this.modelUrl.equals(modelUrl)) {
      // Previously initialized for the same URL, no initialization needed
      return;
    }

    this.modelUrl = modelUrl;
    initialized = true;
  }

  public void parse(InputStream stream, ContentHandler handler,
    Metadata metadata, ParseContext context) throws IOException, SAXException, TikaException {

  }

}
