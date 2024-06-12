package com.sidorov.pet.book_store.services;

import com.sidorov.pet.book_store.entities.Role;
import com.sidorov.pet.book_store.entities.User;
import com.sidorov.pet.book_store.entities.dto.RoleDTO;
import com.sidorov.pet.book_store.entities.dto.UserDTO;
import com.sidorov.pet.book_store.entities.mappers.RoleMapper;
import com.sidorov.pet.book_store.entities.mappers.UserMapper;
import com.sidorov.pet.book_store.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    UserMapper userMapper;

    public Optional<UserDTO> getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(userMapper::toDTO);
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::toDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                mapRolesToAuthorities(userDTO.getRoles())
        );
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleDTO> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
