package BandurriaLingo.backend.Repository;

import BandurriaLingo.backend.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("SELECT c FROM Chat c WHERE :id1 MEMBER OF c.integrantes AND :id2 MEMBER OF c.integrantes")
    Optional<Chat> findChatBetweenUsers(@Param("id1") int id1, @Param("id2") int id2);
}
