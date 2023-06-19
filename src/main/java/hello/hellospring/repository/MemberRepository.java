package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //ctrl+d 라인복사
    Optional<Member> findByName(String name);

    List<Member> findAll();



}
