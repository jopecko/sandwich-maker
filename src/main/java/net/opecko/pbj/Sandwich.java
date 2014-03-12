package net.opecko.pbj;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sandwich {

  private int gramsOfPeanutButter;
  private int gramsOfJam;

  void addPeanutButter(final int grams) {
    gramsOfPeanutButter += grams;
  }

  void addJam(final int grams) {
    gramsOfJam += grams;
  }

  @JsonProperty
  public int getGramsOfPeanutButter() {
    return gramsOfPeanutButter;
  }

  @JsonProperty
  public int getGramsOfJam() {
    return gramsOfJam;
  }

}
