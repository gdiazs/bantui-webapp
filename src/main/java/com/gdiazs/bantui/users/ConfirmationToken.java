package com.gdiazs.bantui.users;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the confirmation_tokens database table.
 * 
 */
@Entity
@Table(name="confirmation_tokens")
@NamedQuery(name="ConfirmationToken.findAll", query="SELECT c FROM ConfirmationToken c")
public class ConfirmationToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConfirmationTokenPK id;

	private Integer confirmed;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String token;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	public ConfirmationToken() {
	}

	public ConfirmationTokenPK getId() {
		return this.id;
	}

	public void setId(ConfirmationTokenPK id) {
		this.id = id;
	}

	public Integer getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}



}