package domains;

import domains.enumeration.Role;

public class User {

    private final Integer id;
    private final String email;
    private final String password;
    private final String telephone;
    private final Role role;
    //TODO Add List of accounts

    private User(Builder builder){
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.telephone = builder.telephone;
        this.role = builder.role;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephone() {
        return telephone;
    }

    public Role getRole() {
        return role;
    }

    public static class Builder{
        private Integer id;
        private String email;
        private String password;
        private String telephone;
        private Role role;

        public Builder withId(Integer id){
            this.id = id;
            return this;
        }
        public Builder withEmail(String email){
            this.email = email;
            return this;
        }
        public Builder withPassword(String password){
            this.password = password;
            return this;
        }
        public Builder withTelephone(String telephone){
            this.telephone = telephone;
            return this;
        }
        public Builder withRole(Role role){
            this.role = role;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
}
