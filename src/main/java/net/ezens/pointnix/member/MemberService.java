package net.ezens.pointnix.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import net.ezens.pointnix.member.model.CustomUserDetails;
import net.ezens.pointnix.member.model.Member;
import net.ezens.pointnix.member.model.Role;

@Log4j2
@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	// join user
	@Transactional
	public Long joinUser(Member member) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
		return memberMapper.memberJoin(member);
	}
	
	// find user
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*
		 * Optional<Member> memberWrapper = memberMapper.findByUserId(username); Member
		 * member = memberWrapper.get();
		 */
				
		log.info("enter login : " + username);
		
		CustomUserDetails userDetails = new CustomUserDetails();
		/*
		 * userDetails.setUsername(member.getUsername());
		 * userDetails.setPassword(member.getPassword());
		 */
		userDetails.setUsername(username);
		userDetails.setPassword("1234");
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(username.equals("admin")) {
			log.info("enter the admin!");
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			log.info("enter the member!");
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		userDetails.setAuthorities(authorities);
		userDetails.setEnabled(true);
		userDetails.setAccountNonExpired(true);
		userDetails.setAccountNonLocked(true);
		userDetails.setCredentialsNonExpired(true);
		
		return userDetails;
	}
}
