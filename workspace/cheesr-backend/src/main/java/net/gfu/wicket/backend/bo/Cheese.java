package net.gfu.wicket.backend.bo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Cheese extends Lockable implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private double price;
	private int id;

	private HashMap<Integer,CheeseAttribute> additionalAttributes = new HashMap<>();

	public Cheese(){
	}
	
	public Cheese(int id, String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.id = id;
		this.setLockingVersion(1);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cheese cheese = (Cheese) o;
		return id == cheese.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public HashMap<Integer, CheeseAttribute> getAdditionalAttributes() {
		return additionalAttributes;
	}

	@Override
	public String toString() {
		return "Cheese{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", id=" + id +
				", additionalAttributes=" + additionalAttributes +
				'}';
	}
}