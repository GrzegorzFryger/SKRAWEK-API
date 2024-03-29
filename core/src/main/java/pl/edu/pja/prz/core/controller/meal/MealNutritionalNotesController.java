package pl.edu.pja.prz.core.controller.meal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.meal.facade.MealNutritionalNotesFacade;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;

import java.util.List;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_USER;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_NUTRITIONAL_NOTES;

@RestController
@RequestMapping(API_NUTRITIONAL_NOTES)
public class MealNutritionalNotesController {

    private final MealNutritionalNotesFacade mealNutritionalNotesFacade;

    public MealNutritionalNotesController(MealNutritionalNotesFacade mealNutritionalNotesFacade) {
        this.mealNutritionalNotesFacade = mealNutritionalNotesFacade;
    }

    @GetMapping
    @PreAuthorize(HAS_ROLE_USER)
    public List<NutritionalNotes> getAllNutritionalNotes() {
        return mealNutritionalNotesFacade.getAllNutritionalNotes();
    }

    @PostMapping
    @PreAuthorize(HAS_ROLE_USER)
    List<NutritionalNotes> addNutritionalNotes(@RequestBody NutritionalNotesDTO nutritionalNotesDTO) {
        return mealNutritionalNotesFacade.addNutritionalNotes(nutritionalNotesDTO);
    }

    @GetMapping("{id}")
    @PreAuthorize(HAS_ROLE_USER)
    public NutritionalNotes getNutritionalNotesById(@PathVariable("id") Long id) {
        return mealNutritionalNotesFacade.getNutritionalNotesById(id);
    }


    @GetMapping("/delete")
    @PreAuthorize(HAS_ROLE_USER)
    public List<NutritionalNotes> deleteNutritionalNotesById(@RequestParam("nnId") Long nnId, @RequestParam("mealId") Long mealId ) {
       return mealNutritionalNotesFacade.deleteNutritionalNotesById(nnId, mealId);
    }
}
