package com.petshop.PetShop.service;

import com.petshop.PetShop.entity.Pet;
import com.petshop.PetShop.entity.User;
import com.petshop.PetShop.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional(readOnly = true)
    public List<Pet> listByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    @Transactional
    public Pet create(User owner, String name, String species) {
        if (owner == null) {
            throw new IllegalArgumentException("Tutor deve existir.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do pet não pode ser nulo ou vazio.");
        }
        if (species == null || species.isBlank()) {
            throw new IllegalArgumentException("Espécie do pet não pode ser nula ou vazia.");
        }
        return petRepository.save(new Pet(owner, name.trim(), species.trim()));
    }
}
