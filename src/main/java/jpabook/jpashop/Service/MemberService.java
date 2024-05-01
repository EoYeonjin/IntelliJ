package jpabook.jpashop.Service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        readOnly = true
)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        this.validateDuplicateMember(member);
        this.memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = this.memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMember() {
        return this.memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return this.memberRepository.findOne(memberId);
    }

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
