package dao.impl;

import dao.ItemDAO;
import modelo.Item;

public abstract class ItemDAOImpl extends JPADaoGenerico<Item, Long> implements ItemDAO {
	public ItemDAOImpl() {
		super(Item.class);
	}
}
