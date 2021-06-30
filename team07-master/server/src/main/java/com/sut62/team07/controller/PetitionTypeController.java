package com.sut62.team07.controller;

import com.sut62.team07.entity.PetitionType;
import com.sut62.team07.repository.PetitionTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class PetitionTypeController {

  @Autowired
  private final PetitionTypeRepository petitionTypeRepository;

  public PetitionTypeController(PetitionTypeRepository petitionTypeRepository) {
    this.petitionTypeRepository = petitionTypeRepository;
  }

  @GetMapping("/petitionType")
  public Collection<PetitionType> PetitionType() {
    return petitionTypeRepository.findAll().stream().collect(Collectors.toList());
  }

}
