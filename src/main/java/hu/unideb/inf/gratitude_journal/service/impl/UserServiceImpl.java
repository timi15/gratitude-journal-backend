package hu.unideb.inf.gratitude_journal.service.impl;

import hu.unideb.inf.gratitude_journal.data.entity.UserEntity;
import hu.unideb.inf.gratitude_journal.data.repository.UserRepository;
import hu.unideb.inf.gratitude_journal.exception.NotFoundException;
import hu.unideb.inf.gratitude_journal.service.UserService;
import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateUserRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.UserResponseDTO;
import hu.unideb.inf.gratitude_journal.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username);
            }
        };
    }

    @Override
    public List<UserResponseDTO> findAll() {
        List<UserEntity> ownerEntities = userRepository.findAll();
        return userMapper.map(ownerEntities);
    }

    @Override
    public UserResponseDTO findById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with id: " + id);
        }
        return userMapper.toResponseDTO(userRepository.findById(id).get());
    }

    @Override
    public UserResponseDTO update(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with id: " + id);
        }

        UserEntity existing = userRepository.findById(id).get();
        userMapper.updateUserEntityFromDTO(updateUserRequestDTO, existing);
        UserEntity updated = userRepository.save(existing);

        return userMapper.toResponseDTO(updated);
    }

    @Override
    public UserResponseDTO deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with id: " + id);
        }
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.deleteById(id);

        return userMapper.toResponseDTO(userEntity);
    }


}
