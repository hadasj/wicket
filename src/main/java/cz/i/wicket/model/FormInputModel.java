package cz.i.wicket.model;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * @author jan.hadas@i.cz
 */
public class FormInputModel {
  private String text;
  private Integer number;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getOutput() {
    String output = "";
    if (isNotEmpty(text)) {
      output += trim(text);
    }
    if (number != null) {
      if (isNotEmpty(text)) {
        output += ", ";
      }
      output += number;
    }
    return output;
  }
}
