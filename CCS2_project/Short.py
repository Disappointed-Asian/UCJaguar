import snap
import random
import time
import heapq
import sys
from openpyxl import Workbook

NUM_TESTS = 50

print("Test,Nodes,Edges,Dijkstra(s),FloydWarshall(s)")

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
    "Floyd-Warshall Time (s)"
])

for test in range(NUM_TESTS):

    nodes = random.randint(50,300)

    max_edges = nodes * (nodes - 1) // 2

    edges = random.randint(
        nodes,              ##change this only to max_edges//2 to get dense graphs only
        max_edges           ##change this only to max_edges//2 to get sparse graphs only
    )

    ############################### Ignore for now ####################################
    # Graph size suitable for BOTH algorithms
    # nodes = random.randint(50, 300)
    # edges = random.randint(nodes * 2, nodes * 8)

    G = snap.GenRndGnm(
        snap.TNEANet,
        nodes,
        edges
    )

    # Assign random weights
    for EI in G.Edges():
        edge_id = EI.GetId()
        weight = random.randint(1, 10)         ##Change this from 1,100 to 1,10000 to change weight distribution 
        G.AddIntAttrDatE(edge_id, weight, "weight")

    ####################################################
    # Map SNAP node IDs to contiguous indices
    ####################################################

    node_map = {}
    reverse_map = {}

    index = 0
    for NI in G.Nodes():
        node_map[NI.GetId()] = index
        reverse_map[index] = NI.GetId()
        index += 1

    ####################################################
    # DIJKSTRA
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

    dijkstra_graph = DijkstraGraph(nodes)

    ####################################################
    # FLOYD WARSHALL
    ####################################################

    class FloydWarshall:

        def __init__(self, V):

            self.V = V

            self.dist = [
                [float("inf")] * V
                for _ in range(V)
            ]

            for i in range(V):
                self.dist[i][i] = 0

        def add_edge(self, u, v, w):
            self.dist[u][v] = w

        def floyd_warshall(self):

            for k in range(self.V):
                for i in range(self.V):

                    dik = self.dist[i][k]

                    if dik == float("inf"):
                        continue

                    for j in range(self.V):

                        if self.dist[k][j] == float("inf"):
                            continue

                        if self.dist[i][j] > dik + self.dist[k][j]:

                            self.dist[i][j] = (
                                dik +
                                self.dist[k][j]
                            )

    fw_graph = FloydWarshall(nodes)

    ####################################################
    # Build BOTH graphs simultaneously
    ####################################################

    for EI in G.Edges():

        u = node_map[EI.GetSrcNId()]
        v = node_map[EI.GetDstNId()]

        w = G.GetIntAttrDatE(
            EI.GetId(),
            "weight"
        )

        dijkstra_graph.add_edge(u, v, w)
        fw_graph.add_edge(u, v, w)

    ####################################################
    # Time Dijkstra
    ####################################################

    start = time.perf_counter()

    dijkstra_graph.dijkstra(0)

    dijkstra_time = (
        time.perf_counter() - start
    )

    ####################################################
    # Time Floyd-Warshall
    ####################################################

    start = time.perf_counter()

    fw_graph.floyd_warshall()

    fw_time = (
        time.perf_counter() - start
    )

    ####################################################
    # Results
    ####################################################

    print(
        f"Test: {test+1},\n"
        f"Nodes: {nodes},\n"
        f"Edges: {edges},\n"
        f"Dijkstra's: {dijkstra_time:.6f},\n"
        f"Floyd-Warshall: {fw_time:.6f}\n"
    )

    ws.append([
        test + 1,
        nodes,
        edges,
        dijkstra_time,
        fw_time
    ])

wb.save("shortest_path_execution_times.xlsx")
print("\nResults saved to shortest_path_execution_times.xlsx")