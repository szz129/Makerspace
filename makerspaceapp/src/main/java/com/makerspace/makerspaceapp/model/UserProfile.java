package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;


@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {


@Id
@SequenceGenerator(name = "user_profile_seq_gen", sequenceName = "USER_PROFILE_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_seq_gen")
@Column(name = "PROFILE_ID")
private Long profileId;


@OneToOne
@JoinColumn(name = "USER_ID")
private User user;


@Column(name = "BIO", length = 1000)
private String bio;


@Column(name = "PROFILE_PICTURE")
private String profilePicture;


@Column(name = "ADDRESS")
private String address;


@Column(name = "SOCIAL_LINKS")
private String socialLinks;


public UserProfile() {}


public Long getProfileId() { return profileId; }
public void setProfileId(Long profileId) { this.profileId = profileId; }
public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
public String getBio() { return bio; }
public void setBio(String bio) { this.bio = bio; }
public String getProfilePicture() { return profilePicture; }
public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
public String getAddress() { return address; }
public void setAddress(String address) { this.address = address; }
public String getSocialLinks() { return socialLinks; }
public void setSocialLinks(String socialLinks) { this.socialLinks = socialLinks; }
}