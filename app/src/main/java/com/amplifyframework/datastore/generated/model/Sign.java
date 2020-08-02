package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Sign type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Signs")
public final class Sign implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField MARKER_ID = field("markerId");
  public static final QueryField CAMPAIGN_ID = field("campaignId");
  public static final QueryField TITLE = field("title");
  public static final QueryField NUMBER = field("number");
  public static final QueryField LONGITUDE = field("longitude");
  public static final QueryField LATITUDE = field("latitude");
  public static final QueryField DESCRIPTION = field("description");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String markerId;
  private final @ModelField(targetType="String", isRequired = true) String campaignId;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="Int", isRequired = true) Integer number;
  private final @ModelField(targetType="Float", isRequired = true) Float longitude;
  private final @ModelField(targetType="Float", isRequired = true) Float latitude;
  private final @ModelField(targetType="String") String description;
  public String getId() {
      return id;
  }
  
  public String getMarkerId() {
      return markerId;
  }
  
  public String getCampaignId() {
      return campaignId;
  }
  
  public String getTitle() {
      return title;
  }
  
  public Integer getNumber() {
      return number;
  }
  
  public Float getLongitude() {
      return longitude;
  }
  
  public Float getLatitude() {
      return latitude;
  }
  
  public String getDescription() {
      return description;
  }
  
  private Sign(String id, String markerId, String campaignId, String title, Integer number, Float longitude, Float latitude, String description) {
    this.id = id;
    this.markerId = markerId;
    this.campaignId = campaignId;
    this.title = title;
    this.number = number;
    this.longitude = longitude;
    this.latitude = latitude;
    this.description = description;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Sign sign = (Sign) obj;
      return ObjectsCompat.equals(getId(), sign.getId()) &&
              ObjectsCompat.equals(getMarkerId(), sign.getMarkerId()) &&
              ObjectsCompat.equals(getCampaignId(), sign.getCampaignId()) &&
              ObjectsCompat.equals(getTitle(), sign.getTitle()) &&
              ObjectsCompat.equals(getNumber(), sign.getNumber()) &&
              ObjectsCompat.equals(getLongitude(), sign.getLongitude()) &&
              ObjectsCompat.equals(getLatitude(), sign.getLatitude()) &&
              ObjectsCompat.equals(getDescription(), sign.getDescription());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getMarkerId())
      .append(getCampaignId())
      .append(getTitle())
      .append(getNumber())
      .append(getLongitude())
      .append(getLatitude())
      .append(getDescription())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Sign {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("markerId=" + String.valueOf(getMarkerId()) + ", ")
      .append("campaignId=" + String.valueOf(getCampaignId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("number=" + String.valueOf(getNumber()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("description=" + String.valueOf(getDescription()))
      .append("}")
      .toString();
  }
  
  public static MarkerIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Sign justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Sign(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      markerId,
      campaignId,
      title,
      number,
      longitude,
      latitude,
      description);
  }
  public interface MarkerIdStep {
    CampaignIdStep markerId(String markerId);
  }
  

  public interface CampaignIdStep {
    TitleStep campaignId(String campaignId);
  }
  

  public interface TitleStep {
    NumberStep title(String title);
  }
  

  public interface NumberStep {
    LongitudeStep number(Integer number);
  }
  

  public interface LongitudeStep {
    LatitudeStep longitude(Float longitude);
  }
  

  public interface LatitudeStep {
    BuildStep latitude(Float latitude);
  }
  

  public interface BuildStep {
    Sign build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep description(String description);
  }
  

  public static class Builder implements MarkerIdStep, CampaignIdStep, TitleStep, NumberStep, LongitudeStep, LatitudeStep, BuildStep {
    private String id;
    private String markerId;
    private String campaignId;
    private String title;
    private Integer number;
    private Float longitude;
    private Float latitude;
    private String description;
    @Override
     public Sign build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Sign(
          id,
          markerId,
          campaignId,
          title,
          number,
          longitude,
          latitude,
          description);
    }
    
    @Override
     public CampaignIdStep markerId(String markerId) {
        Objects.requireNonNull(markerId);
        this.markerId = markerId;
        return this;
    }
    
    @Override
     public TitleStep campaignId(String campaignId) {
        Objects.requireNonNull(campaignId);
        this.campaignId = campaignId;
        return this;
    }
    
    @Override
     public NumberStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public LongitudeStep number(Integer number) {
        Objects.requireNonNull(number);
        this.number = number;
        return this;
    }
    
    @Override
     public LatitudeStep longitude(Float longitude) {
        Objects.requireNonNull(longitude);
        this.longitude = longitude;
        return this;
    }
    
    @Override
     public BuildStep latitude(Float latitude) {
        Objects.requireNonNull(latitude);
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String markerId, String campaignId, String title, Integer number, Float longitude, Float latitude, String description) {
      super.id(id);
      super.markerId(markerId)
        .campaignId(campaignId)
        .title(title)
        .number(number)
        .longitude(longitude)
        .latitude(latitude)
        .description(description);
    }
    
    @Override
     public CopyOfBuilder markerId(String markerId) {
      return (CopyOfBuilder) super.markerId(markerId);
    }
    
    @Override
     public CopyOfBuilder campaignId(String campaignId) {
      return (CopyOfBuilder) super.campaignId(campaignId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder number(Integer number) {
      return (CopyOfBuilder) super.number(number);
    }
    
    @Override
     public CopyOfBuilder longitude(Float longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder latitude(Float latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
  }
  
}
