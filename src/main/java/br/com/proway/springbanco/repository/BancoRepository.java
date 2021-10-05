package br.com.proway.springbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.proway.springbanco.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {

}
