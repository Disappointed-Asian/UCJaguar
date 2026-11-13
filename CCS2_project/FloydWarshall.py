import snap
import random
import time

for test in range(50):

    # Smaller graph sizes because Floyd-Warshall is O(V³)
    nodes = random.randint(50, 300)
    edges = random.randint(nodes*2, nodes*8)

    # Generate directed graph
    G = snap.GenRndGnm(snap.TNEANet, nodes, edges)

    # Assign random weights
    for EI in G.Edges():
        edge_id = EI.GetId()
        weight = random.randint(1, 10)
        G.AddIntAttrDatE(edge_id, weight, "weight")


    class FloydWarshall:

        def __init__(self, vertices):
            self.V = vertices

            # Distance matrix initialized to infinity
            self.dist = [[float("inf") for _ in range(vertices)]
                         for _ in range(vertices)]

            # Parent matrix for path reconstruction
            self.parent = [[-1 for _ in range(vertices)]
                           for _ in range(vertices)]

            # Distance to itself = 0
            for i in range(vertices):
                self.dist[i][i] = 0


        def add_edge(self, u, v, w):
            self.dist[u][v] = w
            self.parent[u][v] = u


        def floyd_warshall(self):

            for k in range(self.V):
                for i in range(self.V):
                    for j in range(self.V):

                        if (self.dist[i][k] != float("inf") and
                            self.dist[k][j] != float("inf")):

                            if self.dist[i][j] > self.dist[i][k] + self.dist[k][j]:

                                self.dist[i][j] = (
                                    self.dist[i][k] +
                                    self.dist[k][j]
                                )

                                self.parent[i][j] = self.parent[k][j]

            # Check negative cycles
            for i in range(self.V):
                if self.dist[i][i] < 0:
                    print("Negative cycle detected")
                    return False

            return True


        def obtain_path(self, i, j):

            if self.dist[i][j] == float("inf"):
                return "No path"

            path = [j]

            while j != i:
                j = self.parent[i][j]

                if j == -1:
                    return "No path"

                path.append(j)

            path.reverse()

            return " -> ".join(map(str, path))


    # Create graph object
    g = FloydWarshall(G.GetNodes())


    # SNAP node IDs are not always guaranteed contiguous
    node_map = {}
    index = 0

    for NI in G.Nodes():
        node_map[NI.GetId()] = index
        index += 1


    # Add edges from SNAP graph
    for EI in G.Edges():

        u = node_map[EI.GetSrcNId()]
        v = node_map[EI.GetDstNId()]

        edge_id = EI.GetId()
        w = G.GetIntAttrDatE(edge_id, "weight")

        g.add_edge(u, v, w)


    # Measure runtime
    start_time = time.perf_counter()

    g.floyd_warshall()

    end_time = time.perf_counter()

    execution_time = end_time - start_time

    print(
        f"Test {test+1}: "
        f"Nodes={nodes}, "
        f"Edges={edges}, "
        f"Time={execution_time:.6f} sec"
    )

    # Optional: print sample path
    # print(g.obtain_path(0, nodes-1))