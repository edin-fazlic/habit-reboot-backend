package com.habit.reboot.backend.repositories;

import com.habit.reboot.backend.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByUsername(String username);
}
