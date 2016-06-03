package konto.data.DBUtil;

import java.util.ArrayList;

import konto.data.model.Category;

public interface ICategory {
	
	public void createCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void deleteCategory(Category category);
	
	public ArrayList<Category> getAllCategories();

}
