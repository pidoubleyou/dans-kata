package de.pw.danskata;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public enum Note {
  C(1),
  CIS(2),
  D(3),
  DIS(4),
  E(5),
  F(6),
  FIS(7),
  G(8),
  GIS(9),
  A(10),
  AIS(11),
  H(12);

  private static final Map<Integer, Note> BY_VALUE = new HashMap<>();

  static {
    for (Note e : values()) {
      BY_VALUE.put(e.value, e);
    }
  }

  private final int value;

  Note(int value) {
    this.value = value;
  }

  public static Note valueOf(char note) {
    if (note == 'B') {
      return Note.AIS;
    }

    return Note.valueOf(String.valueOf(note));
  }

  public int diff(Note note) {
    return min(abs(this.value - note.value), abs(note.value + 12 - this.value));
  }

  public Note increaseHalf() {
    return BY_VALUE.get(value + 1);
  }

  @Override
  public String toString() {
    return super.toString().replace("IS", "#");
  }
}
