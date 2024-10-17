package com.example.radio_active_mushroom.models.entity;

import com.example.radio_active_mushroom.models.primary_keys.MembershipRequestPrimeryKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "membership_request_entity")
@IdClass(MembershipRequestPrimeryKey.class)
public class MembershipRequestEntity {

  @Column(name = "message")
  private String message;

  @Column(name = "sended_at", nullable = false)
  private LocalDateTime sended_at = LocalDateTime.now();

  @Id
  @ManyToOne
  @JoinColumn(name = "sender_username")
  private UserEntity sender;

  @Id
  @ManyToOne
  @JoinColumn(name = "project_name", nullable = false, referencedColumnName = "name")
  @JoinColumn(name = "project_owner_username", referencedColumnName = "owner_username", nullable = false)
  private ProjectEntity project;

}