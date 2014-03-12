package net.opecko.pbj;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/sandwich/stats")
public class SandwichStatsResource {

  private final SandwichStats sandwichStats;

  @Inject
  SandwichStatsResource(final SandwichStats sandwichStats) {
    this.sandwichStats = sandwichStats;
  }

  @GET
  public SandwichStats.StatsSnapshot getStats() {
    return sandwichStats.getStats();
  }

}
