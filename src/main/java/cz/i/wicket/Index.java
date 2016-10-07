package cz.i.wicket;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * @author jan.hadas@i.cz
 */
public class Index extends WebPage {

  public Index () {

    final List<Class<? extends WebPage>> pages = asList(HelloPage.class, SimpleForm.class, FormValidation.class,
        Eshop.class);

    add(new ListView("pagesList", pages) {

      private static final long serialVersionUID = 1L;

      @Override
      protected void populateItem(ListItem item) {
        Class<?> pgClass = (Class<?>) item.getDefaultModelObject();
        BookmarkablePageLink pgLink = new BookmarkablePageLink("pageLink", pgClass);
        pgLink.add(new Label("pageName", pgClass.getSimpleName()));
        item.add(pgLink);
      }
    });
  }
}
