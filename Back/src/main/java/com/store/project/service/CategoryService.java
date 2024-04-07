package com.store.project.service;

import com.store.project.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category saveCategory(Category category);

    Optional<Category> getCategoryById(Integer id);

    List<Category> getAllCategories();

    Category updateCategory(Category category);

    void deleteCategoryById(Integer id);
}
