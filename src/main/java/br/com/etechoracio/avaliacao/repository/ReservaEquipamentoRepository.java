package br.com.etechoracio.avaliacao.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.avaliacao.entity.ReservaEquipamento;

public interface ReservaEquipamentoRepository extends JpaRepository<ReservaEquipamento, Long> {
	Optional<ReservaEquipamento> findByNumeroAndData(Integer numero, LocalDate data);
}
