package models;

import java.time.LocalDateTime;

import enums.Gender;

public abstract class Users {
    private int userId;
    private String email;
    private String password;
    private Role role;
    private String fullName;
    private String phone;
    private String address;
    private Gender gender;
    private LocalDateTime createdAt;
    private boolean isActive;

    // Constructors
    public Users() {}
     public Users( String email , String password , Role role ){

        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = true;
    }
    public Users(int userId, String email, String password, Role role, String fullName,
                 String phone, String address, Gender gender, LocalDateTime createdAt, boolean isActive) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    @Override
    public String toString() {
        return "Users [userId=" + userId + ", email=" + email + ", role=" + role.getRoleName() + "]";
    }
    public void setGender(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGender'");
    }
}
