package melting_pot.ewha_sinmungo.vote.service;


import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.member.service.MemberService;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.post.repository.PostRepository;
import melting_pot.ewha_sinmungo.post.service.PostService;
import melting_pot.ewha_sinmungo.vote.entity.Vote;
import melting_pot.ewha_sinmungo.vote.reporitory.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class VoteService {

    private final MemberService memberService;
    private final PostService postService;
    private final VoteRepository voteRepository;

    //투표 생성
    public void createVote(Long postId){
        Member member = memberService.getCurrentMember();
        Post post = postService.findById(postId);

        if(isExistsByMemberAndPost(member,post)){
            throw new RuntimeException("이미 투표한 게시물입니다.");
        }

        Vote vote = Vote.builder()
                .post(post)
                .member(member)
                .build();

        voteRepository.save(vote);
        increaseVoteCount(post);
    }

    //투표 삭제
    public void deleteVote(Long postId){
        Post post = postService.findById(postId);
        Member member = memberService.getCurrentMember();

        Vote vote = voteRepository.findByMemberAndPost(member, post)
                .orElseThrow(()->new RuntimeException("투표가 존재하지 않습니다."));
        voteRepository.delete(vote);
        decreaseVoteCount(post);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByMemberAndPost(Member member, Post post){
        return voteRepository.existsByMemberAndPost(member,post);
    }

    //투표수 증가
    private void increaseVoteCount(Post post){
        post.setVoteCount(post.getVoteCount()+1);
    }

    //투표수 감소
    private void decreaseVoteCount(Post post){
        post.setVoteCount(post.getVoteCount()-1);
    }
}
