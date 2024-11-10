package com.example.produto.adapter.infra;


import com.example.produto.adapter.infra.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findByCategoriaAndStatus(String categoria, boolean status);
}
