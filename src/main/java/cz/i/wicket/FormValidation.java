package cz.i.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

import cz.i.wicket.model.FormInputModel;

/**
 * @author jan.hadas@i.cz
 */
public class FormValidation extends WebPage {

  private static final String FEEDBACK_COMPONENT_WICKET_ID = "feedback";
  private static final String FORM_COMPONENT_WICKET_ID = "form";
  private static final String TEXT_INPUT_COMPONENT_WICKET_ID = "text";
  private static final String NUMBER_INPUT_COMPONENT_WICKET_ID = "number";
  private static final String DISPLAY_TEXT_COMPONENT_WICKET_ID = "textOutput";
  private static final String DISPLAY_COMPOSED_RESULT_COMPONENT_WICKET_ID = "output";
  private static final int MIN_VALUE = 1;
  private static final int MAX_VALUE = 100;

  public FormValidation() {
    // compound model - uses wicket IDs for storing/getting values
    final CompoundPropertyModel<FormInputModel> model = new CompoundPropertyModel<FormInputModel>(new FormInputModel());

    // Construct form and feedback panel and hook them up
    final FeedbackPanel feedback = new FeedbackPanel(FEEDBACK_COMPONENT_WICKET_ID);
    add(feedback);
    setDefaultModel(model);

    final Form<?> form = new InputForm(FORM_COMPONENT_WICKET_ID, model);
    add(form);

    // display just the text - stringInput by property model
    add(new Label(DISPLAY_TEXT_COMPONENT_WICKET_ID, new PropertyModel(model.getObject(),  TEXT_INPUT_COMPONENT_WICKET_ID)));
    // display output from method getOutput() (text + number - formatted)
    add(new Label(DISPLAY_COMPOSED_RESULT_COMPONENT_WICKET_ID));
  }

  /**
   *
   */
  private class InputForm extends Form {

    private InputForm(String id, IModel model) {
      super(id, model);

      add(new TextField<String>(TEXT_INPUT_COMPONENT_WICKET_ID).setRequired(true));

      add(new TextField<Integer>(NUMBER_INPUT_COMPONENT_WICKET_ID, Integer.class).setRequired(true)
          .add(new RangeValidator<Integer>(MIN_VALUE, MAX_VALUE)));
    }

  }
}
