package dao.impl;

import modelo.Produto;
import dao.ProdutoDAO;

public abstract class ProdutoDAOImpl
       extends JPADaoGenerico<Produto, Long> implements ProdutoDAO 
{   
    public ProdutoDAOImpl()
    { 	super(Produto.class); 
    }
}
