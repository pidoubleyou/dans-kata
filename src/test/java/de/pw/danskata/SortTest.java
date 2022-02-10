package de.pw.danskata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SortTest {

  private static List<Tuning> getDansExample() {
    List<Tuning> tunings = new ArrayList<>();
    tunings.add(Tuning.parse("DADF#AD"));
    tunings.add(Tuning.parse("EADGHE"));
    tunings.add(Tuning.parse("CGDGAD"));
    tunings.add(Tuning.parse("D#BCGBD"));
    tunings.add(Tuning.parse("DGDGAD"));
    tunings.add(Tuning.parse("F#ADGHE"));
    tunings.add(Tuning.parse("HGDGAD"));
    tunings.add(Tuning.parse("DAC#EHE"));
    tunings.add(Tuning.parse("CGCGAD"));

    return tunings;
  }

  @Test
  void sortOneTuningReturnsInput() {
    List<Tuning> tunings = new ArrayList<>();
    tunings.add(Tuning.parse("EADGHE"));

    Tuning[] expected = tunings.toArray(Tuning[]::new);
    Tuning[] actual = Sort.sortCheapest(tunings);

    assertArrayEquals(expected, actual);
  }

  @Test
  void sortTwoTuningsReturnsInput() {
    List<Tuning> tunings = new ArrayList<>();
    tunings.add(Tuning.parse("EADGHE"));
    tunings.add(Tuning.parse("CGCGAD"));

    Tuning[] expected = tunings.toArray(Tuning[]::new);
    Tuning[] actual = Sort.sortCheapest(tunings);

    assertArrayEquals(expected, actual);
  }

  @Test
  void sortThreeTunings() {
    List<Tuning> tunings = new ArrayList<>();
    tunings.add(Tuning.parse("EADGHE"));
    tunings.add(Tuning.parse("EHDGCE"));
    tunings.add(Tuning.parse("EADGCE"));

    Tuning[] expected = new Tuning[] { tunings.get(0), tunings.get(2), tunings.get(1) };

    Tuning[] actual = Sort.sortCheapest(tunings);

    assertArrayEquals(expected, actual);
  }
}