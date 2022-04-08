package uz.karkas.building.service.project;

import uz.karkas.building.domain.Project;
import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;
import uz.karkas.building.service.base.GenericCRUDService;

public interface ProjectService extends GenericCRUDService<ProjectDTO, ProjectCreateDTO, ProjectUpdateDTO, Integer> {
}
