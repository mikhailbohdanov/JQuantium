package com.jquantium.bean.security;

import com.jquantium.bean.social.UserSocial;
import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import com.jquantium.util.Primitives;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Table(name = "security_users")
public class UserSecurity implements UserDetails  {

    @Column(primary = true, length = 10, autoIncrement = true, unsigned = true)
    private int userId;

    @Column(unique = "name", length = 128, notNull = true)
    private String userName;

    @Column(length = 128, notNull = true)
    private String password;

    @Column
    private boolean accountNonExpired;

    @Column
    private boolean accountNonLocked;

    @Column
    private boolean credentialsNonExpired;

    @Column
    private boolean enabled;

    @Column(unique = "email")
    private String email;

    @Column(unique = "phone", length = 16)
    private String phone;

    @Column(length = 4)
    private String languageCode;

    private Collection<GrantedAuthority> authorities;

    private UserSocial userSocial;

    public int getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return userName;
    }
    public String getUserName() {
        return userName;
    }
    public UserSecurity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public UserSecurity setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public UserSecurity setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    public UserSecurity setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public UserSecurity setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public UserSecurity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public UserSecurity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }
    public UserSecurity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getLanguageCode() {
        return languageCode;
    }
    public UserSecurity setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    public String getAuthoritiesAsString() {
        if (authorities != null) {
            return Primitives.join(",", authorities);
        } else {
            return "ROLE_USER";
        }
    }
    public UserSecurity setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }
    public UserSecurity setAuthorities(String authorities) {
        this.authorities = new HashSet<GrantedAuthority>();

        for (final String role : authorities.split(",")) {
            if (role != null && !"".equals(role.trim())) {
                GrantedAuthority grandAuthority = new GrantedAuthority() {
                    private static final long serialVersionUID = 3958183417696804555L;

                    public String getAuthority() {
                        return role.trim();
                    }
                };

                this.authorities.add(grandAuthority);
            }
        }

        return this;
    }

    public UserSocial getUserSocial() {
        return userSocial;
    }
    public UserSecurity setUserSocial(UserSocial userSocial) {
        this.userSocial = userSocial;
        return this;
    }
}
