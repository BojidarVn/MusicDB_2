package bg.example.demo.service;

import bg.example.demo.model.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean userNameExists(String username);
}
