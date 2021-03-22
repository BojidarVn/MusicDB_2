package bg.example.demo.service;

import bg.example.demo.model.entity.UserEntity;
import bg.example.demo.model.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean userNameExists(String username);

    UserEntity findByName(String username);

    UserEntity findById(Long id);
}
