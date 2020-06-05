package net.ezens.pointnix.member;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import net.ezens.pointnix.member.model.Member;

@Repository
public interface MemberMapper {
	Long memberJoin(Member member);
	
	Optional<Member> findByUserId(String username);
}
