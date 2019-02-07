package cat.tecnocampus.useCases;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.persistence.NoteLabDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("NotesUseCases")
public class NotesUseCases {

    private final NoteLabDAO noteLabDAO;

    public NotesUseCases(NoteLabDAO noteLabDAO) {
        this.noteLabDAO = noteLabDAO;
    }

    public NoteLab createUserNote(String username, NoteLab noteLab) {
        noteLab.setDateCreation(LocalDateTime.now());
        noteLab.setDateEdit(LocalDateTime.now());

        return addUserNote(username, noteLab);
    }

    private NoteLab addUserNote(String username, NoteLab noteLab) {
        return noteLabDAO.insert(noteLab, username);
    }

    public NoteLab addUserNote(String owner, String title, String contents) {
        LocalDateTime now = LocalDateTime.now();
        NoteLab note = new NoteLab.NoteLabBuilder(title, contents, owner).dateEdit(now).dateCreation(now).build();
        noteLabDAO.insert(note, owner);
        return note;
    }

    public NoteLab updateUserNote(String owner, NoteLab note, Long id) {
        NoteLab oldNote = noteLabDAO.findById(id);
        note.setDateCreation(oldNote.getDateCreation());
        note.setDateEdit(LocalDateTime.now());
        noteLabDAO.updateNote(id, note);
        return note;
    }

    public int deleteUserNote(Long id, String username) {
        return noteLabDAO.deleteNote(id, username);
    }

    public List<NoteLab> getUserNotes(String userName) {
        return noteLabDAO.findByUsername(userName);
    }

    public List<NoteLab> getAllNotes() {
        return noteLabDAO.findAll();
    }

    public NoteLab findById(Long id) {
        return this.noteLabDAO.findById(id);
    }

}
