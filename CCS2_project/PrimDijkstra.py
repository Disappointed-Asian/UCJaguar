import snap
import random
import time
import heapq
import sys
from openpyxl import Workbook

NUM_TESTS = 50

print("Test,Nodes,Edges,Dijkstra(s),Prim(s)")

# Excel workbook
wb = Workbook()
ws = wb.active
ws.title = "Dijkstra vs Prim"

ws.append([
    "Test",
    "Nodes",
    "Edges",
    "Dijkstra Time (s)",
    "Prim Time (s)"
])


####################################################
# GRAPH
####################################################

class Graph:

    def __init__(self, V):

        self.V = V
        self.adj = [[] for _ in range(V)]

    def add_edge(self, u, v, w):

        # Undirected graph
        self.adj[u].append((v, w))
        self.adj[v].append((u, w))


    ################################################
    # Dijkstra
    ################################################

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


    ################################################
    # Prim
    ################################################

    def prim(self):

        pq = [(0, 0)]

        key = [float("inf")] * self.V
        parent = [-1] * self.V
        in_mst = [False] * self.V

        key[0] = 0

        total_weight = 0

        while pq:

            weight, u = heapq.heappop(pq)

            if in_mst[u]:
                continue

            in_mst[u] = True

            total_weight += weight

            for v, w in self.adj[u]:

                if not in_mst[v] and w < key[v]:

                    key[v] = w
                    parent[v] = u

                    heapq.heappush(
                        pq,
                        (w, v)
                    )

        return total_weight



####################################################
# TEST LOOP
####################################################

for test in range(NUM_TESTS):

    nodes = random.randint(100,500)              

    max_edges = nodes * (nodes - 1) // 2

    edges = random.randint(
        max_edges//2,                 #max_edges // 2 for dense
        max_edges              #max_edges // 2 for sparse
    )

    ################################################
    # Generate graph
    ################################################

    G = snap.GenRndGnm(
        snap.TNEANet,
        nodes,
        edges
    )

    for EI in G.Edges():

        G.AddIntAttrDatE(
            EI.GetId(),
            random.randint(1,10),               #weights (1,100) for low dist, (1,10000 for hight dist)
            "weight"
        )

    ################################################
    # Map node IDs
    ################################################

    node_map = {}

    index = 0

    for NI in G.Nodes():

        node_map[NI.GetId()] = index
        index += 1


    ################################################
    # Build graph
    ################################################

    graph = Graph(nodes)

    for EI in G.Edges():

        u = node_map[EI.GetSrcNId()]
        v = node_map[EI.GetDstNId()]

        w = G.GetIntAttrDatE(
            EI.GetId(),
            "weight"
        )

        graph.add_edge(
            u,
            v,
            w
        )


    ################################################
    # Dijkstra
    ################################################

    start = time.perf_counter()

    graph.dijkstra(0)

    dijkstra_time = (
        time.perf_counter() - start
    )


    ################################################
    # Prim
    ################################################

    start = time.perf_counter()

    graph.prim()

    prim_time = (
        time.perf_counter() - start
    )


    ################################################
    # Results
    ################################################

    print(f"\nTest {test+1}")
    print(f"Nodes: {nodes}")
    print(f"Edges: {edges}")
    print(f"Dijkstra: {dijkstra_time:.6f}s")
    print(f"Prim:     {prim_time:.6f}s")

    ws.append([
        test + 1,
        nodes,
        edges,
        dijkstra_time,
        prim_time
    ])


####################################################
# Save Excel
####################################################

wb.save("dijkstra_vs_prim_execution_times.xlsx")

print("\nResults saved to dijkstra_vs_prim_execution_times.xlsx")