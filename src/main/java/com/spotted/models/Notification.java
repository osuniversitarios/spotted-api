package com.spotted.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notification implements Comparable<Notification> {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "publication_type")
	@NotNull(message = "Publication type can not be null")
	@NotEmpty(message = "Publication type can not be empty")
	private String publicationType;
	
	@Column(name = "publication_id")
	@NotNull(message = "Publication id can not be null")
	private Long publicationId;
	
	@ManyToOne
	@NotNull(message = "Commenter can not be null")
	@JoinColumn(name = "commenter_id", referencedColumnName = "email")
	private User commenter;
	
	@Column(name = "marked_email")
	@NotNull(message = "Marked email can not be null")
	@NotEmpty(message = "Marked email can not be empty")
	private String markedEmail;
	
	@Column(name = "visualized")
	private boolean visualized = false;
	
	@Column(name = "datetime")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime datetime;
	
	public Notification() {
	}
	
	public Notification(String publicationType, Long publicationId, User commenter, String markedEmail) {
		this.publicationType = publicationType;
		this.publicationId = publicationId;
		this.commenter = commenter;
		this.markedEmail = markedEmail;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getCommenter() {
		return commenter;
	}
	
	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}
	
	public String getPublicationType() {
		return publicationType;
	}
	
	
	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}
	
	public Long getPublicationId() {
		return publicationId;
	}
	
	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}
	
	public boolean isVisualized() {
		return visualized;
	}
	
	public void setVisualized(boolean visualized) {
		this.visualized = visualized;
	}
	
	public String getMarkedEmail() {
		return markedEmail;
	}
	
	public void setMarkedEmail(String markedEmail) {
		this.markedEmail = markedEmail;
	}
	
	public LocalDateTime getDatetime() {
		return datetime;
	}
	
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Notification other = (Notification) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		}
		else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(Notification o) {
		return o.datetime.compareTo(this.getDatetime());
	}
}
