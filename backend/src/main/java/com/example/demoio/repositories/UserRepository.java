package com.example.demoio.repositories;

import com.example.demoio.models.orm.User;
import com.example.demoio.models.orm.UserGames;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY u.userScore DESC LIMIT 10")
    List<User> findTop10ByScore();

    @Query("SELECT ug FROM UserGames ug WHERE ug.game_id =:gameID ORDER BY ug.userScore DESC LIMIT 10")
    List<UserGames> getUsersRankByGameID(@Param("gameID") int gameID);

    User save(User user);

    UserGames save(UserGames userGames);


    //@Query("INSERT INTO User_Games ug(ug.game_id,ug.user_coins,ug.user_score,ug.username) VALUES(:gameID,:ucoins,:uscore,:uname)")
    //void saveUserScore(@Param("gameID") int gameId,@Param("ucoins") double coins,@Param("uscore") double uscore,@Param("uname") String username);
}
