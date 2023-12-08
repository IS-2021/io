package com.example.demoio;

import com.example.demoio.models.User_Games;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY u.userScore DESC LIMIT 10")
    List<User> findTop10ByScore();

    @Query("SELECT ug FROM User_Games ug WHERE ug.game_id =:gameID ORDER BY ug.userScore DESC LIMIT 10")
    List<User_Games> getUsersRankByGameID(@Param("gameID") int gameID);

    User save(User user);

    User_Games save(User_Games userGames);


    //@Query("INSERT INTO User_Games ug(ug.game_id,ug.user_coins,ug.user_score,ug.username) VALUES(:gameID,:ucoins,:uscore,:uname)")
    //void saveUserScore(@Param("gameID") int gameId,@Param("ucoins") double coins,@Param("uscore") double uscore,@Param("uname") String username);
}
