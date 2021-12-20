// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TrackDAO {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackDAO(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void post(Set<Track> tracks) {
        trackRepository.saveAll(tracks);
    }

    public void post(Track track) {
        trackRepository.save(track);
    }
}
