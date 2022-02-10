package de.pw.danskata;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Program {
  private static final String DANS_PATTERN =
      "[\"DADF#AD\",\"EADGHE\",\"CGDGAD\",\"D#BCGBD\",\"DGDGAD\",\"F#ADGHE\",\"HGDGAD\",\"DAC#EHE\",\"CGCGAD\"]";

  public static void main(final String[] args) {
    Tuning[] tunings = TuningsParser.parse(DANS_PATTERN);

    print("cheapest", Sort.sortCheapest(Arrays.stream(tunings).collect(Collectors.toList())));
  }

  private static void print(String method, Tuning[] actual) {
    int sum = 0;

    System.out.println("**** Result using " + method + "****");
    for (int i = 0; i < actual.length; i++) {
      System.out.print(actual[i]);
      if (i > 0) {
        System.out.print(" (");
        int diff = actual[i - 1].diff(actual[i]);
        sum += diff;
        System.out.print(diff);
        System.out.print(")");
      }
      System.out.print(" - ");
    }

    System.out.println("Summe: " + sum);
  }
}
