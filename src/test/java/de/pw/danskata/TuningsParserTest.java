package de.pw.danskata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TuningsParserTest {

  @Test
  void parseNullReturnsEmptyArray() {
    assertArrayEquals(new Tuning[0], TuningsParser.parse(null));
  }

  @Test
  void parseEmptyStringReturnsEmptyArray() {
    assertArrayEquals(new Tuning[0], TuningsParser.parse(""));
  }

  @Test
  void parseEmptyArrayReturnsEmptyArray() {
    assertArrayEquals(new Tuning[0], TuningsParser.parse("[]"));
  }

  @Test
  void parseOneTuning() {
    Tuning[] expected = new Tuning[] {
            new Tuning(Note.E, Note.A, Note.D, Note.G, Note.H, Note.E)
    };
    assertArrayEquals(expected, TuningsParser.parse("[\"EADGHE\"]"));
  }

  @Test
  void parseTwoTuning() {
    Tuning[] expected = new Tuning[] {
            new Tuning(Note.E, Note.A, Note.D, Note.GIS, Note.H, Note.E),
            new Tuning(Note.F, Note.A, Note.C, Note.G, Note.H, Note.E)
    };
    assertArrayEquals(expected, TuningsParser.parse("[\"EADG#HE\",\"FACGHE\"]"));
  }
}