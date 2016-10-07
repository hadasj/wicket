package cz.i.wicket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

import cz.i.wicket.model.EshopModel;
import cz.i.wicket.model.FormInputModel;
import cz.i.wicket.store.Commodity;

/**
 * @author jan.hadas@i.cz
 */
public class Eshop extends WebPage {

  private static final String HONEY_CAKE_WICKET_ID = "honeyCake";
  private static final String CHOCOLATE_CAKE_WICKET_ID = "chocolateCake";
  private static final String MARZIPAN_CAKE_WICKET_ID = "marzipanCake";
  private static final String BLUEBERRY_PIE_WICKET_ID = "blueberryPie";
  private static final String CINNAMON_SNAIL_WICKET_ID = "cinnamonSnail";
  private static final String FEEDBACK_COMPONENT_WICKET_ID = "feedback";
  private static final String OUTPUT_MESSAGE_WICKET_ID = "output";
  private static final String FORM_COMPONENT_WICKET_ID = "form";

  private static final Commodity HONEY_CAKE = new Commodity("Medovník", BigDecimal.valueOf(200));
  private static final Commodity CHOCOLATE_CAKE = new Commodity("Čokoládový dort", BigDecimal.valueOf(135));
  private static final Commodity MARZIPAN_CAKE = new Commodity("Marcipánový zákusek", BigDecimal.valueOf(25));
  private static final Commodity BLUEBERRY_PIE = new Commodity("Borůvkový koláček", BigDecimal.valueOf(45));
  private static final Commodity CINNAMON_SNAIL = new Commodity("Skořicový šnek", BigDecimal.valueOf(15));

  private static final String FILE_PATH = "/home/honza/";

  // compound model - uses wicket IDs for storing/getting values
  private final CompoundPropertyModel<EshopModel> model = new CompoundPropertyModel<EshopModel>(new EshopModel());

  public Eshop() {
    // Construct form and feedback panel and hook them up
    final FeedbackPanel feedback = new FeedbackPanel(FEEDBACK_COMPONENT_WICKET_ID);

    add(feedback);
    setDefaultModel(model);

    final Form<?> form = new InputForm(FORM_COMPONENT_WICKET_ID, model);
    add(form);

    // display output message
    add(new Label(OUTPUT_MESSAGE_WICKET_ID));
  }

  private class InputForm extends Form {

    private InputForm(String id, IModel model) {
      super(id, model);

      add(new TextField<BigDecimal>(HONEY_CAKE_WICKET_ID, BigDecimal.class)
          .add(new RangeValidator<BigDecimal>(BigDecimal.ZERO, new BigDecimal(Integer.MAX_VALUE))));
      add(new TextField<BigDecimal>(CHOCOLATE_CAKE_WICKET_ID, BigDecimal.class)
          .add(new RangeValidator<BigDecimal>(BigDecimal.ZERO, new BigDecimal(Integer.MAX_VALUE))));
      add(new TextField<BigDecimal>(MARZIPAN_CAKE_WICKET_ID, BigDecimal.class)
          .add(new RangeValidator<BigDecimal>(BigDecimal.ZERO, new BigDecimal(Integer.MAX_VALUE))));
      add(new TextField<BigDecimal>(BLUEBERRY_PIE_WICKET_ID, BigDecimal.class)
          .add(new RangeValidator<BigDecimal>(BigDecimal.ZERO, new BigDecimal(Integer.MAX_VALUE))));
      add(new TextField<BigDecimal>(CINNAMON_SNAIL_WICKET_ID, BigDecimal.class)
          .add(new RangeValidator<BigDecimal>(BigDecimal.ZERO, new BigDecimal(Integer.MAX_VALUE))));
    }

    @Override
    protected void onSubmit() {
      try {
        final String fileName = FILE_PATH + "order_" + new Date().getTime() + ".txt";
        final PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        writer.println("***  ORDER  ***");
        writer.println();

        final EshopModel eshopModel = model.getObject();

        if (eshopModel.getHoneyCake() != null) {
          writer.println(HONEY_CAKE.getName() + ", " + HONEY_CAKE.getDescription() + " - " +
          HONEY_CAKE.getPrice().multiply(new BigDecimal(1 + HONEY_CAKE.getTaxRate()/100.0))
              .multiply(new BigDecimal(eshopModel.getHoneyCake())).round(new MathContext(2,
              RoundingMode.HALF_UP)).toPlainString() + "Kč");
        }

        writer.close();
      } catch (IOException e) {
        throw new IllegalStateException("Couldn't write order to filesystem", e);
      }



      model.getObject().setOutput("Děkujeme za vaši objednávku");
    }
  }
}
