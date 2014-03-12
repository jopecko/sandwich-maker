package net.opecko.pbj;

import javax.inject.Inject;

public class SandwichMaker {

  private final PeanutButter peanutButter;

  @Inject
  public SandwichMaker(final PeanutButter peanutButter) {
    this.peanutButter = peanutButter;
  }

  Sandwich make(final int gramsOfPeanutButter, final int gramsOfJam) {
    Sandwich sandwich = new Sandwich();
    peanutButter.apply(sandwich, gramsOfPeanutButter);
    sandwich.addJam(gramsOfJam);
    return sandwich;
  }

}
