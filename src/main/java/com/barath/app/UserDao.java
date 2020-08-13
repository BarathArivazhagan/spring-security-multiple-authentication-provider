package com.barath.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserDTO, Long>
{

	UserDTO findByUserName(final String userName);

}