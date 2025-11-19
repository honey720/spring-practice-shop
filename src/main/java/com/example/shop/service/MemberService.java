package com.example.shop.service;

import com.example.shop.common.ResponseEntity;
import com.example.shop.controller.MemberRequest;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public ResponseEntity<List<MemberResponse>> getAllMember() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponse> response = new ArrayList<>();

        for(Member member : members) {
            response.add(new MemberResponse(
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    member.getPassword(),
                    member.getPhone(),
                    member.getRegId(),
                    member.getRegDt(),
                    member.getModifyId(),
                    member.getModifyDt(),
                    member.getSaltKey(),
                    member.getFlag()
            ));
        }

        return new ResponseEntity(
                HttpStatus.OK.value(),
                response,
                response.size()
        );
    }

    public ResponseEntity<MemberResponse> createMember(MemberRequest request) {
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

        MemberResponse response = new MemberResponse(
                member1.getId(),
                member1.getEmail(),
                member1.getName(),
                member1.getPassword(),
                member1.getPhone(),
                member1.getRegId(),
                member1.getRegDt(),
                member1.getModifyId(),
                member1.getModifyDt(),
                member1.getSaltKey(),
                member1.getFlag()
        );

        return new ResponseEntity<>(
                HttpStatus.OK.value(),
                response,
                1
        );
    }

    public ResponseEntity<MemberResponse> updateMember(MemberRequest request, String id) {
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

        MemberResponse response = new MemberResponse(
                member1.getId(),
                member1.getEmail(),
                member1.getName(),
                member1.getPassword(),
                member1.getPhone(),
                member1.getRegId(),
                member1.getRegDt(),
                member1.getModifyId(),
                member1.getModifyDt(),
                member1.getSaltKey(),
                member1.getFlag()
        );

        return new ResponseEntity<>(
                HttpStatus.OK.value(),
                response,
                1
        );
    }

    public ResponseEntity<?> deleteMember(String id) {
        memberRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(
                HttpStatus.OK.value(),
                null,
                0
        );
    }
}
