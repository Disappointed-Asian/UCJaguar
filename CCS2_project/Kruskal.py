import snap
import random
import time

for i in range (50):
    nodes = random.randint(100,1000)          #Nodes
    edges = random.randint(nodes, nodes*(nodes-1)/2)       #Sparse graphs = nodes but less than half of the max. Dense graphs = more than half the max

    G = snap.GenRndGnm(snap.TNEANet, nodes, edges)

    # Add weight attribute to each edge
    for EI in G.Edges():
        edge_id = EI.GetId()
        weight = random.randint(1, 10)

        # Store edge weight in SNAP
        G.AddIntAttrDatE(edge_id, weight, "weight")

    class Graph:
        def __init__(self, vertices):
            self.V = vertices
            self.graph = []

        def add_edge(self, u, v, w):
            self.graph.append((u, v, w))

        def find(self, parent, i):
            if parent[i] != i:
                parent[i] = self.find(parent, parent[i])
            return parent[i]

        def apply_union(self, parent, rank, x, y):
            xroot = self.find(parent, x)
            yroot = self.find(parent, y)

            if rank[xroot] < rank[yroot]:
                parent[xroot] = yroot
            elif rank[xroot] > rank[yroot]:
                parent[yroot] = xroot
            else:
                parent[yroot] = xroot
                rank[xroot] += 1

        def kruskal_algo(self):
            result = []
            i, e = 0, 0

            self.graph = sorted(self.graph,
                                key=lambda item: item[2])

            parent = list(range(self.V))
            rank = [0] * self.V

            while e < self.V - 1 and i < len(self.graph):
                u, v, w = self.graph[i]
                i += 1

                x = self.find(parent, u)
                y = self.find(parent, v)

                if x != y:
                    e += 1
                    result.append([u, v, w])
                    self.apply_union(parent, rank, x, y)
            ###for testing
            # print("\nMinimum Spanning Tree")
            # total = 0

            # for u, v, w in result:
            #     print(f"{u} - {v}: {w}")
            #     total += w

            # print("Total weight:", total)


    # Build Graph from SNAP
    g = Graph(G.GetNodes())

    for EI in G.Edges():
        u = EI.GetSrcNId()
        v = EI.GetDstNId()

        edge_id = EI.GetId()
        w = G.GetIntAttrDatE(edge_id, "weight")

        g.add_edge(u, v, w)

    #Kruskal's Algorithm
    start_time = time.perf_counter()
    g.kruskal_algo()
    end_time = time.perf_counter()

    #Results
    print("Execution Time: " + str(end_time-start_time))
    print("Edges: " + str(edges))
    print("Nodes: " + str(nodes))