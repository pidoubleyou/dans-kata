package de.pw.danskata;

import java.util.Objects;

public class Tuning {

  private final Note string1;
  private final Note string2;
  private final Note string3;
  private final Note string4;
  private final Note string5;
  private final Note string6;

  Tuning(Note string1, Note string2, Note string3, Note string4, Note string5, Note string6) {
    this.string1 = string1;
    this.string2 = string2;
    this.string3 = string3;
    this.string4 = string4;
    this.string5 = string5;
    this.string6 = string6;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Tuning)) {
      return false;
    }
    Tuning tuning = (Tuning) o;
    return string1 == tuning.string1
        && string2 == tuning.string2
        && string3 == tuning.string3
        && string4 == tuning.string4
        && string5 == tuning.string5
        && string6 == tuning.string6;
  }

  @Override
  public int hashCode() {
    return Objects.hash(string1, string2, string3, string4, string5, string6);
  }

  @Override
  public String toString() {
    return string1.toString() + string2.toString() + string3.toString() + string4.toString() + string5.toString() + string6.toString();
  }

  public static Tuning parse(String tuning) {
    final char[] chars = tuning.toCharArray();
    final Note[] notes = new Note[6];
    int noteIndex = 0;

    for (final char actualChar : chars) {
      if (actualChar == '#') {
        notes[noteIndex - 1] = notes[noteIndex - 1].increaseHalf();
      } else {
        notes[noteIndex] = Note.valueOf(actualChar);
        noteIndex++;
      }
    }
    return new Tuning(notes[0], notes[1], notes[2], notes[3], notes[4], notes[5]);
  }

  public int diff(Tuning other) {
    return this.string1.diff(other.string1)
        + this.string2.diff(other.string2)
        + this.string3.diff(other.string3)
        + this.string4.diff(other.string4)
        + this.string5.diff(other.string5)
        + this.string6.diff(other.string6);
  }
}
