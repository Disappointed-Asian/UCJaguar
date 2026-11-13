import snap
import random
import time
import heapq
import sys

for i in range(50):

    nodes = random.randint(1000,10000)
    edges = random.randint(100000,1000000)

    G = snap.GenRndGnm(
        snap.TNEANet,
        nodes,
        edges
    )


    # Add random weights
    for EI in G.Edges():

        edge_id = EI.GetId()

        weight = random.randint(
            1,
            10
        )

        G.AddIntAttrDatE(
            edge_id,
            weight,
            "weight"
        )


    class Graph:

        def __init__(self,V):

            self.V = V
            self.adj = [[] for _ in range(V)]


        def add_edge(
            self,
            u,
            v,
            w
        ):

            self.adj[u].append(
                (v,w)
            )

            self.adj[v].append(
                (u,w)
            )


        def dijkstra(
            self,
            src
        ):

            pq = []

            dist = [
                sys.maxsize
            ] * self.V


            dist[src] = 0

            heapq.heappush(
                pq,
                (0,src)
            )


            while pq:

                d,u = heapq.heappop(
                    pq
                )


                if d > dist[u]:
                    continue


                for v,w in self.adj[u]:

                    if (dist[u] + w
                        < dist[v]):

                        dist[v] = (
                            dist[u] + w
                        )

                        heapq.heappush(
                            pq,
                            (
                                dist[v],
                                v
                            )
                        )

            return dist



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


    # Dijkstra timing
    start_time = time.perf_counter()

    shortest_distances = g.dijkstra(0)

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