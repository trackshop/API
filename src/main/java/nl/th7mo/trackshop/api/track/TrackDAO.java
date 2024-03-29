// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class TrackDAO {

    private final TrackRepository trackRepository;

    public void post(List<Track> tracks) {
        trackRepository.saveAll(tracks);
    }

    public void post(Track track) {
        trackRepository.save(track);
    }

    public Track get(String id) throws TrackNotFoundException {
        return trackRepository.findById(id).orElseThrow(() ->
            new TrackNotFoundException(
                "Track with id '" + id + "' could not be found in the database"
            )
        );
    }
}
