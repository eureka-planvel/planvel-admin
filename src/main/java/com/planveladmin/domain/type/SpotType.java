package com.planveladmin.domain.type;

public enum SpotType {
  FOOD("음식"),
  TOURIST("관광지"),
  CAFE("카페");

  private final String displayName;

  SpotType(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static SpotType fromString(String value) {
    for (SpotType type : SpotType.values()) {
      if (type.name().equalsIgnoreCase(value) || type.getDisplayName().equals(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("유효하지 않은 Spot Type: " + value);
  }
}
