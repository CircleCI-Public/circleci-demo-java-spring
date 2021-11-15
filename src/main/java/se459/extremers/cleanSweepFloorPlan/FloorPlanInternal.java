package se459.extremers.cleanSweepFloorPlan;

import java.util.*;
import floorplan.*;
import floorplan.Point;



public class FloorPlanInternal {

    HashMap<Point, CleanSweepNode> map;
    LinkedList<Tuple> unvisited;
    LinkedList<CleanSweepNode> stations;
    CleanSweepNode lastDiscovered;
    CleanSweepNode Reference;

    public FloorPlanInternal() {
        this.Reference = null;
        this.map = new HashMap<Point, CleanSweepNode>();
        this.unvisited = new LinkedList<Tuple>();
        this.stations = new LinkedList<CleanSweepNode>();
    }

    public CleanSweepNode Add(CleanSweepNode node, Point pos) {

        // Creates new copy of passed external node
        node = new CleanSweepNode(node, pos);

        // check if charging station
        if (node.isIsChargingStation()) {
            this.stations.addFirst(node);
        }

        // for first node, make it the reference node and add to map
        if (this.Reference == null) {
            this.Reference = node;

            this.map.put(pos, node);
            this.lastDiscovered = node;
        }
        else {
            // add pos and node to internal hashmap
            this.map.put(pos, node);
            this.lastDiscovered = node;
        }
        // Check around node and create connections & check for unvisted nodes

        // Check north
        // Gets north position of node
        Point checkPos = GetNorthPos(pos);
        // Attemps to get north node if it exists in hash map. If so, create connections.
        CleanSweepNode tmpNode = map.get(checkPos);
        Tuple tmpTuple = new Tuple(node, NavigationOptionsEnum.NORTH);
        if (tmpNode != null) {
            tmpNode.AssignSouthInternal(node);
            node.AssignNorthInternal(tmpNode);
        }
        // checks if north is open and if north position is in unvisited queue
        if (node.getNorthEdge() == edgeType.OPEN && tmpNode == null && !this.map.containsKey(checkPos) && !this.unvisited.contains(tmpTuple)) {
            this.unvisited.addFirst(tmpTuple);
        }

        // Check east
        checkPos = GetEastPos(pos);
        tmpNode = map.get(checkPos);
        tmpTuple = new Tuple(node, NavigationOptionsEnum.EAST);
        if (tmpNode != null) {
            tmpNode.AssignWestInternal(node);
            node.AssignEastInternal(tmpNode);
        }

        if (node.getEastEdge() == edgeType.OPEN && tmpNode == null && !this.map.containsKey(checkPos) && !this.unvisited.contains(tmpTuple)) {
            this.unvisited.addFirst(tmpTuple);
        }

        // Check south
        checkPos = GetSouthPos(pos);
        tmpNode = map.get(checkPos);
        tmpTuple = new Tuple(node, NavigationOptionsEnum.SOUTH);
        if (tmpNode != null) {
            tmpNode.AssignNorthInternal(node);
            node.AssignSouthInternal(tmpNode);
        }
        if (node.getSouthEdge() == edgeType.OPEN && tmpNode == null && !this.map.containsKey(checkPos) && !this.unvisited.contains(tmpTuple)) {
            this.unvisited.addFirst(tmpTuple);
        }

        // Check west
        checkPos = GetWestPos(pos);
        tmpNode = map.get(checkPos);
        tmpTuple = new Tuple(node, NavigationOptionsEnum.WEST);
        if (tmpNode != null) {
            tmpNode.AssignEastInternal(node);
            node.AssignWestInternal(tmpNode);
        }
        if (node.getWestEdge() == edgeType.OPEN && tmpNode == null && !this.map.containsKey(checkPos) && !this.unvisited.contains(tmpTuple)) {
            this.unvisited.addFirst(tmpTuple);
        }

        // Finally, check if current node is in univisted list, if so remove it
        NavigationOptionsEnum dirToCheck = NavigationOptionsEnum.EAST;
        for (int i = 0; i < 4; i++) {
            Tuple tupleToRemove;
            switch(dirToCheck) {
                case EAST:
                    tupleToRemove = new Tuple(GetEastNode(pos), NavigationOptionsEnum.WEST);
                    if(tupleToRemove.node != null && this.unvisited.contains(tupleToRemove)) {
                        this.unvisited.remove(tupleToRemove);
                    } 
                    break;
                case SOUTH:
                    tupleToRemove = new Tuple(GetSouthNode(pos), NavigationOptionsEnum.NORTH);
                    if(tupleToRemove.node != null && this.unvisited.contains(tupleToRemove)) {
                        this.unvisited.remove(tupleToRemove);
                    } 
                    break;
                case WEST:
                    tupleToRemove = new Tuple(GetWestNode(pos), NavigationOptionsEnum.EAST);
                    if(tupleToRemove.node != null && this.unvisited.contains(tupleToRemove)) {
                        this.unvisited.remove(tupleToRemove);
                    } 
                    break;
                case NORTH:
                    tupleToRemove = new Tuple(GetNorthNode(pos), NavigationOptionsEnum.SOUTH);
                    if(tupleToRemove.node != null && this.unvisited.contains(tupleToRemove)) {
                        this.unvisited.remove(tupleToRemove);
                    } 
                    break;
                default:
                    break;
            }
            dirToCheck = NavigationOptionsEnum.RotateDirection(dirToCheck);
        }

        return node;
    }

    public CleanSweepNode GetNorthNode(Point pos) {
        try {
            return this.map.get(new Point(pos.getX(), pos.getY() - 1));
        } catch (NullPointerException e) {
            return null;
        }
    }

    public CleanSweepNode GetEastNode(Point pos) {

        try {
            return this.map.get(new Point(pos.getX() + 1, pos.getY()));
        } catch (NullPointerException e) {
            return null;
        }
    }

    public CleanSweepNode GetSouthNode(Point pos) {
        try {
            return this.map.get(new Point(pos.getX(), pos.getY() + 1));
        } catch (NullPointerException e) {
            return null;
        }
    }

    public CleanSweepNode GetWestNode(Point pos) {
        try {
            return this.map.get(new Point(pos.getX() - 1, pos.getY()));
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Point GetNorthPos(Point pos) {
        return new Point(pos.getX(), pos.getY() - 1);
    }

    public Point GetEastPos(Point pos) {
        return new Point(pos.getX() + 1, pos.getY());
    }

    public Point GetSouthPos(Point pos) {
        return new Point(pos.getX(), pos.getY() + 1);
    }

    public Point GetWestPos(Point pos) {
        return new Point(pos.getX() - 1, pos.getY());
    }

    public CleanSweepNode FindClosestStation(CleanSweepNode start) {
        TreeMap<Integer, CleanSweepNode> paths = new TreeMap<Integer, CleanSweepNode>();

        for (CleanSweepNode station : this.stations) {
            int size = aStar(start, station).size();
            paths.put(size, station);
        }

        return paths.get(paths.firstKey());
    }

    // code base from
    // https://stackoverflow.com/questions/5601889/unable-to-implement-a-star-in-java
    public List<CleanSweepNode> aStar(CleanSweepNode start, CleanSweepNode goal) {
        Set<CleanSweepNode> open = new HashSet<CleanSweepNode>();
        Set<CleanSweepNode> closed = new HashSet<CleanSweepNode>();

        start.setG(0);
        start.setH(estimateDistance(start, goal));
        start.setF(start.getH());

        open.add(start);

        while (true) {
            CleanSweepNode current = null;

            if (open.size() == 0) {
                throw new RuntimeException("no route");
            }

            for (CleanSweepNode node : open) {
                if (current == null || node.getF() < current.getF()) {
                    current = node;
                }
            }

            if (current == goal) {
                break;
            }

            open.remove(current);
            closed.add(current);

            for (CleanSweepNode neighbor : current.getNeighbors()) {
                if (neighbor == null) {
                    continue;
                }

                 int nextG = current.getG() + neighbor.getCost();

                if (nextG < neighbor.getG()) {
                    open.remove(neighbor);
                    closed.remove(neighbor);
                }

                if (!open.contains(neighbor) && !closed.contains(neighbor)) {
                    neighbor.setG(nextG);
                    neighbor.setH(estimateDistance(neighbor, goal));
                    neighbor.setF(neighbor.getG() + neighbor.getH());
                    neighbor.setParent(current);
                    open.add(neighbor);
                }
            }
        }

        List<CleanSweepNode> nodes = new ArrayList<CleanSweepNode>();
        CleanSweepNode current = goal;
        while (current.getParent() != null) {
            nodes.add(current);
            current = current.getParent();
        }
        nodes.add(start);

        ResetNodeValues(nodes, start, goal);

        return nodes;
    }

    private void ResetNodeValues(List<CleanSweepNode> nodes, CleanSweepNode start, CleanSweepNode goal) {

        start.setParent(null); goal.setParent(null);
        start.setF(0); goal.setF(0);
        start.setG(0); goal.setG(0);
        start.setH(0); goal.setH(0);
        
        for (CleanSweepNode node: nodes) {
            node.setParent(null); 
            node.setF(0); 
            node.setG(0);
            node.setH(0);
        }
    }

    public int estimateDistance(CleanSweepNode node1, CleanSweepNode node2) {
        double tmp = Math.abs(node1.getPos().getX() - node2.getPos().getX()) + Math.abs(node1.getPos().getY() - node2.getPos().getY());

        return (int) tmp;
    }

}