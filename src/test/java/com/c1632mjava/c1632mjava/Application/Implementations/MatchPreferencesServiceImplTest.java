package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.MatchPreferencesMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesUpdateDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.CompatibilityPercentage;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Distance;
import com.c1632mjava.c1632mjava.Domain.Entities.MatchPreferences;
import com.c1632mjava.c1632mjava.Domain.Repositories.MatchPreferencesRepository;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchPreferencesServiceImplTest {
    @Mock
    private MatchPreferencesRepository matchPreferencesRepository;
    @Mock
    private MatchPreferencesMapper matchPreferencesMapper;
    @InjectMocks
    private MatchPreferencesServiceImpl matchPreferencesService;

    /*data for testing*/
    Long matchPreferenceId=6L;
    Long userId=2L;
    boolean female=true;
    boolean male=false;
    boolean other=false;
    int minAge=25;
    int maxAge=45;
    Distance distance=Distance.CERCA;
    CompatibilityPercentage compatibilityPercentage=CompatibilityPercentage.NO_DESAFINAN;
    boolean longTermRelationship=false;
    boolean justFriends=true;
    boolean rightNow=true;
    boolean active=true;

    @Test
    void createMatchPreferences() {/*advertencias extrañas*/
        MatchPreferencesServiceImpl testing = new MatchPreferencesServiceImpl(matchPreferencesRepository, matchPreferencesMapper );
        MatchPreferencesReadDto expectedReadDto= new MatchPreferencesReadDto(female, male, other, minAge, maxAge, distance,
        compatibilityPercentage, longTermRelationship,justFriends, rightNow);
        MatchPreferences expectedMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);
        MatchPreferencesCreateDto expectedPreferencesCreateDto=new MatchPreferencesCreateDto(userId, female, male, other, minAge,
                maxAge, distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow);

        when(matchPreferencesMapper.convertCreateToMatchPreferences(expectedPreferencesCreateDto)).
                thenReturn(expectedMatchPreferences);
        when(matchPreferencesRepository.save(expectedMatchPreferences)).thenReturn(expectedMatchPreferences);
        when(matchPreferencesMapper.convertMatchPreferencesToRead(expectedMatchPreferences)).thenReturn(expectedReadDto);

        MatchPreferencesReadDto result = testing.createMatchPreferences(expectedPreferencesCreateDto);
        assertNotNull(result);
        assertEquals(expectedReadDto, result);
    }

    @Test
    void findPreferencesByUserId() { /*advertencias extrañas*/
        MatchPreferencesServiceImpl testing = new MatchPreferencesServiceImpl(matchPreferencesRepository, matchPreferencesMapper);
        MatchPreferences expectedMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);
        MatchPreferencesReadDto expectedReadDto= new MatchPreferencesReadDto(female, male, other, minAge, maxAge, distance,
                compatibilityPercentage, longTermRelationship,justFriends, rightNow);

        when(matchPreferencesRepository.findByUserId(userId)).thenReturn(Optional.of(expectedMatchPreferences));
        when(matchPreferencesMapper.convertMatchPreferencesToRead(expectedMatchPreferences)).thenReturn(expectedReadDto);

        MatchPreferencesReadDto result = testing.findPreferencesByUserId(userId);
        assertNotNull(result);
        assertEquals(expectedReadDto, result);
    }
    @Test
    void updateMatchPreferences() {/*advertencias extrañas*/
        //MatchPreferencesServiceImpl testing = new MatchPreferencesServiceImpl(matchPreferencesRepository, matchPreferencesMapper);
        MatchPreferences oldMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);

        /*I decided to change minAge, maxAge, longTermRelationship*/
        minAge=18;
        maxAge=22;
        longTermRelationship=true;

        MatchPreferencesUpdateDto expectedUpdate= new MatchPreferencesUpdateDto(matchPreferenceId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow);
        MatchPreferences newMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);
        MatchPreferencesReadDto expectedReadDto= new MatchPreferencesReadDto(female, male, other, minAge, maxAge, distance,
                compatibilityPercentage, longTermRelationship,justFriends, rightNow);

        when(matchPreferencesRepository.findByUserId(expectedUpdate.userId())).thenReturn(Optional.of(oldMatchPreferences));
        when(matchPreferencesMapper.convertMatchPreferencesToRead(newMatchPreferences)).thenReturn(expectedReadDto);
        MatchPreferencesReadDto result=matchPreferencesService.updateMatchPreferences(expectedUpdate);
        assertNotNull(result);
        assertEquals(expectedReadDto, result);
    }
    @Test
    void toggleMatchPreferences() throws UserNotFoundException {/*fail*/
        MatchPreferences expectedMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);

        when(matchPreferencesRepository.findByUserId(userId)).thenReturn(Optional.of(expectedMatchPreferences));

        // Act
        Boolean result = matchPreferencesService.toggleMatchPreferences(userId);

        // Assert
        assertNotNull(result);
        assertEquals(!expectedMatchPreferences.isActive(), result);
    }

    /*negative cases*/
    @Test
    void createMatchPreferencesWith5EmptyParameters() {/*advertencias extrañas*/
        /*all the variables are null*/
        distance=null;
        minAge=0;
        maxAge=0;
        userId=null;
        compatibilityPercentage=null;
        MatchPreferencesServiceImpl testing = new MatchPreferencesServiceImpl(matchPreferencesRepository, matchPreferencesMapper );
        MatchPreferencesReadDto expectedReadDto= new MatchPreferencesReadDto(female, male, other, minAge, maxAge, distance,
                compatibilityPercentage, longTermRelationship,justFriends, rightNow);
        MatchPreferences expectedMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);
        MatchPreferencesCreateDto expectedPreferencesCreateDto=new MatchPreferencesCreateDto(userId, female, male, other, minAge,
                maxAge, distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow);

        when(matchPreferencesMapper.convertCreateToMatchPreferences(expectedPreferencesCreateDto)).
                thenReturn(expectedMatchPreferences);
        when(matchPreferencesRepository.save(expectedMatchPreferences)).thenReturn(expectedMatchPreferences);
        when(matchPreferencesMapper.convertMatchPreferencesToRead(expectedMatchPreferences)).thenReturn(expectedReadDto);

        MatchPreferencesReadDto result = testing.createMatchPreferences(expectedPreferencesCreateDto);
        assertNotNull(result);
        assertEquals(expectedReadDto, result);
    }

   @Test
   void updateMatchPreferencesWith5EmptyParameters() {/*advertencias extrañas*/
        MatchPreferencesServiceImpl testing = new MatchPreferencesServiceImpl(matchPreferencesRepository, matchPreferencesMapper);
        /*I decided to change minAge, maxAge, longTermRelationship*/
        userId=0L;
        matchPreferenceId=0L;
        minAge=0;
        maxAge=0;
        distance= null;
        compatibilityPercentage=null;
        longTermRelationship=true;
        MatchPreferences oldMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);

        MatchPreferencesUpdateDto expectedUpdate= new MatchPreferencesUpdateDto(matchPreferenceId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow);
        MatchPreferences newMatchPreferences = new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);
        MatchPreferencesReadDto expectedReadDto= new MatchPreferencesReadDto(female, male, other, minAge, maxAge, distance,
                compatibilityPercentage, longTermRelationship,justFriends, rightNow);

       when(matchPreferencesRepository.findByUserId(userId))
               .thenReturn(Optional.of(newMatchPreferences));

       Boolean result = matchPreferencesService.toggleMatchPreferences(userId);

       verify(matchPreferencesRepository, times(1)).findByUserId(userId);
       verify(matchPreferencesRepository, times(1)).save(any(MatchPreferences.class));

       assertEquals(!newMatchPreferences.isActive(), result);
    }
}