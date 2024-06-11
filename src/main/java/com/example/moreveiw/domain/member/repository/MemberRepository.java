package com.example.moreveiw.domain.member.repository;

import com.example.moreveiw.domain.member.model.dao.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByMemberName(String memberName);

    Member findByMemberName(String memberName);
}
