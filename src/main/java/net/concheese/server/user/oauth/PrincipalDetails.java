package net.concheese.server.user.oauth;

import lombok.Getter;
import lombok.ToString;
import net.concheese.server.user.model.User;
import net.concheese.server.user.dto.OAuth2UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {
    private User user;
    private OAuth2UserInfo oAuth2UserInfo;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public PrincipalDetails(User user, OAuth2UserInfo oAuth2UserInfo) {
        this.user = user;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    // 유저의 권한 목록 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });
        return collect;
    }

    //네이버에서는 password를 제공하지 않으나 스프링 시큐리티 특성상 패스워드 설정해야 함
    @Override
    public String getPassword() {
        return null;
    }


    /**
     * Username을 Null로 반환 시 principalName cannot be empty 에러 발생
     * name을 반환하는 메서드가 이미 존재하므로 LoginId 반환
     */
    @Override
    public String getUsername() {
        return oAuth2UserInfo.getLoginId();
    }


    // 계정 만료 여부 false -> 만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    // 계정 잠김 여부 false -> 잠김
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 false -> 만료
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부 true -> 활성화
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }

    @Override
    public String getName() {
        return oAuth2UserInfo.getName();
    }
}
