package cat.tecnocampus.RESTcontrollers;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.useCases.NotesUseCases;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteRESTController {

    private NotesUseCases notesUseCases;

    public NoteRESTController(NotesUseCases notesUseCases) {
        this.notesUseCases = notesUseCases;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteLab> getNotes() {

        List<NoteLab> noteLabs = notesUseCases.getAllNotes();

        return noteLabs;
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteLab> getUserNotes(@PathVariable String username) {
        return notesUseCases.getUserNotes(username);
    }

    @GetMapping(value = "/{username}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteLab getUserNote(@PathVariable String username, @PathVariable Long id) {
        return notesUseCases.findById(id);
    }

    @PostMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteLab createNote(@RequestBody @Valid NoteLab note, @PathVariable String username) {

        System.out.println("going to create note: " + username);
        NoteLab newNote = this.notesUseCases.createUserNote(username, note);

        return newNote;
    }

    @PutMapping(value = "/{username}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteLab updateNote(@RequestBody @Valid NoteLab note, @PathVariable String username,
                                      @PathVariable Long id) {
        NoteLab newNote = notesUseCases.updateUserNote(username, note, id);

        return newNote;
    }

    @DeleteMapping(value = "/{username}")
    public void deleteNote(@RequestBody @Valid NoteLab note, @PathVariable String username) {
        notesUseCases.deleteUserNote(note.getId(), username);
    }

    @DeleteMapping(value = "/{username}/{id}")
    public void deleteNoteId(@PathVariable String username, @PathVariable Long id) {
        notesUseCases.deleteUserNote(id, username);
    }
}
