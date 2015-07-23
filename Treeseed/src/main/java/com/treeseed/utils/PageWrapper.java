package com.treeseed.utils;

import java.util.ArrayList;
import java.util.List;

public class PageWrapper<E> {

	private List<E> results;
	private long totalItems;
	private int totalPages;
	
	public PageWrapper(){
		results = new ArrayList<E>();
	}
	
	public List<E> getResults() {
		return results;
	}
	public void setResults(List<E> results) {
		this.results = results;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
