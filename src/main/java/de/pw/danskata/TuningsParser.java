package de.pw.danskata;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TuningsParser {

  private static final Pattern TUNING_PATTERN = Pattern.compile("\"([\\w#]+)\"");
  private TuningsParser() {}

  public static Tuning[] parse(String tunings) {
    if (tunings == null || tunings.isEmpty()) {
      return new Tuning[0];
    }

    List<Tuning> result = new ArrayList<>();
    final Matcher matcher = TUNING_PATTERN.matcher(tunings);
    while (matcher.find()) {
      result.add(Tuning.parse(matcher.group(1)));
    }

    return result.toArray(Tuning[]::new);
  }
}
