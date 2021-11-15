package se459.extremers.jarmoveout;

import java.util.*;
import org.springframework.data.geo.Point;

public class CleanSweepNode  {
	int id;
	surfaceType surface;
	int dirt;
	boolean isClean;
	boolean obstacle;
	boolean visited;
	boolean isChargingStation;

	//for pathfinding
	List<CleanSweepNode> neighbors;
	CleanSweepNode parent;
	int f;
	int g;
	int h;
	Point pos;
	int cost;
	
	edgeType northEdge, eastEdge, westEdge, southEdge;
	CleanSweepNode northNode, eastNode, southNode, westNode;

	public CleanSweepNode() {
		this.northNode =  null;
		this.eastNode = null;
		this.southNode = null;
		this.westNode = null;
		this.visited = false;
		this.neighbors = new ArrayList<CleanSweepNode>();
	}


	public CleanSweepNode(int id, edgeType northEdge, edgeType southEdge, edgeType eastEdge, edgeType westEdge, 
				boolean clean, surfaceType surface, boolean chargingStation, int dirt_level) {
		this.id= id;
		this.surface = surface;
		this.isClean = clean;
			
		this.northEdge = northEdge;
		this.eastEdge = eastEdge;
		this.southEdge = southEdge;
		this.westEdge = westEdge;

		this.neighbors = new ArrayList<CleanSweepNode>();

		this.cost = this.surface.getUnits();

		this.isChargingStation = chargingStation;
		this.dirt = dirt_level;
	}

	public CleanSweepNode(CleanSweepNode node, Point position) {
		this.id= node.id;
		this.surface = node.surface;
		this.isClean = node.isClean;
			
		this.northEdge = node.northEdge;
		this.eastEdge = node.eastEdge;
		this.southEdge = node.southEdge;
		this.westEdge = node.westEdge;

		this.neighbors = new ArrayList<CleanSweepNode>();

		this.pos = position;

		this.cost = this.surface.getUnits();

		this.isChargingStation = node.isChargingStation;
		this.dirt = node.dirt;
	}

	@Override
    public boolean equals(Object tmp) {
        return (this.id == ((CleanSweepNode)tmp).id);
    }

    public boolean decreaseDirt() {
		if (this.dirt > 0){
			this.dirt--;
			if (this.dirt == 0 ) this.isClean = true; 
		}
		return isClean();
    }

	public boolean isClean(){
		return this.isClean;
	}

	public void AssignNorthInternal(CleanSweepNode node) {
		this.northNode = node;
		if (this.northEdge == edgeType.OPEN) {
			this.neighbors.add(node);
		}
	}

	public void AssignEastInternal(CleanSweepNode node) {
		this.eastNode = node;
		if (this.eastEdge == edgeType.OPEN) {
			this.neighbors.add(node);
		}
	}

	public void AssignSouthInternal(CleanSweepNode node) {
		this.southNode = node;
		if (this.southEdge == edgeType.OPEN) {
			this.neighbors.add(node);
		}
	}

	public void AssignWestInternal(CleanSweepNode node) {
		this.westNode = node;
		if (this.westEdge == edgeType.OPEN) {
			this.neighbors.add(node);
		}
	}
}


	