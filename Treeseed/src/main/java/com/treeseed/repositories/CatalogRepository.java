package com.treeseed.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.treeseed.ejb.Catalog;

public interface CatalogRepository extends CrudRepository<Catalog,Integer> {
	
		public static final int PAGE_SIZE = 5;
			
		Page<Catalog> findAll(Pageable pageable);
		
		Page<Catalog> findByNameContaining(String name,
				Pageable pageable);
}