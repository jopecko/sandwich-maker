package net.opecko.pbj;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Singleton;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.Inject;
import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.Gauge;
import com.yammer.metrics.core.MetricsRegistry;

@ThreadSafe
@Singleton
public class SandwichStats {

  private int sandwichesMade;
  private int gramsOfJam;
  private int gramsOfPeanutButter;
  private final Counter jamCounter;
  private final Counter pbCounter;

  @Inject
  SandwichStats(final MetricsRegistry metricRegistry) {
    metricRegistry.newGauge(SandwichStats.class, "sandwich", "count",
      new Gauge<Integer>() {
        @Override
        public Integer value() {
          return sandwichesMade;
        }
      }
    );
    jamCounter = metricRegistry.newCounter(SandwichStats.class, "grams-of-jam");
    pbCounter = metricRegistry.newCounter(SandwichStats.class, "grams-of-pb");
  }

  synchronized void record(final Sandwich sandwich) {
    sandwichesMade++;
    gramsOfJam += sandwich.getGramsOfJam();
    gramsOfPeanutButter += sandwich.getGramsOfPeanutButter();

    jamCounter.inc(sandwich.getGramsOfJam());
    pbCounter.inc(sandwich.getGramsOfPeanutButter());
  }

  synchronized StatsSnapshot getStats() {
    return new StatsSnapshot(sandwichesMade, gramsOfJam, gramsOfPeanutButter);
  }

  public static final class StatsSnapshot {

    private final int sandwichesMade;
    private final int gramsOfJam;
    private final int gramsOfPeanutButter;

    private StatsSnapshot(final int sandwichesMade, final int gramsOfJam, final int gramsOfPeanutButter) {
      this.sandwichesMade = sandwichesMade;
      this.gramsOfJam = gramsOfJam;
      this.gramsOfPeanutButter = gramsOfPeanutButter;
    }

    @JsonProperty
    public int getSandwichesMade() {
      return sandwichesMade;
    }

    @JsonProperty
    public int getGramsOfJam() {
      return gramsOfJam;
    }

    @JsonProperty
    public int getGramsOfPeanutButter() {
      return gramsOfPeanutButter;
    }
  }

}
