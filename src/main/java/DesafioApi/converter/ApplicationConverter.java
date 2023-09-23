package DesafioApi.converter;

import com.DesafioApi.springswaggercodegen3.model.UserRequest;
import com.DesafioApi.springswaggercodegen3.model.UserResponse;

import java.util.UUID;

public class ApplicationConverter {

    public UserResponse toUserResponse(UserRequest request) {
       try{
           return new UserResponse()
                   .id(UUID.randomUUID().toString())
                   .firstName(request.getFirstName())
                   .lastName(request.getLastName())
                   .email(request.getEmail())
                   .birthday(request.getBirthday())
                   .login(request.getLogin())
                   .password(request.getPassword())
                   .phone(request.getPhone())
                   .cars(request.getCars());
       }catch (Exception e){
           e.getMessage();
       }
        //TODO: CRIAR VERIFICACAO DA MSG PELA EXCECAO CASO ALGUM N TENHA VALOR
        return null;
    }
}
