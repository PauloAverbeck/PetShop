package com.petshop.PetShop.view;

import com.petshop.PetShop.entity.Pet;
import com.petshop.PetShop.entity.User;
import com.petshop.PetShop.service.PetService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.transaction.annotation.Transactional;

@Route("")
public class PetView extends VerticalLayout {

    private final PetService petService;

    private final Grid<Pet> grid = new Grid<>(Pet.class);
    private final TextField name = new TextField("Nome");
    private final TextField species = new TextField("Espécie");

    public PetView(PetService petService) {
        this.petService = petService;

        grid.addColumn(Pet::getId)
                .setHeader("ID")
                .setAutoWidth(true);

        grid.addColumn(Pet::getName)
                .setHeader("Nome");

        grid.addColumn(Pet::getSpecies)
                .setHeader("Espécie");

        Button add = new Button("Cadastrar Pet", e -> createPet());

        add(name, species, add, grid);
        setSizeFull();
        refreshGrid();
    }

    private void refreshGrid() {
        //TODO: Posteriormente mudar para o tutor logado.
        Long ownerId = 1L;
        grid.setItems(petService.listByOwnerId(ownerId));
    }

    @Transactional
    private void createPet() {
        //TODO: Posteriormente mudar.
        User owner = new User("Admin", "admin@local", "pass");

        try {
            petService.create(owner, name.getValue(), species.getValue());
            Notification.show("Pet cadastrado com sucesso!");
            name.clear();
            species.clear();
            refreshGrid();
        } catch (Exception ex) {
            Notification.show(ex.getMessage());
        }
    }
}
