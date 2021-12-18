package services;

import accounts.UserHuuzer;
import dao.UsersDAO;

public class UserService {

    private UsersDAO usersDao = new UsersDAO();

    public UserService() {
    }

    public UserHuuzer findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(UserHuuzer user) {
        usersDao.save(user);
    }

    public void deleteUser(UserHuuzer user) {
        usersDao.delete(user);
    }

    public void updateUser(UserHuuzer user) {
        usersDao.update(user);
    }
    public UserHuuzer findByLogin(String login) {return usersDao.findByLogin(login);}
}