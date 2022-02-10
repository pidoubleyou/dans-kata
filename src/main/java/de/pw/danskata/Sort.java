package de.pw.danskata;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.tour.*;
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
    NearestNeighborHeuristicTSP<Tuning, DefaultWeightedEdge> alg =
        new NearestNeighborHeuristicTSP<>(tunings.get(0));
    return sort(tunings, alg);
  }


  public static Tuning[] sortChristofidisTSP(List<Tuning> tunings) {
    ChristofidesThreeHalvesApproxMetricTSP<Tuning, DefaultWeightedEdge> alg =
            new ChristofidesThreeHalvesApproxMetricTSP<>();
    return sort(tunings, alg);
  }

  public static Tuning[] sortPalmerHamiltonian(List<Tuning> tunings) {
    PalmerHamiltonianCycle<Tuning, DefaultWeightedEdge> alg = new PalmerHamiltonianCycle<>();
    return sort(tunings, alg);
  }

  public static Tuning[] sortNearestInsertion(List<Tuning> tunings) {
    NearestInsertionHeuristicTSP<Tuning, DefaultWeightedEdge> alg = new NearestInsertionHeuristicTSP<>();
    return sort(tunings, alg);
  }

  public static Tuning[] sortHeldKarp(List<Tuning> tunings) {
    HeldKarpTSP<Tuning, DefaultWeightedEdge> alg = new HeldKarpTSP<>();
    return sort(tunings, alg);
  }

  public static Tuning[] sortGreedyHeuristicTSP(List<Tuning> tunings) {
    GreedyHeuristicTSP<Tuning, DefaultWeightedEdge> alg = new GreedyHeuristicTSP<>();
    return sort(tunings, alg);
  }

  private static Tuning[] sort(List<Tuning> tunings, HamiltonianCycleAlgorithmBase<Tuning, DefaultWeightedEdge> alg) {
    final SimpleWeightedGraph<Tuning, DefaultWeightedEdge> graph = createGraph(tunings);

    final GraphPath<Tuning, DefaultWeightedEdge> tour = alg.getTour(graph);
    List<Tuning> vertexList = reorder(tour.getVertexList(), tunings.get(0));
    return vertexList.toArray(Tuning[]::new);
  }

  private static List<Tuning> reorder(List<Tuning> vertexList, Tuning start) {
    final int startIndex = vertexList.indexOf(start);
    if (startIndex == 0) {
      return vertexList.subList(0, vertexList.size() - 1);
    }

    List<Tuning> reorderList = new ArrayList<>();
    reorderList.addAll(vertexList.subList(startIndex, vertexList.size()));
    reorderList.addAll(vertexList.subList(1, startIndex));
    return reorderList;
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
