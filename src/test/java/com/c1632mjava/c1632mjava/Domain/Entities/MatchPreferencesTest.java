package com.c1632mjava.c1632mjava.Domain.Entities;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.CompatibilityPercentage;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Distance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchPreferencesTest {
    /*data for testing*/
    Long matchPreferenceId=2L;
    Long userId=8L;
    boolean female=true;
    boolean male=false;
    boolean other=false;
    int minAge=20;
    int maxAge=26;
    Distance distance=Distance.LEJOS;
    CompatibilityPercentage compatibilityPercentage=CompatibilityPercentage.BUENOS_ACORDES;
    boolean longTermRelationship=true;
    boolean justFriends=false;
    boolean rightNow=false;

    private boolean active=true;
    MatchPreferences testing;

    @BeforeEach
    void setUp() {
        testing = new MatchPreferences();
    }

    @Test
    void emptyConstructorTest(){/*fail*/
        //assertNotNull(testing);
        assertEquals(0L, testing.getMatchPreferenceId());
        assertEquals(0L, testing.getUserId());
        assertFalse(testing.isFemale());
        assertFalse(testing.isMale());
        assertFalse(testing.isOther());
        assertFalse(testing.isLongTermRelationship());
        assertFalse(testing.isJustFriends());
        assertFalse(testing.isRightNow());
        assertEquals(0, testing.getMinAge());
        assertEquals(0, testing.getMaxAge());
        assertEquals("", testing.getDistance());//enum
        assertEquals("", testing.getCompatibilityPercentage());//enum

    }
    @Test
    void fullConstructorTest(){
        MatchPreferences mp=new MatchPreferences(matchPreferenceId, userId, female, male, other, minAge,
                maxAge, distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow, active);
        assertEquals(2L, mp.getMatchPreferenceId());
        assertEquals(8L, mp.getUserId());
        assertTrue(mp.isFemale());
        assertFalse(mp.isMale());
        assertFalse(mp.isOther());
        assertTrue(mp.isLongTermRelationship());
        assertFalse(mp.isJustFriends());
        assertFalse(mp.isRightNow());
        assertEquals(20, mp.getMinAge());
        assertEquals(26, mp.getMaxAge());
        assertEquals("BUENOS_ACORDES", mp.getCompatibilityPercentage().name());//enum
        assertEquals("LEJOS", mp.getDistance().name());//enum

    }

    @Test
    void getMatchPreferenceId() {
        Long matchPreferenceActual= testing.getMatchPreferenceId();
        Assertions.assertEquals(matchPreferenceId, matchPreferenceActual);
    }

    @Test
    void getUserId() {
        Long userIdActual= testing.getUserId();
        Assertions.assertEquals(matchPreferenceId, userIdActual);
    }

    @Test
    void isFemale() {
        boolean femaleActual=testing.isFemale();
        Assertions.assertTrue(female);
    }

    @Test
    void isMale() {
        boolean genreActual=testing.isMale();
        Assertions.assertFalse(genreActual);
    }

    @Test
    void isOther() {
        boolean otherActual=testing.isOther();
        Assertions.assertFalse(otherActual);
    }

    @Test
    void getMinAge() {
        testing.setMinAge(minAge);
        int minAgeActual= testing.getMinAge();
        Assertions.assertEquals(20, minAgeActual);
    }

    @Test
    void getMaxAge() {
        testing.setMaxAge(maxAge);
        int maxAgeActual= testing.getMaxAge();
        Assertions.assertEquals(26, maxAgeActual);
    }

    @Test
    void getDistance() {
        testing.setDistance(distance);
        Distance distanceActual=testing.getDistance();
        Assertions.assertEquals("LEJOS", distanceActual.name());
    }

    @Test
    void getCompatibilityPercentage() {
        testing.setCompatibilityPercentage(compatibilityPercentage);
        CompatibilityPercentage cpActual=testing.getCompatibilityPercentage();
        Assertions.assertEquals("BUENOS_ACORDES", cpActual.name());
    }

    @Test
    void isLongTermRelationship() {/*fail*/
        boolean lGrRelationshipActual=testing.isLongTermRelationship();
        Assertions.assertTrue(lGrRelationshipActual);
    }

    @Test
    void isJustFriends() {
        boolean justFriendsActual=testing.isJustFriends();
        Assertions.assertFalse(justFriendsActual);
    }

    @Test
    void isRightNow() {
        boolean rightNowActual=testing.isRightNow();
        Assertions.assertFalse(rightNowActual);
    }

    @Test
    void isActive() {/*fail*/
        boolean isActiveActual=testing.isActive();
        Assertions.assertTrue(isActiveActual);
    }

    @Test
    void setMatchPreferenceId() {
        testing.setMatchPreferenceId(matchPreferenceId);
        Long idExpected=2L;
        Long idActual= testing.getMatchPreferenceId();
        Assertions.assertEquals(idExpected, idActual);
    }

    @Test
    void setUserId() {
        testing.setUserId(userId);
        Long idExpected=8L;
        Long idActual= testing.getUserId();
        Assertions.assertEquals(idExpected, idActual);
    }

    @Test
    void setFemale() {
        testing.setFemale(female);
        boolean genreActual=testing.isFemale();
        Assertions.assertTrue(genreActual);
    }

    @Test
    void setMale() {
        testing.setMale(male);
        boolean genreActual=testing.isMale();
        Assertions.assertFalse(genreActual);
    }

    @Test
    void setOther() {
        testing.setMale(other);
        boolean genreActual=testing.isOther();
        Assertions.assertFalse(genreActual);
    }

    @Test
    void setMinAge() {
        testing.setMinAge(minAge);
        int minAgeExpected=20;
        int minAgeActual= testing.getMinAge();
        Assertions.assertEquals(minAgeExpected,minAgeActual);
    }

    @Test
    void setMaxAge() {
        testing.setMaxAge(maxAge);
        int maxAgeExpected=26;
        int maxAgeActual= testing.getMaxAge();
        Assertions.assertEquals(maxAgeExpected,maxAgeActual);
    }

    @Test
    void setDistance() {
        testing.setDistance(distance);
        //Distance distanceExpected=Distance.LEJOS;
        Distance distanceActual=testing.getDistance();
        Assertions.assertEquals("LEJOS", distanceActual.name());
    }

    @Test
    void setCompatibilityPercentage() {
        testing.setCompatibilityPercentage(compatibilityPercentage);
        //CompatibilityPercentage compabilityExpected=CompatibilityPercentage.BUENOS_ACORDES;
        CompatibilityPercentage compabilityActual=testing.getCompatibilityPercentage();
        Assertions.assertEquals("BUENOS_ACORDES", compabilityActual.name());
    }

    @Test
    void setLongTermRelationship() {
        testing.setLongTermRelationship(longTermRelationship);
        boolean relationshipActual=testing.isLongTermRelationship();
        Assertions.assertTrue(relationshipActual);
    }

    @Test
    void setJustFriends() {
        testing.setJustFriends(justFriends);
        boolean justFriendsActual=testing.isJustFriends();
        Assertions.assertFalse(justFriendsActual);
    }

    @Test
    void setRightNow() {
        testing.setRightNow(rightNow);
        boolean rightNowActual=testing.isRightNow();
        Assertions.assertFalse(rightNowActual);
    }

    @Test
    void setActive() {
        testing.setActive(active);
        boolean activeActual=testing.isActive();
        Assertions.assertTrue(activeActual);
    }
}