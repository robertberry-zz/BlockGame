package com.sunderance.block_game;

import java.util.ArrayList;

import Jama.Matrix;

public class Block {

	private double x;
	private double y;
	
	private ArrayList<BlockComponents> projections;
	
	private int currentProjection;

	public Block(double x, double y, ArrayList<BlockComponents> projections,
			int currentProjection) {
		super();
		this.x = x;
		this.y = y;
		this.projections = projections;
		this.currentProjection = currentProjection;
	}
	
	private BlockComponents getProjection() {
		return projections.get(currentProjection);
	}
	
	private ArrayList<Matrix> getComponents() {
		return getProjection().getComponents();
	}
	
	public ArrayList<Matrix> getCoordinates() {
		ArrayList<Matrix> coordinates = new ArrayList<Matrix>();
		
		double[][] center_coordinates = {{x, y}};
		Matrix center = new Matrix(center_coordinates);
		
		for (Matrix component : getComponents()) {
			coordinates.add(component.times(center));
		}
		
		return coordinates;
	}
}
