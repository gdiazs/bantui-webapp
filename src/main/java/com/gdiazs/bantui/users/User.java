package com.gdiazs.bantui.users;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  private static final long serialVersionUID = -8738458013930775017L;

  public static final Integer YES = 1;
  public static final Integer NO = 0;

  @Id
  @Column(name = "id_user")
  private String id;

  @Column(name = "user_name")
  private String username;

  @Column(name = "user_email")
  private String email;

  @Column(name = "user_password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_authorities",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"),
      inverseJoinColumns = @JoinColumn(name = "authority_id",
          referencedColumnName = "id_authority"))
  private Collection<Authority> authorities;

  @Column(name = "user_account_non_expired")
  private Integer accountNonExpired;

  @Column(name = "user_account_non_locked")
  private Integer accountNonLocked;

  @Column(name = "user_credentials_non_expired")
  private Integer credentialsNonExpired;

  @Column(name = "user_enabled")
  private Integer enabled;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at")
  private Date udatedAt;

  @Version
  @Column(name = "user_version")
  private Integer version;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired == YES;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked == YES;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired == YES;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled == YES;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getUdatedAt() {
    return udatedAt;
  }

  public void setUdatedAt(Date udatedAt) {
    this.udatedAt = udatedAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public void setAuthorities(Collection<Authority> authorities) {
    this.authorities = authorities;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAccountNonExpired(Integer accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public void setAccountNonLocked(Integer accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public void setCredentialsNonExpired(Integer credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getAccountNonExpired() {
    return accountNonExpired;
  }

  public Integer getAccountNonLocked() {
    return accountNonLocked;
  }

  public Integer getCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public Integer getEnabled() {
    return enabled;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public boolean isUserInRole(String role) {
    return this.authorities.stream().filter(auth -> auth.getAuthority().equals(role)).findAny()
        .isPresent();
  }

  public static class UserBuilder {

    private User newUser;

    /**
     * It creates an inactive user
     */
    public UserBuilder() {
      this.newUser = new User();
      newUser.setId(UUID.randomUUID() + "");
      newUser.setAccountNonExpired(YES);
      newUser.setAccountNonLocked(YES);
      newUser.setCredentialsNonExpired(YES);
      newUser.setEnabled(NO);
    }

    public UserBuilder addUsername(String username) {
      this.newUser.setUsername(username);
      return this;
    }

    public UserBuilder addPassword(String password) {
      this.newUser.setPassword(password);
      return this;
    }

    public UserBuilder addRoles(String... roles) {
      final List<Authority> createdRoles = Authority.createRoles(roles);
      this.newUser.setAuthorities(createdRoles);
      return this;
    }

    public UserBuilder addEmail(String email) {
      this.newUser.setEmail(email);
      return this;
    }

    public User build() {
      return newUser;
    }

  }

}
