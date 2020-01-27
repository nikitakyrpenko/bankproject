package domains;

import domains.abstracts.Account;
import domains.enumeration.Role;
import java.util.List;
import java.util.Objects;

import static domains.utility.CollectionUtility.*;

public class User {

    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String telephone;
    private final Role role;
    private  List<Account> accounts;

    private User(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.telephone = builder.telephone;
        this.role = builder.role;
        this.accounts = nullSafeListInitialize(builder.accounts);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts){this.accounts = nullSafeListInitialize(accounts);}

    public static Builder builder(){ return new Builder();}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(telephone, user.telephone) &&
                role == user.role &&
                Objects.equals(accounts, user.accounts);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, telephone, role, accounts);
    }

    public static class Builder{
        private Integer id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private String telephone;
        private Role role;
        private List<Account> accounts;

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
        public Builder withAccounts(List<Account> accounts){
            this.accounts = accounts;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
}
