package com.sms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Roles {
  @JsonProperty("admin") ADMIN,
  @JsonProperty("user") USER,
  @JsonProperty("other") OTHER
}
