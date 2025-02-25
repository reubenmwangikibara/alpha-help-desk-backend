package com.alpha.alpha_help_desk_backend.Repository;

import com.alpha.alpha_help_desk_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {


}
