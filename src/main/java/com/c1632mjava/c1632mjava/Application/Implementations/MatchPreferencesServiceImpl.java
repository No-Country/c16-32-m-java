package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.MatchPreferencesMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.*;
import com.c1632mjava.c1632mjava.Domain.Entities.MatchPreferences;
import com.c1632mjava.c1632mjava.Domain.Repositories.MatchPreferencesRepository;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchPreferencesServiceImpl implements MatchPreferencesService {

    private final MatchPreferencesRepository matchPreferencesRepository;
    private final MatchPreferencesMapper matchPreferencesMapper;

    @Override
    public MatchPreferencesReadDto createMatchPreferences(
            MatchPreferencesCreateDto matchPreferencesCreateDto) {
        MatchPreferences matchPreferences = matchPreferencesMapper.
                convertCreateToMatchPreferences(matchPreferencesCreateDto);
        matchPreferences = matchPreferencesRepository.save(matchPreferences);
        return matchPreferencesMapper.convertMatchPreferencesToRead(matchPreferences);
    }

    @Override
    public MatchPreferencesReadDto findPreferencesByUserId(Long id)
            throws EntityNotFoundException {
        MatchPreferences matchPreferences =
                matchPreferencesRepository.findByUserId(id).orElseThrow(EntityNotFoundException::new);
        return matchPreferencesMapper.convertMatchPreferencesToRead(matchPreferences);
    }

    @Override
    public MatchPreferencesReadDto updateMatchPreferences(
            MatchPreferencesUpdateDto mPUpdateDto)
            throws EntityNotFoundException {
        MatchPreferences newPreferences =
                matchPreferencesRepository.findByUserId(mPUpdateDto.userId())
                        .orElseThrow(EntityNotFoundException::new);
        if(newPreferences.isActive()) {
            if (mPUpdateDto.distance() != null) {
                newPreferences.setDistance(mPUpdateDto.distance());
            }
            if (mPUpdateDto.compatibilityPercentage() != null) {
                newPreferences.setCompatibilityPercentage(
                        mPUpdateDto.compatibilityPercentage());
            }
            newPreferences.setMinAge(mPUpdateDto.minAge());
            newPreferences.setMaxAge(mPUpdateDto.maxAge());
            newPreferences.setFemale(mPUpdateDto.female());
            newPreferences.setMale(mPUpdateDto.male());
            newPreferences.setOther(mPUpdateDto.other());
            newPreferences.setLongTermRelationship(
                    mPUpdateDto.longTermRelationship());
            newPreferences.setJustFriends(
                    mPUpdateDto.longTermRelationship());
            newPreferences.setRightNow(
                    mPUpdateDto.rightNow());
            matchPreferencesRepository.save(newPreferences);
        }
        return matchPreferencesMapper.
                convertMatchPreferencesToRead(newPreferences);
    }

    @Override
    public Boolean toggleMatchPreferences(Long userId)
            throws EntityNotFoundException {
        MatchPreferences toggleMP =
                matchPreferencesRepository.findByUserId(userId)
                        .orElseThrow(EntityNotFoundException::new);
        toggleMP.setActive(!toggleMP.isActive());
        return toggleMP.isActive();
    }
}
