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
AND (((SELECT male FROM match_preferences mp  WHERE mp.user_id = loggedUserId) 
AND (u.gender = "MASCULINO"))
OR ((SELECT female FROM match_preferences mp  WHERE mp.user_id = loggedUserId) 
AND (u.gender = "FEMENINO"))
OR ((SELECT other FROM match_preferences mp  WHERE mp.user_id = loggedUserId) 
AND (u.gender = "OTRX")))
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
     >=
       (SELECT case compatibility_percentage
                WHEN 'MELODIAS_GEMELAS' THEN 91
                WHEN 'RITMO_PERFECTO' THEN 81
                WHEN 'BUENOS_ACORDES' THEN 71
                WHEN 'NOTAS_SIMILARES' THEN 55
                WHEN 'NO_DESAFINAN' THEN 40
                ELSE null
                END
        FROM match_preferences mp  WHERE user_id = loggedUserId))
-- validacion porcentajes de compatibilidad

;
END$$

DELIMITER ;generateAlgorithm