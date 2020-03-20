package com.example.ecommerce.security;

import com.example.ecommerce.util.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // ========================

    // create getters and setters + constructor + build method for all the
    // attributes in the user entity
    private int id;

    private String name;

//    private String username;

    private String email;

    @JsonIgnore
    private String password;
    // ============================

    public UserPrincipal(int id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
//        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserPrincipal(int id2, String name2, String email2, String password2) {
		// TODO Auto-generated constructor stub
	}

	public static UserPrincipal build(Users user) {
        System.out.println(user);
        return new UserPrincipal(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // ============================================================
    // overridding the methods of user details interface since we are implementing
    // it
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }
}