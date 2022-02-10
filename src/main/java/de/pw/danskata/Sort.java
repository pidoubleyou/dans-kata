package de.pw.danskata;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.tour.NearestNeighborHeuristicTSP;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

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

  public static Tuning[] sortNearestNeighborTSP(List<Tuning> tunings) {
    final SimpleWeightedGraph<Tuning, DefaultWeightedEdge> graph = createGraph(tunings);

    NearestNeighborHeuristicTSP<Tuning, DefaultWeightedEdge> alg =
        new NearestNeighborHeuristicTSP<>(tunings.get(0));

    final GraphPath<Tuning, DefaultWeightedEdge> tour = alg.getTour(graph);
    List<Tuning> vertexList = tour.getVertexList().subList(0, 9);
    return vertexList.toArray(Tuning[]::new);
  }

  private static SimpleWeightedGraph<Tuning, DefaultWeightedEdge> createGraph(
      List<Tuning> tunings) {
    final SimpleWeightedGraph<Tuning, DefaultWeightedEdge> graph =
        new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    for (Tuning tuning : tunings) {
      graph.addVertex(tuning);
    }
    for (int i = 0; i < tunings.size(); i++) {
      for (int j = i + 1; j < tunings.size(); j++) {
        final DefaultWeightedEdge edge = graph.addEdge(tunings.get(i), tunings.get(j));
        graph.setEdgeWeight(edge, tunings.get(i).diff(tunings.get(j)));
      }
    }
    return graph;
  }
}
