package com.cogent.blogrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "comments"
)
public class Comment {

  @Id
  @GeneratedValue(
          strategy = GenerationType.IDENTITY
  )
  private Long id;

  @NotEmpty(message = "name should not be empty")
//  @Column(name = "name", nullable = false)
  private String name;

  @NotEmpty(message = "email should not be empty")
  @Email
  private String email;

  @NotEmpty(message = "comment body should not be empty")
  @Size(min = 10, message = "comment body must be minimum 10 characters")
//  @Column(name = "body", nullable = false)
  private String body;

  @ManyToOne(
//          fetch = FetchType.LAZY
  )
  @JoinColumn(
          name = "post_id",
          nullable = false
  )
//  @JsonIgnoreProperties({"hibernateLazyInitializer"})
  private Post post;

}
