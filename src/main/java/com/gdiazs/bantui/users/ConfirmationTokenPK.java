package com.gdiazs.bantui.users;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the confirmation_tokens database table.
 * 
 */
@Embeddable
public class ConfirmationTokenPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_confimation_token")
	private String idConfimationToken;

	@Column(name="id_user")
	private String idUser;

	public ConfirmationTokenPK() {
	}
	public String getIdConfimationToken() {
		return this.idConfimationToken;
	}
	public void setIdConfimationToken(String idConfimationToken) {
		this.idConfimationToken = idConfimationToken;
	}
	public String getIdUser() {
		return this.idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConfirmationTokenPK)) {
			return false;
		}
		ConfirmationTokenPK castOther = (ConfirmationTokenPK)other;
		return 
			this.idConfimationToken.equals(castOther.idConfimationToken)
			&& this.idUser.equals(castOther.idUser);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idConfimationToken.hashCode();
		hash = hash * prime + this.idUser.hashCode();
		
		return hash;
	}
}