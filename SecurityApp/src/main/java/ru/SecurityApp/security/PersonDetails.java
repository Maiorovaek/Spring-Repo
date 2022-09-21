package ru.SecurityApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.SecurityApp.models.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PersonDetails implements UserDetails {
    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//        authorityList.add(new SimpleGrantedAuthority("SHOW_ACCOUNT"));

        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
//этот аккаунт включен и работает
    @Override
    public boolean isEnabled() {
        return true;
    }


    public Person getPerson(){
        return this.person;
    }
}
