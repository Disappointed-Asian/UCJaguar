import snap
import random
import time

for i in range(50):
    nodes = random.randint(100, 1000)
    edges = random.randint(1000, 10000)

    G = snap.GenRndGnm(snap.TNEANet, nodes, edges)

    # Add random weights to edges
    for EI in G.Edges():
        edge_id = EI.GetId()
        weight = random.randint(1, 10)

        G.AddIntAttrDatE(edge_id, weight, "weight")


    class Graph:
        def __init__(self, vertices):
            self.V = vertices
            self.graph = []

        def add_edge(self, u, v, w):
            self.graph.append([u, v, w])

        def find(self, parent, i):
            if parent[i] == i:
                return i
            return self.find(parent, parent[i])

        def union(self, parent, rank, x, y):
            root_x = self.find(parent, x)
            root_y = self.find(parent, y)

            if rank[root_x] < rank[root_y]:
                parent[root_x] = root_y
            elif rank[root_x] > rank[root_y]:
                parent[root_y] = root_x
            else:
                parent[root_y] = root_x
                rank[root_x] += 1


        def boruvka_mst(self):
            parent = list(range(self.V))
            rank = [0] * self.V

            mst_weight = 0
            num_of_trees = self.V
            mst_edges = []

            while num_of_trees > 1:

                cheapest = [-1] * self.V

                # Find cheapest edge for each component
                for u, v, w in self.graph:

                    set1 = self.find(parent, u)
                    set2 = self.find(parent, v)

                    if set1 != set2:

                        if (cheapest[set1] == -1 or
                                cheapest[set1][2] > w):
                            cheapest[set1] = [u, v, w]

                        if (cheapest[set2] == -1 or
                                cheapest[set2][2] > w):
                            cheapest[set2] = [u, v, w]

                # Add selected edges to MST
                for node in range(self.V):

                    if cheapest[node] != -1:

                        u, v, w = cheapest[node]

                        set1 = self.find(parent, u)
                        set2 = self.find(parent, v)

                        if set1 != set2:

                            mst_weight += w
                            mst_edges.append([u, v, w])

                            self.union(parent, rank,
                                       set1, set2)

                            num_of_trees -= 1

            return mst_edges, mst_weight


    # Build Graph from SNAP
    g = Graph(G.GetNodes())

    for EI in G.Edges():
        u = EI.GetSrcNId()
        v = EI.GetDstNId()

        edge_id = EI.GetId()
        w = G.GetIntAttrDatE(edge_id, "weight")

        g.add_edge(u, v, w)


    # Boruvka Algorithm
    start_time = time.perf_counter()

    mst_edges, mst_weight = g.boruvka_mst()

    end_time = time.perf_counter()


    # Results
    print("Execution Time: " + str(end_time - start_time))
    print("Edges: " + str(edges))
    print("Nodes: " + str(nodes))