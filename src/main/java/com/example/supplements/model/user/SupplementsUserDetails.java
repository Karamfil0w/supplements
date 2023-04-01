package com.example.supplements.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
public class SupplementsUserDetails implements UserDetails {

  private final Long id;
  private final String password;
  private final String username;
  private final Collection<GrantedAuthority> authorities;

  public SupplementsUserDetails(
          Long id,
          String password,
          String username,
          String email , Collection<GrantedAuthority> authorities) {
    this.id = id;
    this.password = password;
    this.username = username;
    this.authorities = authorities;
  }


  public Long getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
