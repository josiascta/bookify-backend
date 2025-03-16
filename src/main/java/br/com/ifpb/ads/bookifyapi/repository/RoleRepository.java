package br.com.ifpb.ads.bookifyapi.repository;

import br.com.ifpb.ads.bookifyapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNome(String nome);
}
