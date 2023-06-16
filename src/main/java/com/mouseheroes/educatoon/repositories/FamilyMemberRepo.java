package com.mouseheroes.educatoon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mouseheroes.educatoon.entities.FamilyMember;
import java.util.List;


public interface FamilyMemberRepo extends JpaRepository<FamilyMember, Long> {

    @Override
    List<FamilyMember> findAll();

    @Override
    FamilyMember save(FamilyMember fm);
}
