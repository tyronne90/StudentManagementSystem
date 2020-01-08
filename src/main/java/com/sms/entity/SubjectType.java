package com.sms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SubjectType {
  @JsonProperty("C/G") CREADITWITHGPA,
  @JsonProperty("C/NG") CREADITWITHNONGPA,
  @JsonProperty("O/NG") OPTIONALWITHNONGPA,
  @JsonProperty("O/G") OPTIONALWITHGPA,
  @JsonProperty("E/G") EFFECTIVEWITHGPA,
  @JsonProperty("E/NG") EFFECTIVEWITHNONGPA,
}
