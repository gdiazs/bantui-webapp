package com.gdiazs.bantui.users;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author gdiazs
 */
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_authority")
  private String id;

  @Column(name = "authority_name")
  private String authority;

  @Version
  @Column(name = "authority_version")
  private Integer version;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date udatedAt;

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getAuthority() {
    return this.authority;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUdatedAt() {
    return udatedAt;
  }

  public void setUdatedAt(Date udatedAt) {
    this.udatedAt = udatedAt;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "Authority [id=" + id + ", authority=" + authority + ", version=" + version + "]";
  }

  public static List<Authority> createRoles(String... roles) {

    List<Authority> auths = new ArrayList<Authority>();

    for (int i = 0; i < roles.length; i++) {
      Authority auth = new Authority();
      auth.setAuthority(roles[i]);
      auth.setVersion(1);
    }

    return auths;
  }

}
