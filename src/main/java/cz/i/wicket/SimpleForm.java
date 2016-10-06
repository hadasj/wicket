package cz.i.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

/**
 * @author jan.hadas@i.cz
 */
public class SimpleForm extends WebPage {

  private static final String LABEL_COMPONENT_WICKET_ID = "message";
  private static final String FORM_COMPONENT_WICKET_ID = "form";
  private static final String TEXT_INPUT_COMPONENT_WICKET_ID = "input";

  private String message;

  /**
   * Constructor.
   */
  public SimpleForm()
  {
    // This model references the page's message property and is
    // shared by the label and form component
    final PropertyModel<String> messageModel = new PropertyModel<String>(this, "message");

    // The label displays the currently set message
    add(new Label(LABEL_COMPONENT_WICKET_ID, messageModel));

    // Add a form to change the message.
    // We don't need to do anything else with this form as the shared model is automatically updated on form submit
    final Form<?> form = new Form(FORM_COMPONENT_WICKET_ID);
    form.add(new TextField<String>(TEXT_INPUT_COMPONENT_WICKET_ID, messageModel));
    add(form);
  }

  /**
   * @return the message
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * @param message
   *            the message to set
   */
  public void setMessage(String message)
  {
    this.message = message;
  }
}
