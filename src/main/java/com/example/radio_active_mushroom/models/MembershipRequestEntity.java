package com.example.radio_active_mushroom.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "membership_request_entity")
public class MembershipRequestEntity {
  @Column(name = "message")
  private String message;

  @Column(name = "sended_at", nullable = false)
  private LocalDateTime sended_at;

}