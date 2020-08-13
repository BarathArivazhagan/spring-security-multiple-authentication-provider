package com.barath.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class OAuth2UserDetails implements UserDetails
{
	private static final long	serialVersionUID	= 7116369654223628650L;
	private UserDTO				user;

	public OAuth2UserDetails(UserDTO user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : user.getRoles())
		{
			authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));
		}
		return authorities;
	}

	@Override
	public String getPassword()
	{
		return user.getPassword();
	}

	@Override
	public String getUsername()
	{
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return user.getActive();
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return (user.getInvalidLoginCount() == null ? 0
		        : user.getInvalidLoginCount()) <= 5;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public UserDTO getUser()
	{
		return user;
	}

	public void setUser(UserDTO user)
	{
		this.user = user;
	}

}
