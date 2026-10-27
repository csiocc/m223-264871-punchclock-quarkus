package ch.zli.m223.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record EntryDto(
  @NotNull LocalDateTime checkIn, 
  @NotNull LocalDateTime checkOut) {
  @AssertTrue(message = "checkOut muss nach checkIn liegen")
  public boolean isCheckOutAfterCheckIn() {
    return checkIn == null || checkOut == null || checkOut.isAfter(checkIn);
    }
  }
