Problem 1: Find if a given Directed Graph has a cycle in it or not.

Solution: If we can’t determine the topological ordering of all the vertices of a directed graph, the graph has a cycle
in it. This was also referred to in the above code:
    if (sortedOrder.size() != vertices) // topological sort is not possible as the graph has a cycle
      return new ArrayList<>();