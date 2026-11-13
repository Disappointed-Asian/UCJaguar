import snap
import random
import time
import heapq

for i in range(50):

    nodes = random.randint(100,1000)          #Nodes
    edges = random.randint(nodes, nodes*(nodes-1)/2)       #Sparse graphs = nodes but less than half of the max. Dense graphs = more than half the max

    G = snap.GenRndGnm(
        snap.TNEANet,
        nodes,
        edges
    )

    # Add random weights
    for EI in G.Edges():

        edge_id = EI.GetId()
        weight = random.randint(1,10)

        G.AddIntAttrDatE(
            edge_id,
            weight,
            "weight"
        )


    class Graph:

        def __init__(self, V):

            self.V = V
            self.adj = [[] for _ in range(V)]

        def add_edge(self, u, v, w):

            self.adj[u].append((v,w))
            self.adj[v].append((u,w))


        def prim_mst(self):

            pq = []
            src = 0

            key = [float('inf')] * self.V
            parent = [-1] * self.V
            in_mst = [False] * self.V

            heapq.heappush(
                pq,
                (0,src)
            )

            key[src] = 0

            mst_edges = []
            total_weight = 0


            while pq:

                u = heapq.heappop(
                    pq
                )[1]

                if in_mst[u]:
                    continue

                in_mst[u] = True

                if parent[u] != -1:

                    mst_edges.append(
                        (parent[u],
                         u,
                         key[u])
                    )

                    total_weight += key[u]


                for v, weight in self.adj[u]:

                    if (not in_mst[v] and
                        key[v] > weight):

                        key[v] = weight
                        parent[v] = u

                        heapq.heappush(
                            pq,
                            (key[v],v)
                        )


            return mst_edges,total_weight


    # Build graph from SNAP
    g = Graph(
        G.GetNodes()
    )

    for EI in G.Edges():

        u = EI.GetSrcNId()
        v = EI.GetDstNId()

        edge_id = EI.GetId()

        w = G.GetIntAttrDatE(
            edge_id,
            "weight"
        )

        g.add_edge(
            u,
            v,
            w
        )


    # Prim's Algorithm timing
    start_time = time.perf_counter()

    mst_edges, mst_weight = g.prim_mst()

    end_time = time.perf_counter()


    # Results
    print(
        "Execution Time: "
        + str(end_time-start_time)
    )

    print(
        "Edges: "
        + str(edges)
    )

    print(
        "Nodes: "
        + str(nodes)
    )