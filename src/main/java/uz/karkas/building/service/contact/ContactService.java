package uz.karkas.building.service.contact;

import uz.karkas.building.dto.contact.ContactCreateDTO;
import uz.karkas.building.dto.contact.ContactDTO;
import uz.karkas.building.dto.contact.ContactUpdateDTO;
import uz.karkas.building.service.base.GenericCRUDService;

public interface ContactService extends GenericCRUDService<ContactDTO, ContactCreateDTO, ContactUpdateDTO, Integer> {
}
