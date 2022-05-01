package com.example.L13.L13.service;

import com.example.L13.L13.models.UserInfo;
import com.example.L13.L13.repository.UserRepository;
import com.example.L13.L13.requests.CreateUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserInfo createUser(CreateUserRequestDTO createUserRequestDTO){
        UserInfo user = createUserRequestDTO.toUser();
        return userRepository.save(user);
    }

    public List<UserInfo> fetchAllUsers(Integer pageNumber){
      Pageable page = PageRequest.of(pageNumber,20);
      return userRepository.findAll(page).getContent();
    }

    public Optional<UserInfo> fetchUserById(Long id){
        return userRepository.findById(id);
    }

}
