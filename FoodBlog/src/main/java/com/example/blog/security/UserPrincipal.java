package com.example.blog.security;



import com.example.blog.util.Busers;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
    // ========================

    // create getters and setters + constructor + build method for all the
    // attributes in the user entity
    private int userId;

   

    private String userName;

    private String email;

    @JsonIgnore
    private String password;
    // ============================

    public UserPrincipal(int userId,  String userName, String email, String password) {
        this.userId = userId;

        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static UserPrincipal build(Busers user) {
        System.out.println(user);
        return new UserPrincipal(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword());
    }

    public int getUserId() {
        return userId;
    }


    public String getEmail() {
        return email;
    }

    
   

    @Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
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
        return Objects.equals(userId, user.userId);
    }

	
	
}
