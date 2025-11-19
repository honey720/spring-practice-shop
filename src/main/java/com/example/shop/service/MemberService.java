package com.example.shop.service;

import com.example.shop.common.ResponseEntity;
import com.example.shop.controller.MemberRequest;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public ResponseEntity<List<Member>> getAllMember() {
        return new ResponseEntity(
                HttpStatus.OK.value(),
                memberRepository.findAll(),
                memberRepository.count()
        );
    }

    public ResponseEntity<Member> createMember(MemberRequest request) {
        Member member = new Member(
                UUID.randomUUID(),
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );

        Member member1 = memberRepository.save(member);

        AtomicInteger cnt = new AtomicInteger();
        if (member1 instanceof List) {
            ((List<?>) member1).size();
        }else {
            cnt.set(1);
        }

        return new ResponseEntity<>(
                HttpStatus.OK.value(),
                member1,
                cnt.get()
        );
    }

    public ResponseEntity<Member> updateMember(MemberRequest request, String id) {
        Member member = memberRepository.findById(UUID.fromString(id)).orElseThrow();

        member.updateMember(
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );

        Member member1 = memberRepository.save(member);

        AtomicInteger cnt = new AtomicInteger();
        if (member1 instanceof List) {
            ((List<?>) member1).size();
        }else {
            cnt.set(1);
        }

        return new ResponseEntity<>(
                HttpStatus.OK.value(),
                member1,
                cnt.get()
        );
    }

    public ResponseEntity<?> deleteMember(String id) {
        memberRepository.deleteById(UUID.fromString(id));
        return null;
    }
}
