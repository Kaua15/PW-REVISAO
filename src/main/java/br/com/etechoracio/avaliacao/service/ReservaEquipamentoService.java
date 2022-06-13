package br.com.etechoracio.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.avaliacao.entity.ReservaEquipamento;
import br.com.etechoracio.avaliacao.enums.StatusReservaEnum;
import br.com.etechoracio.avaliacao.exception.RecursoNaoEncontradoException;
import br.com.etechoracio.avaliacao.exception.RegraNegocioException;
import br.com.etechoracio.avaliacao.repository.ReservaEquipamentoRepository;

@Service
public class ReservaEquipamentoService {

    @Autowired
    private ReservaEquipamentoRepository repository;

    public List<ReservaEquipamento> findAll() {
    	return repository.findAll();
    }

    public Optional<ReservaEquipamento> findById(Long id) {
    	return repository.findById(id);
    }

    public ReservaEquipamento save(ReservaEquipamento reservaEquipamento) {
    	Optional<ReservaEquipamento> existe = repository.findByNumeroAndData(reservaEquipamento.getNumero(), reservaEquipamento.getData());
    	if(existe.isPresent() && (existe.get().getStatus() == StatusReservaEnum.EM_ANALISE || existe.get().getStatus() == StatusReservaEnum.APROVADA)){
    		throw new RegraNegocioException("Reserva Já realizada");
    	}
    	
    	reservaEquipamento.setStatus(StatusReservaEnum.EM_ANALISE);
    	return repository.save(reservaEquipamento);
    }

    public ReservaEquipamento approve(Long id) {
    	Optional<ReservaEquipamento> existe = repository.findById(id);
    	if(!existe.isPresent())
    	{
    		throw new RecursoNaoEncontradoException("Não existe");
    	}
    }

    public ReservaEquipamento cancel(Long id) {
    	throw new UnsupportedOperationException();
    }

}
