package edu.mum.service;

import java.util.List;

import edu.mum.domain.Item;



public interface ItemService {
	
	public void save(Item item);
	public void update(Item item);
	public List<Item> findAll();
	public Item findByProductName(String name);

	public Item findOne(Long id);

}
