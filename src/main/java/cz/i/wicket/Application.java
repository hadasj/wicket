package cz.i.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author jan.hadas@i.cz
 */
public class Application extends WebApplication {

  @Override
  public Class<? extends Page> getHomePage() {
    return Index.class;
  }
}
