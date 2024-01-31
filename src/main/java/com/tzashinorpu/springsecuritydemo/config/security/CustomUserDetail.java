package com.tzashinorpu.springsecuritydemo.config.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Getter
@Setter
@JsonIgnoreProperties({"roles", "authorities"})
public class CustomUserDetail implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<SysRolePO> roles;


    List<SimpleGrantedAuthority> authorities;

    private void setAuthorities(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRolePO role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        this.authorities = authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        this.setAuthorities();
        return this.authorities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetail user = (CustomUserDetail) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        String roles_flatten = roles.stream().map(SysRolePO::toString).collect(Collectors.joining(","));
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", accountNonExpired=" + accountNonExpired +
            ", accountNonLocked=" + accountNonLocked +
            ", credentialsNonExpired=" + credentialsNonExpired +
            ", enabled=" + enabled +
            ", roles=" + roles +
            '}';
    }
}
