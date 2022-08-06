package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //MemberRepository(추상화), MemoryMemberRepository(구체화)에 모두 의존 -> DIP 원칙 위배
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
