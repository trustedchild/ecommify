package com.revature.ecommify.models;

public class User {

    //has many orders //hmmm, doubt! may through lineitems
    //has many reviews

    private String id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String street_address;
    private String city;
    private String zip_code;
    private String country;
    private String avatar;
    private String role = "DEFAULT";
    private String last_sign_in;
    private String created_at;
    private String updated_at;

    public User(){

    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String id, String username, String password, String role, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
    }

    //user obj to return to admin dashboard
    public User(String id, String first_name, String last_name, String username, String email, String phone, String street_address, String city, String zip_code, String country, String avatar, String role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.street_address = street_address;
        this.city = city;
        this.zip_code = zip_code;
        this.country = country;
        this.avatar = avatar;
        this.role = role;
    }

    public User(String id, String first_name, String last_name, String username, String password, String email, String phone, String street_address, String city, String zip_code, String country, String avatar, String role, String last_sign_in, String created_at, String updated_at) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.street_address = street_address;
        this.city = city;
        this.zip_code = zip_code;
        this.country = country;
        this.avatar = avatar;
        this.role = role;
        this.last_sign_in = last_sign_in;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLast_sign_in() {
        return last_sign_in;
    }

    public void setLast_sign_in(String last_sign_in) {
        this.last_sign_in = last_sign_in;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", street_address='" + street_address + '\'' +
                ", city='" + city + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", country='" + country + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role='" + role + '\'' +
                ", last_sign_in='" + last_sign_in + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
