package com.treeseed.utils;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PageWrapper.
 *
 * @param <E> the element type
 */
public class PageWrapper<E> {

	/** The results. */
	private List<E> results;
	
	/** The total items. */
	private long totalItems;
	
	/** The total pages. */
	private int totalPages;
	
	/**
	 * Instantiates a new page wrapper.
	 */
	public PageWrapper(){
		results = new ArrayList<E>();
	}
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<E> getResults() {
		return results;
	}
	
	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<E> results) {
		this.results = results;
	}
	
	/**
	 * Gets the total items.
	 *
	 * @return the total items
	 */
	public long getTotalItems() {
		return totalItems;
	}
	
	/**
	 * Sets the total items.
	 *
	 * @param totalItems the new total items
	 */
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	
	/**
	 * Gets the total pages.
	 *
	 * @return the total pages
	 */
	public int getTotalPages() {
		return totalPages;
	}
	
	/**
	 * Sets the total pages.
	 *
	 * @param totalPages the new total pages
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
