package opennlp.tools.formats.pan.pan16;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by anthony on 6/7/16.
 */
public class PAN16ReaderTest {

  /**
   * Test for PAN16 reader
   * */
  @Test
  public void testReader(){
    ArrayList<PAN16Author> authors = PAN16Reader.getPAN16Authors(0, 19);
    assertEquals("Check the number of authors ", 20, authors.size());
  }


}
