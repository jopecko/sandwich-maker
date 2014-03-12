package net.opecko.pbj;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

import net.opecko.http.HttpServer;
import net.opecko.http.jersey.JerseyModule;

public class TestServer extends HttpServer {

  public TestServer(final String[] args) {
    super(args);
  }

  @Override
  protected List<Module> getModules() {
    return ImmutableList.<Module>builder()
      .addAll(super.getModules())
      .add(new JerseyModule())
      .add(new Module() {

        @Override
        public void configure(final Binder binder) {
          binder.bind(SandwichStatsResource.class);
          binder.bind(SandwichMakerResource.class);

          binder.bind(PeanutButter.class).to(OrganicCrunchyValenciaPeanutButter.class).in(Scopes.SINGLETON);
          binder.bind(SandwichMaker.class);
          binder.bind(SandwichStats.class);
        }

      })
      .build();
  }

  public static void main(final String[] args) {
    System.setProperty("jetty.home", System.getProperty("java.io.tmpdir"));
    new TestServer(args).main();
  }

}
