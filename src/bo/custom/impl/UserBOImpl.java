package bo.custom.impl;

import java.util.ArrayList;
import java.util.List;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO dto) {
        return userDAO.save(new User(dto.getName(), dto.getEmail(), dto.getPassword()));
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userDAO.getAll();
        for (User user : users) {
            userDTOS.add(new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword()));
        }
        return userDTOS;
    }

    @Override
    public boolean updateUser(UserDTO dto) {
        return userDAO.update(new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword()));
    }

    @Override
    public boolean deleteUser(long id) {
        return userDAO.delete(id);
    }
}
