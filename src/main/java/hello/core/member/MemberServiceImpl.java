package hello.core.member;

public class MemberServiceImpl implements MemberService {

  // 인터페이스만 가지고 있으면 구현체가 없어서 NPE가 터진다.
  // 따라서 구현 객체(MemoryMemberRepository)를 선택해줘야 한다.
  private final MemberRepository memberRepository;

  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }


  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
