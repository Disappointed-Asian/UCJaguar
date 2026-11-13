import snap
import random
import time
import heapq
from openpyxl import Workbook


class MSTGraph:
    def __init__(self, V):
        self.V = V
        self.edge_list = []      # For Kruskal/Boruvka
        self.adj = [[] for _ in range(V)]  # For Prim

    def add_edge(self, u, v, w):
        self.edge_list.append((u, v, w))

        self.adj[u].append((v, w))
        self.adj[v].append((u, w))


    #################################################
    # KRUSKAL
    #################################################

    def kruskal(self):

        parent = list(range(self.V))
        rank = [0] * self.V

        def find(i):
            if parent[i] != i:
                parent[i] = find(parent[i])
            return parent[i]

        def union(x, y):

            xroot = find(x)
            yroot = find(y)

            if rank[xroot] < rank[yroot]:
                parent[xroot] = yroot

            elif rank[xroot] > rank[yroot]:
                parent[yroot] = xroot

            else:
                parent[yroot] = xroot
                rank[xroot] += 1


        edges = sorted(
            self.edge_list,
            key=lambda x: x[2]
        )

        mst = []
        total_weight = 0

        for u, v, w in edges:

            x = find(u)
            y = find(v)

            if x != y:

                mst.append((u, v, w))
                total_weight += w

                union(x, y)

                if len(mst) == self.V - 1:
                    break

        return mst, total_weight


    #################################################
    # PRIM
    #################################################

    def prim(self):

        pq = [(0, 0)]

        key = [float('inf')] * self.V
        parent = [-1] * self.V
        in_mst = [False] * self.V

        key[0] = 0

        mst = []
        total_weight = 0

        while pq:

            current_weight, u = heapq.heappop(pq)

            if in_mst[u]:
                continue

            in_mst[u] = True

            if parent[u] != -1:

                mst.append(
                    (parent[u], u, current_weight)
                )

                total_weight += current_weight


            for v, weight in self.adj[u]:

                if not in_mst[v] and weight < key[v]:

                    key[v] = weight
                    parent[v] = u

                    heapq.heappush(
                        pq,
                        (weight, v)
                    )

        return mst, total_weight


    #################################################
    # BORUVKA
    #################################################

    def boruvka(self):

        parent = list(range(self.V))
        rank = [0] * self.V

        def find(i):

            if parent[i] != i:
                parent[i] = find(parent[i])

            return parent[i]


        def union(x, y):

            xroot = find(x)
            yroot = find(y)

            if rank[xroot] < rank[yroot]:
                parent[xroot] = yroot

            elif rank[xroot] > rank[yroot]:
                parent[yroot] = xroot

            else:
                parent[yroot] = xroot
                rank[xroot] += 1


        num_trees = self.V
        mst = []
        total_weight = 0


        while num_trees > 1:

            cheapest = [-1] * self.V

            for i, (u, v, w) in enumerate(self.edge_list):

                set1 = find(u)
                set2 = find(v)

                if set1 == set2:
                    continue

                if (cheapest[set1] == -1 or
                        self.edge_list[
                            cheapest[set1]
                        ][2] > w):

                    cheapest[set1] = i


                if (cheapest[set2] == -1 or
                        self.edge_list[
                            cheapest[set2]
                        ][2] > w):

                    cheapest[set2] = i


            for node in range(self.V):

                if cheapest[node] != -1:

                    u, v, w = self.edge_list[
                        cheapest[node]
                    ]

                    set1 = find(u)
                    set2 = find(v)

                    if set1 != set2:

                        mst.append((u, v, w))
                        total_weight += w

                        union(set1, set2)

                        num_trees -= 1

        return mst, total_weight



#################################################
# TESTING LOOP
#################################################
# Create Excel workbook
wb = Workbook()
ws = wb.active
ws.title = "MST Results"

# Header row
ws.append([
    "Test",
    "Nodes",
    "Edges",
    "Kruskal Time (s)",
    "Prim Time (s)",
    "Boruvka Time (s)",
    "Kruskal Weight",
    "Prim Weight",
    "Boruvka Weight"
])

for test in range(50):

    nodes = random.randint(100,500)

    max_edges = nodes * (nodes - 1) // 2

    edges = random.randint(
        nodes,              ##change this only to max_edges//2 to get dense graphs only
        max_edges           ##change this only to max_edges//2 to get sparse graphs only
    )


    # Generate random SNAP graph
    G = snap.GenRndGnm(
        snap.PUNGraph,
        nodes,
        edges
    )


    # Add random weights
    weights = {}

    for EI in G.Edges():

        u = EI.GetSrcNId()
        v = EI.GetDstNId()

        # Keep one ordering because the graph is undirected
        edge = tuple(sorted((u, v)))

        weights[edge] = random.randint(1, 100)      ##Change this from 1,100 to 1,10000 to change weight distribution 


        # Create node ID mapping
        node_map = {}

    index = 0

    for NI in G.Nodes():

        node_map[NI.GetId()] = index
        index += 1


    graph = MSTGraph(nodes)


    # Transfer SNAP graph
    for EI in G.Edges():

        u = node_map[
            EI.GetSrcNId()
        ]

        v = node_map[
            EI.GetDstNId()
        ]

        edge = tuple(sorted((EI.GetSrcNId(), EI.GetDstNId())))

        w = weights[edge]

        graph.add_edge(
            u,
            v,
            w
        )


    #################################################
    # KRUSKAL
    #################################################

    start = time.perf_counter()

    mst1, weight1 = graph.kruskal()

    kruskal_time = (
        time.perf_counter() - start
    )


    #################################################
    # PRIM
    #################################################

    start = time.perf_counter()

    mst2, weight2 = graph.prim()

    prim_time = (
        time.perf_counter() - start
    )


    #################################################
    # BORUVKA
    #################################################

    start = time.perf_counter()

    mst3, weight3 = graph.boruvka()

    boruvka_time = (
        time.perf_counter() - start
    )


    print(f"\nTEST {test+1}")
    print(f"Nodes: {nodes}")
    print(f"Edges: {edges}")

    print(
        f"Kruskal: {kruskal_time:.6f}s"
    )

    print(
        f"Prim:    {prim_time:.6f}s"
    )

    print(
        f"Boruvka: {boruvka_time:.6f}s"
    )

    print(
        f"MST weights -> "
        f"K:{weight1} "
        f"P:{weight2} "
        f"B:{weight3}"
    )

    ws.append([
        test + 1,
        nodes,
        edges,
        kruskal_time,
        prim_time,
        boruvka_time,
        weight1,
        weight2,
        weight3
    ])

wb.save("mst_execution_times.xlsx")
print("\nResults saved to mst_execution_times.xlsx")
