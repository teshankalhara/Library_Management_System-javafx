package bo.custom;

import java.util.List;

import bo.SupperBO;
import dto.UserDTO;

public interface UserBO extends SupperBO {
    boolean saveUser(UserDTO dto);

    List<UserDTO> getAllUser();

    boolean updateUser(UserDTO dto);

    boolean deleteUser(long id);
}
