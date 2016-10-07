package cz.i.wicket.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jan.hadas@i.cz
 */
public class FormInputModelTest {
  private FormInputModel modelUnderTest;

  @Before
  public void setUp() {
    modelUnderTest = new FormInputModel();
  }

  @Test
  public void should_format_2_params() {
    modelUnderTest.setText("Jaromír Jágr");
    modelUnderTest.setNumber(68);

    final String output = modelUnderTest.getOutput();

    assertNotNull(output);
    assertEquals("Jaromír Jágr, 68", output);
  }

  @Test
  public void should_format_empty_model() {
    final String output = modelUnderTest.getOutput();

    assertNotNull(output);
    assertEquals("", output);
  }

  @Test
  public void should_format_text_only() {
    modelUnderTest.setText("Hanz Hagen");
    final String output = modelUnderTest.getOutput();

    assertNotNull(output);
    assertEquals("Hanz Hagen", output);
  }

  @Test
  public void should_format_number_only() {
    modelUnderTest.setNumber(1_000_000);
    final String output = modelUnderTest.getOutput();

    assertNotNull(output);
    assertEquals("1000000", output);
  }
}
