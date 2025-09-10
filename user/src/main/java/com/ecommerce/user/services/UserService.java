package com.ecommerce.user.services;

import com.ecommerce.user.controller.UserController;
import com.ecommerce.user.dtos.AddressDTO;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.dtos.UserRequest;
import com.ecommerce.user.dtos.UserResponse;
import com.ecommerce.user.models.Address;
import com.ecommerce.user.models.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService
{

    private UserRepository userRepository;

    private List<User> usersList=new ArrayList<>();
    private Long nextId=1L;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> fetchAllUsers()
    {
       return userRepository.findAll().stream()
                .map(this::mapToUserResponseDto)
                .collect(Collectors.toList());
    }


    public void AddUsers(UserRequest userRequest)
    {
//        user.setId(nextId++);
//        usersList.add(user);
        User user = new User();
        UpdateUserFromRequestDto(user,userRequest);
      userRepository.save(user);

    }



    public Optional<UserResponse> fetchSingleUser(String id) {
//        return usersList.stream()
//                .filter(user -> user.getId().equals(id)).findFirst();

   return userRepository.findById(id)
                .map(this::mapToUserResponseDto);
    }


//    public boolean updateUser(String id, UserRequest updatedUser) {
//
//        return usersList.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .map(existingUser -> {
//                 UpdateUserFromRequestDto(existingUser, updatedUser);
//                    userRepository.save(existingUser);
//
//                    return true;
//                })
//                .orElse(false);
//    }

    public boolean updateUser(String id, UserRequest updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            UpdateUserFromRequestDto(existingUser, updatedUser);
            userRepository.save(existingUser);
            return true;
        } else {
            return false;
        }
    }



    private void UpdateUserFromRequestDto(User user, UserRequest userRequest)
    {

        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPhonenumber(userRequest.getPhonenumber());
        user.setUserRole(userRequest.getUserRole());
        if (userRequest.getAddress() != null)
        {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }

    }


        public UserResponse mapToUserResponseDto (User user)
        {
            UserResponse response = new UserResponse();
            response.setId(String.valueOf(user.getId()));
            response.setFirstname(user.getFirstname());
            response.setLastname(user.getLastname());
            response.setEmail(user.getEmail());
            response.setPhonenumber(user.getPhonenumber());

            if (user.getAddress() != null) {
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setCity(user.getAddress().getCity());
                addressDTO.setCountry(user.getAddress().getCountry());
                addressDTO.setState(user.getAddress().getState());
                addressDTO.setStreet(user.getAddress().getStreet());
                addressDTO.setZipcode(user.getAddress().getZipcode());
                response.setAddress(addressDTO);
            }

            return response;
        }
    }

