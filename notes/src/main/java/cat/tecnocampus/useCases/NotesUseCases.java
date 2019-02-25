package cat.tecnocampus.useCases;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.persistence.NoteLabDAO;
import cat.tecnocampus.userClient.UserClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("NotesUseCases")
public class NotesUseCases {

    private final NoteLabDAO noteLabDAO;
    private final UserClient userClient;

    public NotesUseCases(NoteLabDAO noteLabDAO, UserClient userClient) {
        this.noteLabDAO = noteLabDAO;
        this.userClient = userClient;
    }

    public NoteLab createUserNote(String username, NoteLab noteLab) {
        noteLab.setDateCreation(LocalDateTime.now());
        noteLab.setDateEdit(LocalDateTime.now());

        int existsUser = userClient.userExists(username);

        System.out.println("in NotesUseCases. Exists user: " + existsUser);

        if (existsUser == UserClient.USER_EXISTS || existsUser == UserClient.CLIENT_DOWN)
            return noteLabDAO.insert(noteLab, username, existsUser);
        else return null;
    }

    public List<NoteLab> getAllnotCheckedOwner(){return noteLabDAO.findAllnotCheckedOwner();}

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

    public void setOwnerExists(long id){ noteLabDAO.setOwnerExists(id);}


}
