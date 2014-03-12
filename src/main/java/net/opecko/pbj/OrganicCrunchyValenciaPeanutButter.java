package net.opecko.pbj;

public class OrganicCrunchyValenciaPeanutButter implements PeanutButter {

  @Override
  public void apply(final Sandwich sandwich, final int grams) {
    sandwich.addPeanutButter(grams);
  }

}
