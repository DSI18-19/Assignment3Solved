package cat.tecnocampus.useCases;

import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("userUseCases")
public class UserUseCases {

    private final UserLabDAO userLabDAO;

    public UserUseCases(UserLabDAO UserLabDAO) {
        this.userLabDAO = UserLabDAO;
    }

    public UserLab createUser(String username, String name, String secondName, String email) {
        UserLab userLab = new UserLab.UserLabBuilder(username, email).name(name).secondName(secondName).build();
        registerUser(userLab);
        return userLab;
    }
    public int registerUser(UserLab userLab) {
        return userLabDAO.insert(userLab);
    }

    public int deleteUser(String username) {
        return userLabDAO.delete(username);
    }



    //Note that users don't have their notes with them
    public List<UserLab> getUsers() {
        return userLabDAO.findAll();
    }

    public UserLab getUser(String userName) {
        return userLabDAO.findByUsername(userName);
    }

    public boolean existUser(String username) {
        return userLabDAO.existsUser(username);
    }

}
