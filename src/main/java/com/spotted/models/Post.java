package com.spotted.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotted.enums.PostTypes;

@Entity
@Table(name = "post")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @NotNull(message = "parente can not be null")
	@JoinColumn(name = "parent_id", referencedColumnName = "email")
    private User parent;
	
	@Column(name = "type")
	@NotNull(message = "Type can not be null")
	@Enumerated(EnumType.STRING)
	private PostTypes type;
	
	@Column(name = "title")
	@NotNull(message = "title can not be null")
	@NotEmpty(message = "title can not be empty")
	private String title;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "image", columnDefinition = "TEXT")
	private String image;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Set<Comment> comments;
	
	@Column(name = "start")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime start;
	
	@Column(name = "end")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime end;
	
	
	@Column(name = "datetime")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime datetime;
	
	@Column(name = "visible")
	private boolean visible = true;
	
	public Post() {}	

	public Post(User parent, String text, String image, PostTypes type, String title) {
		this.parent = parent;
		this.text = text;
		this.image = image;
		this.type = type;
		this.title = title;
		this.comments = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PostTypes getType() {
		return type;
	}

	public void setType(PostTypes type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(parent, post.parent) &&
                type == post.type &&
                Objects.equals(text, post.text) &&
                image.equals(post.image) &&
                Objects.equals(comments, post.comments) &&
                Objects.equals(datetime, post.datetime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, parent, type, text, comments, datetime, image);
        result = 31 * result;
        return result;
    }
}
