package com.jquantium.bean.user;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Table(name = "security_users")
public class User implements UserDetails {

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

    @Row(name = "roleId")
    private int roleId;
    private AccessManager accessManager;

    private UserProfile profile;

    public int getUserId() {
        return userId;
    }
    public UserSecurity setUserId(int userId) {
        this.userId = userId;
        return this;
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
        if (authorities != null)
            return Primitives.slice(",", authorities);
        else
            return "ROLE_USER";
    }
    public UserSecurity setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }
    public UserSecurity setAuthorities(String authorities) {
        this.authorities = new HashSet<GrantedAuthority>();

        for (final String role : authorities.split(","))
            if (role != null && !"".equals(role.trim())) {
                GrantedAuthority grandAuthority = new GrantedAuthority() {
                    private static final long serialVersionUID = 3958183417696804555L;

                    public String getAuthority() {
                        return role.trim();
                    }
                };

                this.authorities.add(grandAuthority);
            }

        return this;
    }

    public int getRoleId() {
        return roleId;
    }
    public UserSecurity setRoleId(int roleId) {
        this.roleId = roleId;
        return this;
    }
    public AccessManager getAccessManager() {
        return accessManager;
    }
    public UserSecurity setAccessManager(AccessManager accessManager) {
        this.accessManager = accessManager;
        return this;
    }

    public UserProfile getProfile() {
        return profile;
    }
    public UserSecurity setProfile(UserProfile profile) {
        this.profile = profile;
        return this;
    }
}
