package entity;


import domain.enums.Role;
public class UserEntity {

    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String telephone;
    private final Role role;


    private UserEntity(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.telephone = builder.telephone;
        this.role = builder.role;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
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



    public static Builder builder(){ return new Builder();}



    public static class Builder{
        private Integer id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private String telephone;
        private Role role;

        public Builder withId(Integer id){
            this.id = id;
            return this;
        }
        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withSurname(String surname){
            this.surname = surname;
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

        public UserEntity build(){
            return new UserEntity(this);
        }
    }

}
