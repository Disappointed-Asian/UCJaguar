import snap
import random
import time
import heapq
import sys
from openpyxl import Workbook

NUM_TESTS = 50

print("Test,Nodes,Edges,Dijkstra(s),BellmanFord(s)")

# Create Excel workbook
wb = Workbook()
ws = wb.active
ws.title = "Shortest Path Results"

# Header row
ws.append([
    "Test",
    "Nodes",
    "Edges",
    "Dijkstra Time (s)",
    "Bellman-Ford Time (s)"
])

for test in range(NUM_TESTS):

    nodes = random.randint(100, 500)

    max_edges = nodes * (nodes - 1) // 2

    edges = random.randint(
        nodes,          # max_edges//2 for dense graphs only
        max_edges       # max_edges//2 for sparse graphs only
    )

    G = snap.GenRndGnm(
        snap.TNEANet,
        nodes,
        edges
    )

    ####################################################
    # Assign random weights
    ####################################################

    for EI in G.Edges():

        edge_id = EI.GetId()

        weight = random.randint(
            1,
            100
        )                               #1, 100 for low distribution and 1, 10000 for high distribution

        G.AddIntAttrDatE(
            edge_id,
            weight,
            "weight"
        )

    ####################################################
    # Map SNAP node IDs
    ####################################################

    node_map = {}

    index = 0

    for NI in G.Nodes():

        node_map[NI.GetId()] = index
        index += 1

    ####################################################
    # Dijkstra Graph
    ####################################################

    class DijkstraGraph:

        def __init__(self, V):

            self.V = V
            self.adj = [[] for _ in range(V)]

        def add_edge(self, u, v, w):

            self.adj[u].append((v, w))
            self.adj[v].append((u, w))

        def dijkstra(self, src):

            dist = [sys.maxsize] * self.V
            dist[src] = 0

            pq = [(0, src)]

            while pq:

                d, u = heapq.heappop(pq)

                if d > dist[u]:
                    continue

                for v, w in self.adj[u]:

                    if dist[u] + w < dist[v]:

                        dist[v] = dist[u] + w

                        heapq.heappush(
                            pq,
                            (dist[v], v)
                        )

            return dist

    ####################################################
    # Bellman-Ford
    ####################################################

    def bellmanFord(V, edges, src):

        INF = 100000000

        dist = [INF] * V
        dist[src] = 0

        for i in range(V):

            updated = False

            for u, v, wt in edges:

                if dist[u] != INF and dist[u] + wt < dist[v]:

                    if i == V - 1:
                        return [-1]

                    dist[v] = dist[u] + wt
                    updated = True

            # Early stopping if no updates
            if not updated:
                break

        return dist

    ####################################################
    # Build Graphs
    ####################################################

    dijkstra_graph = DijkstraGraph(nodes)

    bellman_edges = []

    for EI in G.Edges():

        u = node_map[EI.GetSrcNId()]
        v = node_map[EI.GetDstNId()]

        w = G.GetIntAttrDatE(
            EI.GetId(),
            "weight"
        )

        dijkstra_graph.add_edge(u, v, w)

        bellman_edges.append(
            [u, v, w]
        )

    ####################################################
    # Time Dijkstra
    ####################################################

    start = time.perf_counter()

    dijkstra_graph.dijkstra(0)

    dijkstra_time = (
        time.perf_counter() - start
    )

    ####################################################
    # Time Bellman-Ford
    ####################################################

    start = time.perf_counter()

    bellmanFord(
        nodes,
        bellman_edges,
        0
    )

    bellman_time = (
        time.perf_counter() - start
    )

    ####################################################
    # Results
    ####################################################

    print(
        f"Test: {test+1},\n"
        f"Nodes: {nodes},\n"
        f"Edges: {edges},\n"
        f"Dijkstra: {dijkstra_time:.6f},\n"
        f"Bellman-Ford: {bellman_time:.6f}\n"
    )

    ws.append([
        test + 1,
        nodes,
        edges,
        dijkstra_time,
        bellman_time
    ])

####################################################
# Save Excel
####################################################

wb.save("shortest_path_execution_times.xlsx")

print("\nResults saved to shortest_path_execution_times.xlsx")