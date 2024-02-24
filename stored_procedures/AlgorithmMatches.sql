DELIMITER $$

CREATE PROCEDURE generateAlgorithm(loggedUserId bigint)
BEGIN

SELECT loggedUserId AS user1, u.user_id AS user2, 

(100 * ((SELECT COUNT(artists_artist_id) FROM users_artists WHERE user_user_id in (u.user_id, loggedUserId)) 
       / 
       (SELECT COUNT(artists_artist_id) FROM users_artists WHERE user_user_id in (loggedUserId)))) AS compatibility_percentage


FROM users u 

WHERE 
    -- validacion edad
    (year(sysdate()) - year(u.birthdate) BETWEEN 
    (SELECT min_age FROM match_preferences mp WHERE mp.user_id = loggedUserId) AND 
    (SELECT max_age FROM match_preferences mp  WHERE mp.user_id = loggedUserId))
    -- validacion edad
    
-- validacion identidad de genero
AND ((SELECT male FROM match_preferences mp  WHERE mp.user_id = loggedUserId) AND (u.gender = "MALE"))
AND ((SELECT female FROM match_preferences mp  WHERE mp.user_id = loggedUserId) AND (u.gender = "FEMALE"))
AND ((SELECT other FROM match_preferences mp  WHERE mp.user_id = loggedUserId) AND (u.gender = "OTHER"))
-- validacion identidad de genero

-- validacion larga relacion
AND ((SELECT long_term_relationship FROM match_preferences mp  WHERE mp.user_id = loggedUserId) AND 
    (SELECT long_term_relationship FROM match_preferences mp  WHERE mp.user_id = u.user_id)) 
-- validacion larga relacion

-- validacion solo como amigos
AND ((SELECT just_friends FROM match_preferences mp  WHERE mp.user_id = loggedUserId) AND 
    (SELECT just_friends FROM match_preferences mp  WHERE mp.user_id = u.user_id))
-- validacion solo como amigos

-- validacion de aqui y ahora
AND ((SELECT right_now FROM match_preferences mp  WHERE mp.user_id = loggedUserId) AND 
    (SELECT right_now FROM match_preferences mp  WHERE mp.user_id = u.user_id)) 
-- validacion de aqui y ahora
    
-- validacion porcentajes de compatibilidad
AND ((100 * ((SELECT COUNT(artists_artist_id) FROM users_artists WHERE user_user_id in (u.user_id, loggedUserId)) 
       / 
       (SELECT COUNT(artists_artist_id) FROM users_artists WHERE user_user_id in (loggedUserId)))) 
       =
       (SELECT compatibility_percentage FROM match_preferences mp  WHERE user_id = loggedUserId));
-- validacion porcentajes de compatibilidad
END$$

DELIMITER ;generateAlgorithm