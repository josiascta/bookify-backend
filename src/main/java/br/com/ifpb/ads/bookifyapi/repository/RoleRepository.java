package br.com.ifpb.ads.bookifyapi.repository;

import br.com.ifpb.ads.bookifyapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
