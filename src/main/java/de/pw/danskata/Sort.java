package de.pw.danskata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sort {

  private Sort() {}

  public static Tuning[] sortCheapest(List<Tuning> tunings) {

    List<Tuning> sorted = new ArrayList<>();

    Tuning current;
    while (!tunings.isEmpty()) {
      current = tunings.remove(0);
      sorted.add(current);
      tunings.sort(Comparator.comparingInt(current::diff));
    }

    return sorted.toArray(Tuning[]::new);
  }
}
