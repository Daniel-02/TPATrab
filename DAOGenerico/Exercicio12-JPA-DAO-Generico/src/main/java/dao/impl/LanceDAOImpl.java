package dao.impl;

import modelo.Lance;
import dao.LanceDAO;

public abstract class LanceDAOImpl 
	extends JPADaoGenerico<Lance, Long> implements LanceDAO
{
	public LanceDAOImpl() 
	{	super(Lance.class);		
	}
}
