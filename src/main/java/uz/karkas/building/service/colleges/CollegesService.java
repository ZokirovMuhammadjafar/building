package uz.karkas.building.service.colleges;

import uz.karkas.building.dto.colleges.CollegesCreateDTO;
import uz.karkas.building.dto.colleges.CollegesDTO;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.service.base.GenericCRUDService;

public interface CollegesService extends GenericCRUDService<CollegesDTO, CollegesCreateDTO, CollegesUpdateDTO,Integer> {
}
