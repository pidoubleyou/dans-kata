package de.pw.danskata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TuningTest {

  @Test
  void parseSimple() {
    final Tuning expected = new Tuning(Note.E, Note.A, Note.D, Note.G, Note.H, Note.E);

    final Tuning actual = Tuning.parse("EADGHE");

    assertEquals(expected, actual);
  }

  @Test
  void parseWithB() {
    final Tuning expected = new Tuning(Note.E, Note.A, Note.D, Note.G, Note.AIS, Note.E);

    final Tuning actual = Tuning.parse("EADGBE");

    assertEquals(expected, actual);
  }

  @Test
  void parseWithSharp() {
    final Tuning expected = new Tuning(Note.FIS, Note.AIS, Note.CIS, Note.GIS, Note.AIS, Note.DIS);

    final Tuning actual = Tuning.parse("F#A#C#G#BD#");

    assertEquals(expected, actual);
  }

  @Test
  void diffSameTuning() {
    final Tuning tuning1 = new Tuning(Note.E, Note.A, Note.D, Note.G, Note.H, Note.E);
    final Tuning tuning2 = new Tuning(Note.E, Note.A, Note.D, Note.G, Note.H, Note.E);

    final int actual = tuning1.diff(tuning2);

    assertEquals(0, actual);
  }

  @Test
  void diffDifferentTuning() {
    final Tuning tuning1 = new Tuning(Note.E, Note.A, Note.D, Note.G, Note.H, Note.E);
    final Tuning tuning2 = new Tuning(Note.D, Note.GIS, Note.DIS, Note.A, Note.A, Note.D);

    final int actual = tuning1.diff(tuning2);

    assertEquals(10, actual);
  }

  @Test
  void diffIsCommutative() {
    final Tuning tuning1 = new Tuning(Note.E, Note.A, Note.D, Note.G, Note.H, Note.E);
    final Tuning tuning2 = new Tuning(Note.E, Note.AIS, Note.D, Note.G, Note.AIS, Note.D);

    final int actual1 = tuning1.diff(tuning2);
    final int actual2 = tuning2.diff(tuning1);

    assertEquals(4, actual2);
    assertEquals(actual1, actual2);
  }
}