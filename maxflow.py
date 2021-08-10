from collections import deque
class Edge:
    flow = 0
    def __init__(self, fromedge, toedge, capacity):
        self.frome = fromedge
        self.toedg = toedge
        self.cap = capacity
    def setOppose(self,opposite):
        self.oppos = opposite
def oppositeedges(origin, reverse):#setup bidirectional edges
    origin.setOppose(reverse)
    reverse.setOppose(origin)
def bfs(start, visarr,hashmap,end):
    queue = deque()
    queue.append(start)
    visarr[start]=True
    prevArr = []
    for s in range(end+1):
        prevArr.append(-1)
    while len(queue)!=0:
        node = queue.pop()
        if node == end:
            break
        for s in hashmap[node]:
            if s.cap>0 and visarr[s.toedg] == False:
                visarr[s.toedg]=True
                prevArr[s.toedg]=s
                queue.append(s.toedg)
    if prevArr[end] == -1:
        return 0

    bottleNeck = 10000
    augedge = prevArr[end]
    while True:
        bottleNeck = min(bottleNeck, augedge.cap)
        augedge = prevArr[augedge.frome]
        if augedge.frome == start:
            break
    augedge = prevArr[end]
    while augedge!=prevArr[start]:
        augedge.cap-=bottleNeck
        augedge.oppos.cap+=bottleNeck
        augedge = prevArr[augedge.frome]

    return bottleNeck
    


def edmond_karp(hashmap,start,end):
    visarr =[]
    flow = 1
    maxflow = 0
    for s in range(len(hashmap)+1):
        visarr.append(False)
    while flow!=0:
        for s in range(len(visarr)):
            visarr[s]=False
        flow = bfs(start,visarr,hashmap,end)
        maxflow+=flow
    print("Max Flow: "+ str(maxflow))
e1 = Edge(1,2,3)
n1 = Edge(2,1,0)
oppositeedges(e1,n1)
e2 = Edge(1,3,5)
n2 = Edge(3,1,0)
oppositeedges(e2,n2)
e3 = Edge(1,4,1)
n3 = Edge(4,1,0)
oppositeedges(e3,n3)
e4 = Edge(2,5,2)
n4 = Edge(5,2,0)
oppositeedges(e4,n4)
e5 = Edge(3,5,3)
n5 = Edge(5,3,0)
oppositeedges(e5,n5)
e6 = Edge(4,5,1)
n6 = Edge(5,4,0)
oppositeedges(e6,n6)
hashmap = {1:[e1,e2,e3],2:[e4,n1],3:[e5,n2],4:[e6,n3],5:[n4,n5,n6]}
edmond_karp(hashmap,1,5)
