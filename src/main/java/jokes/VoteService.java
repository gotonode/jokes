package jokes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public void vote(Long id, String value) {

        Vote vote = this.voteRepository.findByJokeId(id);

        if (vote == null) {
            vote = new Vote(id, 0, 0);
        }

        if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }

        voteRepository.save(vote);
    }

    Vote getVote(Long id) {

        if (voteRepository.findByJokeId(id) == null) {
            Vote v = new Vote(id, 0, 0);
            voteRepository.save(v);
        }

        return voteRepository.findByJokeId(id);
    }

}
