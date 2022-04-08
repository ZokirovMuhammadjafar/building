package uz.karkas.building.service.category;

import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryDTO;
import uz.karkas.building.dto.category.CategoryUpdateDTO;
import uz.karkas.building.service.base.GenericCRUDService;

public interface CategoryService extends GenericCRUDService<CategoryDTO, CategoryCreateDTO, CategoryUpdateDTO, Integer> {
}
