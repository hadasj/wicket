package cz.i.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author jan.hadas@i.cz
 */
public class HelloPage extends WebPage {
  private static final String LABEL_COMPONENT_WICKET_ID = "message";

  public HelloPage() {
    add(new Label(LABEL_COMPONENT_WICKET_ID,
        "Hello world!!!   :-)"));
  }
}
