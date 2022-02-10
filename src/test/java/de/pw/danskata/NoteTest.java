package de.pw.danskata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NoteTest {

  @Test
  void diff() {
    assertEquals(1, Note.F.diff(Note.E));
    assertEquals(1, Note.E.diff(Note.F));
    assertEquals(4, Note.E.diff(Note.GIS));
    assertEquals(4, Note.A.diff(Note.F));
  }

  @Test
  void diffOverflow() {
    assertEquals(1, Note.H.diff(Note.C));
    assertEquals(2, Note.AIS.diff(Note.C));
    assertEquals(5, Note.A.diff(Note.D));
  }

  @Test
  void diffReturnsZeroIfMatching() {
    assertEquals(0, Note.E.diff(Note.E));
  }
}
