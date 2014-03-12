package net.opecko.pbj;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@ThreadSafe
@Path("/api/sandwich/create")
public class SandwichMakerResource {

  private final SandwichMaker sandwichMaker;
  private final SandwichStats stats;

  @Inject
  SandwichMakerResource(final SandwichMaker sandwichMaker, final SandwichStats stats) {
    this.sandwichMaker = sandwichMaker;
    this.stats = stats;
  }

  @GET
  public Sandwich makeSandwich(
      @QueryParam("jam") @DefaultValue("100") final int gramsOfJam,
      @QueryParam("peanutButter") @DefaultValue("200") final int gramsOfPeanutButter
  ) {
    Sandwich sandwich = sandwichMaker.make(gramsOfPeanutButter, gramsOfJam);
    stats.record(sandwich);
    return sandwich;
  }


}
