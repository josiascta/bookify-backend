package br.com.ifpb.ads.bookifyapi.service;

import br.com.ifpb.ads.bookifyapi.entity.Role;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.repository.RoleRepository;
import br.com.ifpb.ads.bookifyapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository cargoRepository;
    private final UserRepository usuarioRepository;

    public Role findById(Integer idCargo) throws RegraDeNegocioException {
        return cargoRepository.findById(idCargo).orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
    }

    public List<Role> findAll() throws RegraDeNegocioException {
        return cargoRepository.findAll();
    }

    public List<Role> findAllById(List<Integer> idCargo) {
        return cargoRepository.findAllById(idCargo);
    }
}
