package com.example.infirmary.Objects;

import static com.example.infirmary.Main.inventory;

public class Medication {
	private String name;
	private String expiryDate;
	private int stock;

	private Medication(String name, String expiryDate, int stock) {
		this.name = name;
		this.expiryDate = expiryDate;
		this.stock = Math.max(stock, 0);
	}

	public static void addMedication(String name, String expiryDate, int stock) {

		if (inventory.isEmpty()) {
			inventory.add(new Medication(name, expiryDate, stock));
			return;
		}

		//using BS to find the correct position to insert the new medication
		//while maintaining a sorted array based on medication name
		int lo = 0, hi = inventory.size() - 1, pos = inventory.size();
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			//if it already exists, update stock
			if (inventory.get(mid).getName().equals(name)) {
				inventory.get(mid).addStock(stock);
				return;
			}
			//if mid is lexographically less than name
			else if (inventory.get(mid).getName().compareTo(name) < 0)
				lo = mid + 1;
			//if mid is lexographically greater than name
			else {
				pos = mid;
				hi = mid - 1;
			}
		}
		inventory.add(pos, new Medication(name, expiryDate, stock));
	}

	public static Medication find(String name) {
		int pos = Medication.findUtil(name, 0, inventory.size() - 1);
		if (pos == -1)
			return null;
		else
			return inventory.get(pos);
	}

	public static int findUtil(String name, int lo, int hi) {
		if (lo > hi)
			return -1;

		int mid = (lo + hi) / 2;
		String mid_name = inventory.get(mid).getName();

		if (mid_name.compareTo(name) == 0)
			return mid;
		else if (mid_name.compareTo(name) < 0)
			return findUtil(name, mid + 1, hi);
		else
			return findUtil(name, lo, mid - 1);
	}

	public String getName() {
		return name;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public int getStock() {
		return stock;
	}

	public void addStock(int stock) {
		this.stock = Math.max(this.stock + stock, 0);
	}

	public String toString() {
		return name + " " + expiryDate + " " + stock;
	}
}
