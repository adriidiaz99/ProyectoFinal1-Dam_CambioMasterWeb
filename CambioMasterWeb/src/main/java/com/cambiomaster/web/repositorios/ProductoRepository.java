package com.cambiomaster.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cambiomaster.web.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
