package com.example.radio_active_mushroom.models.entity;

import com.example.radio_active_mushroom.models.primary_keys.MembershipRequestPrimeryKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "membershipRequestEntity")
@IdClass(MembershipRequestPrimeryKey.class)
public class MembershipRequestEntity {

  @Column(name = "message")
  private String message;

  @Column(name = "sendedAt", nullable = false)
  private LocalDateTime sendedAt = LocalDateTime.now();

  @Id
  @ManyToOne
  @JoinColumn(name = "senderUsername")
  private UserEntity sender;

  @Id
  @ManyToOne
  @JoinColumn(name = "projectName", nullable = false, referencedColumnName = "name")
  @JoinColumn(name = "projectOwnerUsername", referencedColumnName = "ownerUsername", nullable = false)
  private ProjectEntity project;

}